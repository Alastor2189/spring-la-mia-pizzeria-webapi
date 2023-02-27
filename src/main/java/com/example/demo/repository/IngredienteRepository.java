package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Ingrediente;

public interface IngredienteRepository  extends JpaRepository<Ingrediente, Long>{

}
