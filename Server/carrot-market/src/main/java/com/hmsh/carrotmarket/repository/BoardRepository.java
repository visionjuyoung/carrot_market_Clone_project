package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
