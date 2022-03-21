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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final FileService fileService;


    @Override
    @Transactional
    public Long register(ProductDTO productDTO, MultipartFile[] uploadFiles) {
        log.info("register, productDTO = {}", productDTO);
        Product product = ProductConverter.dtoToEntity(productDTO);

        List<ImageDTO> imageDTOList = fileService.uploadFiles(uploadFiles);
        List<ProductImage> productImageList = imageDTOList.stream()
                .map(imageDTO -> ImageConverter.imageDTOToProductImage(imageDTO, product))
                .collect(Collectors.toList());

        log.info("save images = {}", imageDTOList);

        Product save = productRepository.save(product);
        productImageRepository.saveAll(productImageList);

        return save.getId();
    }

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

    @Override
    public List<ProductListDTO> getList(PageRequestDTO pageRequestDTO, String address) {
        return productRepository.getListByAddress(
                pageRequestDTO.getPageable(Sort.by("modDate").descending()), address).stream()
                .map(ProductConverter::entityToListDTO)
                .collect(Collectors.toList());
    }
}
