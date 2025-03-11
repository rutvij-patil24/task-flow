package com.team15trello.team15trello.repositories;

import com.team15trello.team15trello.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
