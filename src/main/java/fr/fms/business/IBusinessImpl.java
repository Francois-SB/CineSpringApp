package fr.fms.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	AddressRepository addressRepository;
	
	@Autowired
	FilmRepository filmRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	
	
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
		return addressRepository.save(address);
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
		return sessionRepository.save(session);
	}
	@Override
	public City getCity(long id) throws Exception {

		return cityRepository.findById(id).get();
	}
	@Override
	public Address getAddress(long id) throws Exception {
		return addressRepository.findById(id).get();
	}
	@Override
	public Cinema getCinema(long id) throws Exception {
		return cinemaRepository.findById(id).get();
	}
	@Override
	public Film getFilm(long id) throws Exception {
		Optional<Film> optional = filmRepository.findById(id);
		return optional.isPresent()? optional.get() : null;
	}
	@Override
	public Session getSession(long id) throws Exception {
		Optional<Session> optional = sessionRepository.findById(id);
		return optional.isPresent()? optional.get() : null;
	}
	@Override
	public Cinema getCinemaById(long id) throws Exception {
		Optional<Cinema> cine = cinemaRepository.findById(id);
		return cine.isPresent()? cine.get():null;
	}
	@Override
	public Page<Film> getFilmsByCineId(long id, int page) throws Exception {
		
		return filmRepository.findByCinemaId(id, PageRequest.of(page, 5));
	}
	@Override
	public Page<Session> getSessionsByFilmId(long idFilm, int page) throws Exception{
		return sessionRepository.findByFilmId(idFilm, PageRequest.of(page, 5));
	}
	@Override
	public List<Address> getAddresses() throws Exception {
		return addressRepository.findAll();
	}
	@Override
	public void deleteCinemaById(Long id) {
		cinemaRepository.deleteById(id);
		
	}
	@Override
	public List<Cinema> getCinemas() {
		return cinemaRepository.findAll();
	}
	@Override
	public void deleteFilmById(Long id) {
		filmRepository.deleteById(id);
		
	}
	@Override
	public Page<Film> getAllFilms(int page) {
		return filmRepository.findAll(PageRequest.of(page, 5));
	}
	



}
