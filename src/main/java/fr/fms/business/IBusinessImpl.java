package fr.fms.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import fr.fms.dao.AddressRepository;
import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.FilmRepository;
import fr.fms.dao.SessionRepository;
import fr.fms.entities.Address;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Film;
import fr.fms.entities.Session;

@Service
public class IBusinessImpl implements IBusiness {

	@Autowired
	CinemaRepository cinemaRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	AddressRepository addressRipository;
	
	@Autowired
	FilmRepository filmRepository;
	
	@Autowired
	SessionRepository sessionRipository;
	
	
	//cinema
	@Override
	public Page<Cinema> getCinemasByCityPage(Long idCity, int page)  throws Exception {
		return cinemaRepository.findByCityId(idCity, PageRequest.of(page, 5));
	}
	@Override
	public Page<Cinema> getCinemasPage(String kw, int page)  throws Exception {
		
		return cinemaRepository.findByNameContains(kw , PageRequest.of(page, 5));
	}
	
	//City
	@Override
	public List<City> getCities()  throws Exception {
		return cityRepository.findAll();
	}
	@Override
	public City saveCity(City city) throws Exception {
		return cityRepository.save(city);		
	}

	@Override
	public Address saveAddress(Address address) throws Exception {
		return addressRipository.save(address);
	}
	@Override
	public Cinema saveCinema(Cinema cinema) throws Exception {
		return cinemaRepository.save(cinema);
	}
	@Override
	public Film saveFilm(Film film) throws Exception {
		return filmRepository.save(film);
	}
	@Override
	public Session saveSession(Session session) throws Exception {
		return sessionRipository.save(session);
	}
	@Override
	public City getCity(long id) throws Exception {

		return cityRepository.findById(id).get();
	}
	@Override
	public Address getAddress(long id) throws Exception {
		return addressRipository.findById(id).get();
	}
	@Override
	public Cinema getCinema(long id) throws Exception {
		return cinemaRepository.findById(id).get();
	}
	@Override
	public Film getFilm(long id) throws Exception {
		return filmRepository.findById(id).get();
	}
	@Override
	public Session getSession(long id) throws Exception {
		return sessionRipository.findById(id).get();
	}
	



}