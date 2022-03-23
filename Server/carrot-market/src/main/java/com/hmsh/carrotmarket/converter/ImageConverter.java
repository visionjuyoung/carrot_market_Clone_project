package com.hmsh.carrotmarket.converter;

import com.hmsh.carrotmarket.dto.ImageDTO;
import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.entity.ProductImage;

public class ImageConverter {

    public static ProductImage imageDTOToProductImage(ImageDTO imageDTO, Product product) {
        return ProductImage.builder()
                .product(product)
                .imgName(imageDTO.getImgName())
                .path(imageDTO.getPath())
                .uuid(imageDTO.getUuid())
                .build();
    }

    public static ImageDTO productImageToImageDTO(ProductImage productImage) {
        return ImageDTO.builder()
                .imgName(productImage.getImgName())
                .uuid(productImage.getUuid())
                .path(productImage.getPath())
                .build();
    }
}
