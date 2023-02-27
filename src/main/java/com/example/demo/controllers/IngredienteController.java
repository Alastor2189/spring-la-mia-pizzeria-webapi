package com.example.demo.controllers;

import com.example.demo.models.Ingrediente;
import com.example.demo.models.Pizza;
import com.example.demo.repository.IngredienteRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredienti")

public class IngredienteController {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@GetMapping()
	public String index(Model model) {	

		List<Ingrediente> ingredienti;

		ingredienti = ingredienteRepository.findAll(Sort.by("name"));

		
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		Ingrediente ingrediente = new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);

		return "ingredienti/create";
	}

	@PostMapping("/store")
	public String store(
			@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "/ingredienti/edit";
		}

		ingredienteRepository.save(formIngrediente);

		return "redirect:/ingredienti";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = ingredienteRepository.getReferenceById(id);
		model.addAttribute("ingrediente", ingrediente);

		return "ingredienti/edit";
	}

	@PostMapping("/update/{id}")
	public String update(
		@ModelAttribute Ingrediente formIngrediente,
		Model model) {

		ingredienteRepository.save(formIngrediente);

		return "redirect:/ingredienti";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {

		ingredienteRepository.deleteById(id);

		return "redirect:/ingredienti";
	}

}
