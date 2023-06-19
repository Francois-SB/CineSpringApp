package fr.fms.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Cinema;


public interface CinemaRepository extends JpaRepository<Cinema, Long> {
	public Page<Cinema> findByCityId(Long categoryId , Pageable pageable);
	public Page<Cinema> findByNameContains(String kw , Pageable pageable);
}
