package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Offerta;
import com.example.demo.models.Pizza;
import com.example.demo.repository.OffertaRepository;
import com.example.demo.repository.PizzaRepository;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
	@Autowired
	OffertaRepository offertaRepository;
	@Autowired
	PizzaRepository pizzaRepository;

	@GetMapping
	public String index(Model model) {
		List<Offerta> listaOfferte = offertaRepository.findAll();
		model.addAttribute("offerte", listaOfferte);
		return "/offerte/index";
	}

	@GetMapping("/create")
	public String create(@RequestParam(name = "idPizza", required = true) Integer id, Model model) throws Exception {
		Offerta offerta = new Offerta();
		Pizza pizza = pizzaRepository.getReferenceById(id);
		offerta.setPizza(pizza);
		model.addAttribute("offerta", offerta);
		return "/offerte/create";
	}

	@PostMapping("/store")
	public String store(@ModelAttribute("offerta") Offerta formOfferta, Model model) {

		//Pizza pizza = formOfferta.getPizza();
		offertaRepository.save(formOfferta);

		return "redirect:/pizze" + formOfferta.getPizza().getId();

	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Offerta offerta = offertaRepository.getReferenceById(id);
		model.addAttribute("offerta", offerta);

		return "offers/edit";
	}

	@PostMapping("/update/{id}")
	public String update(
		@ModelAttribute Offerta formOffer,
		Model model) {

		offertaRepository.save(formOffer);

		return "redirect:/pizze/" + formOffer.getPizza().getId();
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		Integer pizzaId = offertaRepository.getReferenceById(id).getPizza().getId();

		offertaRepository.deleteById(id);

		return "redirect:/pizze/" + pizzaId;
	}

}
