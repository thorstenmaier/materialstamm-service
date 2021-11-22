package com.trivadis.materialstammdatenservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trivadis.materialstammdatenservice.domain.Materialstamm;

@RestController
public class MaterialstammController {

	private List<Materialstamm> materialstammListe = new ArrayList<>();
	
	@GetMapping("/materialstamm")
	public List<Materialstamm> materialstammAuslesen() {
		return materialstammListe;
	}
	
	// http://localhost:8080/materialstamm/1
	@GetMapping("/materialstamm/{id}")
	public Materialstamm materialstammPerIdAuslesen(@PathVariable("id") Long id) {
		return materialstammListe.stream().filter(m -> m.getId() == id).findFirst().get();
	}
	
	@PostMapping("/materialstamm")
	public Materialstamm neuenMaterialstammAnlegen(@RequestBody Materialstamm materialstamm) {
		materialstammListe.add(materialstamm);
 		return materialstamm;
	}
	
	@PutMapping("/materialstamm/{id}")
	public ResponseEntity<Materialstamm> bestehendenMaterialstammaktualsieren(@PathVariable("id") Long id, @RequestBody Materialstamm materialstamm) {
		Materialstamm bestehenderMaterialstamm = materialstammPerIdAuslesen(id);
		if (bestehenderMaterialstamm == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		bestehenderMaterialstamm.setBezeichnung(materialstamm.getBezeichnung());
		return ResponseEntity.ok(bestehenderMaterialstamm);
	}
	
	@DeleteMapping("/materialstamm/{id}")
	public ResponseEntity<?> materialstammLoeschen(@PathVariable("id") Long id) {
		Iterator<Materialstamm> iter = materialstammListe.iterator();
		while(iter.hasNext()) {
			Materialstamm next = iter.next();
			if (next.getId() == id) {
				iter.remove();
			}
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
