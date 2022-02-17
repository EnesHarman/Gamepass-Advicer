package com.gamepass.advicer.repos;

import com.gamepass.advicer.entity.concretes.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre,Long> {
}
