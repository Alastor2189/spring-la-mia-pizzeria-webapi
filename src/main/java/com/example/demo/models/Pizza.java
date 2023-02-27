package com.example.demo.models;

import java.util.List;
import java.math.BigDecimal;



import ch.qos.logback.core.subst.Token.Type;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "pizza")

public class Pizza {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private Integer id;
		@NotNull(message = "Il campo Nome deve essere compilato")
		@NotEmpty(message = "Il campo Nome deve essere compilato")
		@Size(max = 30, message = "Inserire massimo 30 caratteri")
		
		private String nome;
		@NotNull(message = "Il campo Descrizione deve essere compilato")
		@NotEmpty(message = "Il campo Descrizione deve essere compilato")
		@Size(max = 100, message = "Inserire massimo 100 caratteri")
		
		private String descrizione;
		@NotNull(message = "Il campo Immagine deve essere compilato")
		@NotEmpty(message = "Il campo Immagine deve essere compilato")
		@Size(max = 200, message = "Inserire massimo 200 caratteri")
		
		private String immagine;
		private BigDecimal prezzo;
		@NotNull(message = "Il campo Prezzo deve essere compilato")
		@DecimalMax(value = "99.99", message = "Il prezzo massimo è €99.99")
		@DecimalMin(value = "1.00", message = "Il prezzo minimo è €1")
		
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getDescrizione() {
			return descrizione;
		}
		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}
		public String getImmagine() {
			return immagine;
		}
		public void setImmagine(String immagine) {
			this.immagine = immagine;
		}
		public BigDecimal getPrezzo() {
			return prezzo;
		}
		public void setPrezzo(BigDecimal prezzo) {
			this.prezzo = prezzo;
		}
		
		@ManyToMany
		List<Ingrediente> ingredienti;
		
		@OneToMany(mappedBy = "pizza", cascade=CascadeType.ALL)
		private List<Offerta> offerteList;


		public List<Offerta> getOfferteList() {
			return offerteList;
		}

		public void setOfferteList(List<Offerta> offerteList) {
			this.offerteList = offerteList;
		}
		
		public List<Ingrediente> getIngredienti() {
			return ingredienti;
		}

		public void setIngredienti(List<Ingrediente> ingredienti) {
			this.ingredienti = ingredienti;
		}
}
