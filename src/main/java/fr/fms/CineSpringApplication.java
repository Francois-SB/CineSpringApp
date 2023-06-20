package fr.fms;

import java.sql.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Address;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Film;
import fr.fms.entities.Session;

@SpringBootApplication
public class CineSpringApplication implements CommandLineRunner {

	@Autowired
	IBusinessImpl iBusinessImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(CineSpringApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		generateCity();
//		generateAddress();
//		generateCinema();
//		generateFilm();
//		generateSession();
	}
	private void generateSession() {
		for(int i=1; i<=216;i++) {
			try {
				Session session = iBusinessImpl.saveSession(new Session(null, new Date(2023, ((i*2)%12)+1, ((i*5)%31)+1), iBusinessImpl.getFilm(((i)%36)+1)));
			} catch (Exception e) {
				// TODO code ascii + % + verif + regle pour mot
				e.printStackTrace();
			}
		}
		
	}
	private void generateFilm() {
		for(int i=1;i<=36;i++) {
		try {
			Film film = iBusinessImpl.saveFilm(new Film(null,"Film n "+Integer.toString(((i)%12)+1),"desc resummé",iBusinessImpl.getCinema(((i)%12)+1),null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	private void addFilmCineId() {
		
		try {
			for(int i=1;i<=108;i++) {
				Film film = iBusinessImpl.getFilm(i);
				film.setCinema(iBusinessImpl.getCinema(((i*3)%12)+1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void generateAddress() {
		try {
			Address address = iBusinessImpl.saveAddress(new Address(null,"rue Leon",iBusinessImpl.getCity(1L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue Armand",iBusinessImpl.getCity(2L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue Napoleon",iBusinessImpl.getCity(3L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue Machu picchu",iBusinessImpl.getCity(1L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue Atila",iBusinessImpl.getCity(2L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue Khan",iBusinessImpl.getCity(3L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue Lucien",iBusinessImpl.getCity(1L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue des Pyrénées",iBusinessImpl.getCity(2L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue des Alpes",iBusinessImpl.getCity(3L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue du Jura",iBusinessImpl.getCity(1L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue des spiritueux",iBusinessImpl.getCity(2L),null));
			address = iBusinessImpl.saveAddress(new Address(null,"rue Sherlock",iBusinessImpl.getCity(3L),null));
			
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void generateCinema() {
		try {
			Cinema cinema = iBusinessImpl.saveCinema(new Cinema(null,"La lucarne",iBusinessImpl.getAddress(1),iBusinessImpl.getAddress(1).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"Belle vue",iBusinessImpl.getAddress(2),iBusinessImpl.getAddress(2).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"La bobine",iBusinessImpl.getAddress(3),iBusinessImpl.getAddress(3).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"L'acteur",iBusinessImpl.getAddress(4),iBusinessImpl.getAddress(4).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"Renouveau",iBusinessImpl.getAddress(5),iBusinessImpl.getAddress(5).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"7eme art",iBusinessImpl.getAddress(6),iBusinessImpl.getAddress(6).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"La chevillette",iBusinessImpl.getAddress(7),iBusinessImpl.getAddress(7).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"Le flingueur",iBusinessImpl.getAddress(8),iBusinessImpl.getAddress(8).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"La rose",iBusinessImpl.getAddress(9),iBusinessImpl.getAddress(9).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"Le voltigeur",iBusinessImpl.getAddress(10),iBusinessImpl.getAddress(10).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"La cinema",iBusinessImpl.getAddress(11),iBusinessImpl.getAddress(11).getCity()) );
			cinema = iBusinessImpl.saveCinema(new Cinema(null,"Oui",iBusinessImpl.getAddress(12),iBusinessImpl.getAddress(12).getCity()) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void generateCity() {
		try {
			City city = iBusinessImpl.saveCity(new City(null,"Bordeaux",22222,"France",null,null));
			city = iBusinessImpl.saveCity(new City(null,"Paris",33333,"France",null,null));
			city = iBusinessImpl.saveCity(new City(null,"Lille",44444,"France",null,null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
