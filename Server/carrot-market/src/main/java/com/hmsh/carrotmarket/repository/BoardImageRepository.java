package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Board;
import com.hmsh.carrotmarket.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

    List<BoardImage> findAllByBoard(Board board);

    void deleteAllByBoard(Board board);
}
