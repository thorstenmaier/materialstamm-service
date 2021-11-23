package com.trivadis.materialstammdatenservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trivadis.materialstammdatenservice.domain.Materialstamm;
import com.trivadis.materialstammdatenservice.repository.MaterialstammRepository;

@RestController
public class MaterialstammController {

	@Autowired
	private MaterialstammRepository materialstammRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("/materialstamm")
	public List<Materialstamm> materialstammAuslesen() {
		return materialstammRepository.findAll();
	}

	// http://localhost:8080/materialstamm/1
	@GetMapping("/materialstamm/{id}")
	public Materialstamm materialstammPerIdAuslesen(@PathVariable("id") Long id) {
		return materialstammRepository.findById(id).orElse(null);
	}

	@PostMapping("/materialstamm")
	public Materialstamm neuenMaterialstammAnlegen(@RequestBody Materialstamm materialstamm) {
		Materialstamm db = materialstammRepository.save(materialstamm);
		jmsTemplate.convertAndSend("materialstamm", db.getId());
		return db;
	}

	@PutMapping("/materialstamm/{id}")
	public ResponseEntity<Materialstamm> bestehendenMaterialstammaktualsieren(@PathVariable("id") Long id,
			@RequestBody Materialstamm materialstamm) {
		Materialstamm bestehenderMaterialstamm = materialstammRepository.findById(id).orElse(null);
		if (bestehenderMaterialstamm == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		bestehenderMaterialstamm.setBezeichnung(materialstamm.getBezeichnung());
		return ResponseEntity.ok(materialstammRepository.save(bestehenderMaterialstamm));
	}

	@DeleteMapping("/materialstamm/{id}")
	public ResponseEntity<?> materialstammLoeschen(@PathVariable("id") Long id) {
		materialstammRepository.deleteById(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
