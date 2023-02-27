package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name= "offerte")
public class Offerta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@Size(min=5, max=100)
	private String name;
	
	@NotNull
	private LocalDate inizioOfferta;
	
	@NotNull
	private LocalDate termineOfferta;
	
	
	@JsonBackReference
	@NotNull
	@ManyToOne
	private Pizza pizza;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getInizioOfferta() {
		return inizioOfferta;
	}

	public void setInizioOfferta(LocalDate inizioOfferta) {
		this.inizioOfferta = inizioOfferta;
	}

	public LocalDate getTermineOfferta() {
		return termineOfferta;
	}

	public void setTermineOfferta(LocalDate termineOfferta) {
		this.termineOfferta = termineOfferta;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public String toString() {
		return "Offerta [id=" + id + ", name=" + name + ", inizioOfferta=" + inizioOfferta + ", termineOfferta="
				+ termineOfferta + ", pizza=" + pizza + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getInizioOfferta()=" + getInizioOfferta() + ", getTermineOfferta()=" + getTermineOfferta()
				+ ", getPizza()=" + getPizza() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	

	
	
	
	
}
