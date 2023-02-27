package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Pizza;
import com.example.demo.repository.PizzaRepository;


@RestController
@CrossOrigin
@RequestMapping("/api/pizze")
public class PizzaApiController {

	@Autowired
	PizzaRepository pizzaRepository;


	//READ
	@GetMapping
	public List<Pizza> index(@RequestParam(name="nome", required = false) String keyword){
		List<Pizza> pizze;

		if ( keyword != null && !keyword.isEmpty() ) {
			pizze= pizzaRepository.findByNomeLike("%" + keyword + "%");
		} else {
			pizze = pizzaRepository.findAll();
		}

		return pizze;
	}


	//READ
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getById(@PathVariable("id") Integer id){
		Optional<Pizza> pizza = pizzaRepository.findById(id);
		if(pizza.isPresent()) {
			return new ResponseEntity<Pizza>(pizza.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
		}
	}


	//CREATE
	@PostMapping("/create")
	public Pizza create(@RequestBody Pizza pizza) {
		return pizzaRepository.save(pizza);
	}


	//UPDATE
	@PutMapping("{id}")
	public Pizza update(@RequestBody Pizza pizza, @PathVariable("id") Integer id) {
		pizza.setId(id);
		return pizzaRepository.save(pizza);
	}


	//DELETE
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		pizzaRepository.deleteById(id);
	}



}
