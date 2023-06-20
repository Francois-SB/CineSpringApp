package fr.fms.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import fr.fms.entities.Film;

public interface FilmRepository  extends JpaRepository<Film, Long> {
	public Optional<Film> findById(Long cinemaId);
	public Page<Film> findAll(Pageable pageable);
	public Page<Film> findByCinemaId(Long cinemaId,Pageable pageable);
}
