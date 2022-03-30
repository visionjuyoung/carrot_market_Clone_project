package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.PageRequestDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;
import com.hmsh.carrotmarket.enumeration.TradeStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Long register(ProductDTO productDTO, MultipartFile[] uploadFiles);

    ProductDTO get(Long id, String phoneNumber);

    List<ProductListDTO> getList(PageRequestDTO pageRequestDTO, String address);

    Boolean modify(ProductDTO productDTO, MultipartFile[] uploadFiles);

    Boolean delete(Long id);

    List<ProductListDTO> getLikesList(String phoneNumber);

    List<ProductListDTO> getMyProducts(String phoneNumber);

    void changeTradeStatus(Long productId, TradeStatus tradeStatus) throws IllegalArgumentException;
}
