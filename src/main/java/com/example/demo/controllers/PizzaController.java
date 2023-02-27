package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import com.example.demo.models.Ingrediente;
import com.example.demo.repository.IngredienteRepository;
import com.example.demo.models.Pizza;
import com.example.demo.repository.PizzaRepository;

@Controller
@RequestMapping("/pizze")


public class PizzaController {
		
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Autowired 
	private IngredienteRepository ingredienteRepository;
	
	

	@GetMapping
	public String index(
			@RequestParam(name = "keyword", required = false) String keyword, 
			Model model) {	

		List<Pizza> listPizze;

		if (keyword!=null && !keyword.isEmpty()) {
			listPizze = pizzaRepository.findByNameLike(keyword + "%");
		} else {
			listPizze = pizzaRepository.findAll(Sort.by("name"));
		}
		model.addAttribute("pizze", listPizze);
		return "pizze/index";
	}
	
	@GetMapping("/{id}") 
	public String show(@PathVariable("id") Integer id, Model model) {
		Optional<Pizza> pizza = pizzaRepository.findById(id);
		Pizza pizza2 = pizza.get();
		if (pizza.isEmpty()) {
			return "pizze/notfound";
		} else {
			model.addAttribute("pizza", pizza2);
		}
		return "pizze/detail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List<Ingrediente> ingredienti = ingredienteRepository.findAll();
		model.addAttribute("ingredienti", ingredienti);

		pizza.setImmagine("");

		return "pizze/create";
	}

	@PostMapping("/store")
	public String store(
			@Valid @ModelAttribute("pizza") Pizza formPizza,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "/pizze/create";
		}
		
			pizzaRepository.save(formPizza);
		
		return "redirect:/pizze";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Pizza pizza = pizzaRepository.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		
		List<Ingrediente> ingredienti = ingredienteRepository.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "pizze/edit";
	}

	@PostMapping("/update/{id}")
	public String update(
			@Valid @ModelAttribute Pizza formPizza,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "pizze/edit";
		}

		pizzaRepository.save(formPizza);

		return "redirect:/pizze/" + formPizza.getId();
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		pizzaRepository.deleteById(id);

		return "redirect:/pizze";
	}
}
