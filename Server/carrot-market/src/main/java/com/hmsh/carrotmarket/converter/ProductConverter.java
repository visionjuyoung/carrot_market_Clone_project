package com.hmsh.carrotmarket.converter;


import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.Product;

import java.time.LocalDateTime;

public class ProductConverter {

    public static Product dtoToEntity(ProductDTO productDTO) {
        return Product.builder()
                .title(productDTO.getTitle())
                .content(productDTO.getContent())
                .address(productDTO.getAddress())
                .price(productDTO.getPrice())
                .likes(0)
                .views(0)
                .chats(0)
                .member(Member.builder().phoneNumber(productDTO.getPhoneNumber()).build())
                .build();
    }

    public static ProductDTO entityToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .content(product.getContent())
                .address(product.getAddress())
                .views(product.getViews())
                .chats(product.getChats())
                .likes(product.getLikes())
                .modDate(product.getModDate())
                .memberDTO(MemberConverter.memberToMemberDTO(product.getMember()))
                .build();
    }

    public static ProductListDTO entityToListDTO(Object[] objects) {
        return ProductListDTO.builder()
                .id((Long) objects[0])
                .title((String) objects[1])
                .address((String) objects[2])
                .chats((int) objects[3])
                .modDate((LocalDateTime) objects[4])
                .price((int) objects[5])
                .likes((int) objects[6])
                .build();
    }
}
