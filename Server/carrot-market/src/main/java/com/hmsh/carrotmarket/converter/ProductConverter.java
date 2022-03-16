package com.hmsh.carrotmarket.converter;


import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.Product;

public class ProductConverter {

    public static Product dtoToEntity(ProductDTO productDTO) {
        return Product.builder()
                .title(productDTO.getTitle())
                .content(productDTO.getContent())
                .address(productDTO.getAddress())
                .views(0)
                .chatsCount(0)
                .member(Member.builder().phoneNumber(productDTO.getMemberDTO().getPhoneNumber()).build())
                .build();
    }

    public static ProductDTO entityToDTO(Product product) {
        return ProductDTO.builder()
                .title(product.getTitle())
                .content(product.getContent())
                .address(product.getAddress())
                .views(product.getViews())
                .chatsCount(product.getChatsCount())
                .memberDTO(MemberConverter.memberToMemberDTO(product.getMember()))
                .build();
    }
}
