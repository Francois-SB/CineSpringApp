package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Address;


public interface AddressRepository  extends JpaRepository<Address, Long> {

}
