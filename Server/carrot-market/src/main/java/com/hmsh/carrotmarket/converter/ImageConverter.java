package com.hmsh.carrotmarket.converter;

import com.hmsh.carrotmarket.dto.ImageDTO;
import com.hmsh.carrotmarket.entity.*;

public class ImageConverter {

    public static ProductImage imageDTOToProductImage(ImageDTO imageDTO, Product product) {
        return ProductImage.builder()
                .product(product)
                .imgName(imageDTO.getImgName())
                .path(imageDTO.getPath())
                .uuid(imageDTO.getUuid())
                .build();
    }

    public static BoardImage imageDTOToBoardImage(ImageDTO imageDTO, Board board) {
        return BoardImage.builder()
                .board(board)
                .imgName(imageDTO.getImgName())
                .path(imageDTO.getPath())
                .uuid(imageDTO.getUuid())
                .build();
    }

    public static BoardReplyImage imageDTOToBoardReplyImage(ImageDTO imageDTO, BoardReply board) {
        return BoardReplyImage.builder()
                .boardReply(board)
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

    public static <T extends BaseImage> ImageDTO imageToImageDTO(T image) {
        return ImageDTO.builder()
                .imgName(image.getImgName())
                .uuid(image.getUuid())
                .path(image.getPath())
                .build();
    }
}
