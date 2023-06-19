package fr.fms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Cinema implements Serializable{


		private static final long serialVersionUID = 1L;

		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		@NotNull
		@Size(min=2,max=30)
		private String name;

		@ManyToOne
		private Address address;//pourrais avour juste address car ville contenu ds address mais plus simple d'add vill aussi pour le trie
		
		@ManyToOne
		private City city;	
}
