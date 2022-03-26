package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.enumeration.Address;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p.id, p.title, p.address, p.chats, p.modDate, p.price, p.likes, pi, p.tradeStatus " +
            "from Product p " +
            "left outer join ProductImage pi on p = pi.product " +
            "where p.address = :address " +
            "group by p")
    List<Object[]> getProductsListByAddress(Pageable pageable, Address address);

    @Query("select p.id, p.title, p.address, p.chats, p.modDate, p.price, p.likes, pi, p.tradeStatus " +
            "from Product p " +
            "left outer join ProductImage pi on p = pi.product " +
            "left outer join Likes l on p = l.product " +
            "where l.member = :member " +
            "group by p")
    List<Object[]> getProductsByLikes(Member member);

    @Query("select p.id, p.title, p.address, p.chats, p.modDate, p.price, p.likes, pi, p.tradeStatus " +
            "from Product p " +
            "left outer join ProductImage pi on p = pi.product " +
            "where p.member = :member " +
            "group by p")
    List<Object[]> getAllByMember(Member member);

}
