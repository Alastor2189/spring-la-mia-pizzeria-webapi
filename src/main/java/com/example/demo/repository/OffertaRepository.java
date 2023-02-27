package com.example.demo.repository;

import com.example.demo.models.Offerta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OffertaRepository extends JpaRepository<Offerta, Integer>{

	Offerta getReferenceById(Long id);

	
}
