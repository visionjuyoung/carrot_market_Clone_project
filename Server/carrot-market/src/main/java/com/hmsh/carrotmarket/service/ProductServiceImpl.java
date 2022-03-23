package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.converter.ImageConverter;
import com.hmsh.carrotmarket.converter.ProductConverter;
import com.hmsh.carrotmarket.dto.ImageDTO;
import com.hmsh.carrotmarket.dto.PageRequestDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;
import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.entity.ProductImage;
import com.hmsh.carrotmarket.repository.ProductImageRepository;
import com.hmsh.carrotmarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final FileService fileService;


    /**
     * 상품 등록
     * @param productDTO 상품 정보
     * @param uploadFiles 이미지 파일
     * @return 등록된 상품의 ID
     */
    @Override
    @Transactional
    public Long register(ProductDTO productDTO, MultipartFile[] uploadFiles) {
        Product product = ProductConverter.dtoToEntity(productDTO);

        if (!Objects.isNull(uploadFiles)) {
            List<ImageDTO> imageDTOList = fileService.uploadImageFiles(uploadFiles);
            List<ProductImage> productImageList = imageDTOList.stream()
                    .map(imageDTO -> ImageConverter.imageDTOToProductImage(imageDTO, product))
                    .collect(Collectors.toList());

            log.info("save images = {}", imageDTOList);
            productImageRepository.saveAll(productImageList);
        }
        Product save = productRepository.save(product);

        return save.getId();
    }

    /**
     * 상품 조회
     * @param id 조회할 상품의 ID
     * @return 상품 정보
     */
    @Override
    @Transactional
    public ProductDTO get(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setViews(product.getViews() + 1);
            productRepository.save(product);

            List<ProductImage> imageList = productImageRepository.findProductImagesByProduct(product);
            List<String> imagePathList = imageList.stream()
                    .map(ImageConverter::productImageToImageDTO)
                    .map(ImageDTO::getImageURL)
                    .collect(Collectors.toList());

            return ProductConverter.entityToDTO(product, imagePathList);
        }

        return null;
    }

    /**
     * address 와 같은 상품들을 최근 수정 날짜 순으로 정렬하고 20개씩 페이징해서 조회
     * @param pageRequestDTO 페이징 정보
     * @param address 주소
     * @return 상품 정보 리스트
     */
    @Override
    public List<ProductListDTO> getList(PageRequestDTO pageRequestDTO, String address) {
        return productRepository.getProductListByAddress(
                pageRequestDTO.getPageable(Sort.by("modDate").descending()), address).stream()
                .map(ProductConverter::entityToListDTO)
                .collect(Collectors.toList());
    }

    /**
     * 상품 정보 수정
     * @param productDTO 수정된 상품 정보
     * @param uploadFiles 수정된 이미지 파일
     * @return 수정할 상품의 id 존재 여부
     */
    @Override
    @Transactional
    public Boolean modify(ProductDTO productDTO, MultipartFile[] uploadFiles) {
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());

        if (!optionalProduct.isPresent()) return false;

        Product product = optionalProduct.get();
        product.setPrice(productDTO.getPrice());
        product.setContent(productDTO.getContent());
        product.setTitle(productDTO.getTitle());

        productRepository.save(product);
        productImageRepository.deleteAllProductImageByProduct(product);

        List<ImageDTO> imageDTOList = fileService.uploadImageFiles(uploadFiles);
        List<ProductImage> productImageList = imageDTOList.stream()
                .map(dto -> ImageConverter.imageDTOToProductImage(dto, product))
                .collect(Collectors.toList());
        productImageRepository.saveAll(productImageList);

        return true;
    }

    /**
     * 상품 삭제
     * @param id 삭제할 상품의 id
     * @return 삭제 처리 성공 여부
     */
    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) return false;

        Product product = optionalProduct.get();
        productImageRepository.deleteAllProductImageByProduct(product);
        productRepository.delete(product);

        return true;
    }


}
