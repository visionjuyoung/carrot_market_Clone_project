package com.hmsh.carrotmarket.converter;


import com.hmsh.carrotmarket.dto.ImageDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.entity.ProductImage;
import com.hmsh.carrotmarket.enumeration.Address;
import com.hmsh.carrotmarket.enumeration.TradeStatus;
import com.querydsl.core.Tuple;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductConverter {

    public static Product dtoToEntity(ProductDTO productDTO) {
        return Product.builder()
                .title(productDTO.getTitle())
                .content(productDTO.getContent())
                .address(Address.getByRegion(productDTO.getAddress()))
                .price(productDTO.getPrice())
                .likes(0)
                .views(0)
                .chats(0)
                .likes(0)
                .tradeStatus(TradeStatus.BEFORE_RESERVATION)
                .member(Member.builder().phoneNumber(productDTO.getPhoneNumber()).build())
                .build();
    }

    public static ProductDTO entityToDTO(Product product, List<String> imagePathList, Boolean isLike) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .content(product.getContent())
                .address(product.getAddress().getRegion())
                .price(product.getPrice())
                .views(product.getViews())
                .chats(product.getChats())
                .likes(product.getLikes())
                .imagePathList(imagePathList)
                .modDate(product.getModDate())
                .tradeStatus(product.getTradeStatus())
                .isLike(isLike)
                .member(MemberConverter.memberToMemberDTO(product.getMember()))
                .build();
    }

    public static ProductListDTO entityToListDTO(Object[] objects) {
        String imageURL = null;
        ProductImage productImage = (ProductImage) objects[7];
        if (!Objects.isNull(productImage)) {
            ImageDTO imageDTO = ImageConverter.productImageToImageDTO(productImage);
            imageURL = imageDTO.getImageURL();
        }

        return ProductListDTO.builder()
                .id((Long) objects[0])
                .title((String) objects[1])
                .address(((Address) objects[2]).getRegion())
                .chats((int) objects[3])
                .modDate((LocalDateTime) objects[4])
                .price((int) objects[5])
                .likes((int) objects[6])
                .tradeStatus((TradeStatus) objects[8])
                .imagePath(imageURL)
                .build();
    }

    public static ProductListDTO tupleToListDTO(Tuple tuple) {
        String imageURL = null;
        ProductImage productImage = tuple.get(7, ProductImage.class);
        if (!Objects.isNull(productImage)) {
            ImageDTO imageDTO = ImageConverter.productImageToImageDTO(productImage);
            imageURL = imageDTO.getImageURL();
        }

        return ProductListDTO.builder()
                .id(tuple.get(0, Long.class))
                .title(tuple.get(1, String.class))
                .address((tuple.get(2, Address.class)).getRegion())
                .chats(tuple.get(3, Integer.class))
                .modDate(tuple.get(4, LocalDateTime.class))
                .price(tuple.get(5, Integer.class))
                .likes(tuple.get(6, Integer.class))
                .tradeStatus(tuple.get(8, TradeStatus.class))
                .imagePath(imageURL)
                .build();
    }
}
