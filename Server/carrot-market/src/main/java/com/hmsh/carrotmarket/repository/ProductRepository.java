package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p.id, p.title, p.address, p.chats, p.modDate, p.price, p.likes " +
            "from Product p where p.address=:address")
    List<Object[]> getListByAddress(Pageable pageable, String address);

}
