package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p.id, p.title, p.address, p.chats, p.modDate, p.price, p.likes, pi " +
            "from Product p " +
            "left outer join ProductImage pi on p = pi.product " +
            "where p.address = :address " +
            "group by p")
    List<Object[]> getProductListByAddress(Pageable pageable, String address);

}
