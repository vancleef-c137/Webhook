package tn.esprit.timesheet;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.repository.DepartementRepository;
import tn.esprit.timesheet.repository.EntrepriseRepository;
import tn.esprit.timesheet.services.IEntrepriseService;

@SpringBootTest
public class EntrepriseServiceTest {

	@Autowired
	DepartementRepository dr;
	@Autowired
	EntrepriseRepository er;
	@Autowired
	IEntrepriseService es;

	@Test
	void testAffecterDepartementAEntreprise() {
		dr.save(new Departement("TIC"));
		er.save(new Entreprise("Esprit", "123456"));
		Departement d = dr.findByName("TIC");
		Entreprise e = er.findByName("Esprit");
		es.affecterDepartementAEntreprise(d.getId(), e.getId());
		assertTrue(e.getDepartements().contains(d));
	}

	@Test
	void testGetEntrepriseById() {
		er.save(new Entreprise("ENT", "0000000"));
		Entreprise e = er.findByName("ENT");
		Entreprise e2 = es.getEntrepriseById(e.getId());
		assertEquals(e.getId(), e2.getId());
		er.deleteById(e.getId());

	}

	void testDeleteEntrepriseById() {
		er.save(new Entreprise("Apple", "321654"));
		Entreprise e = er.findByName("Apple");
		long size = er.findAll().size();
		es.deleteEntrepriseById(e.getId());
		assertEquals(size - 1, er.findAll().size());
	} 

}
