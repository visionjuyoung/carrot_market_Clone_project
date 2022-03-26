package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.LikesDTO;
import com.hmsh.carrotmarket.entity.Likes;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;


    /**
     * 좋아요 등록
     * @param likesDTO 좋아요 등록 멤버와 상품 정보
     * @return 좋아요 등록 ID
     * @throws Exception 등록 에러
     */
    @Override
    public Long register(LikesDTO likesDTO) throws Exception {
        Likes likes = Likes.builder()
                .member(Member.builder().phoneNumber(likesDTO.getPhoneNumber()).build())
                .product(Product.builder().id(likesDTO.getProductId()).build())
                .build();

        Likes save = likesRepository.save(likes);

        return save.getId();
    }

    /**
     * 좋아요 삭제
     * @param likesDTO 좋아요 등록 멤버와 상품 정보
     */
    @Override
    @Transactional
    public void remove(LikesDTO likesDTO) {
        likesRepository.deleteLikesByMemberAndProduct(
                Member.builder().phoneNumber(likesDTO.getPhoneNumber()).build(),
                Product.builder().id(likesDTO.getProductId()).build()
        );
    }
}
