package fr.fms.business;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.fms.entities.Address;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Film;
import fr.fms.entities.Session;

public interface IBusiness {
	public Cinema getCinemaById(long id) throws Exception;
	public Page<Cinema> getCinemasByCityPage(Long idCity, int page)  throws Exception;
	public Page<Cinema> getCinemasPage(String kw, int page)  throws Exception;
	public List<City> getCities()  throws Exception;
	public City saveCity(City city) throws Exception;
	public City getCity(long id) throws Exception;
	public Address saveAddress(Address address) throws Exception;
	public Address getAddress(long id) throws Exception;
	public Cinema saveCinema(Cinema cinema) throws Exception;
	public Cinema getCinema(long id) throws Exception;
	public Film saveFilm(Film film) throws Exception;
	public Film getFilm(long id) throws Exception;
	public Page<Film> getFilmsByCineId(long id, int page) throws Exception;
	public Session saveSession(Session session) throws Exception;
	public Session getSession(long id) throws Exception;
	
	//public <T> save(<T> t)throws Exception;
}
