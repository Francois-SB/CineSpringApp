package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Film;

public interface FilmRepository  extends JpaRepository<Film, Long> {

}
