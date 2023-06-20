package fr.fms.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Session;

public interface SessionRepository  extends JpaRepository<Session, Long> {
public Page<Session> findByFilmId(Long cinemaId,Pageable pageable);
public Optional<Session> findById(Long cinemaId);
}
