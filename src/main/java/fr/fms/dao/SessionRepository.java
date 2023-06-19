package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Session;

public interface SessionRepository  extends JpaRepository<Session, Long> {

}
