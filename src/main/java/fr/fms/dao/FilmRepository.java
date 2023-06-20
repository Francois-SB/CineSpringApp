package fr.fms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import fr.fms.entities.Film;

public interface FilmRepository  extends JpaRepository<Film, Long> {
	public Page<Film> findByCinemaId(Long cinemaId , Pageable pageable);
}
