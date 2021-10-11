package tn.esprit.timesheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Role;
import tn.esprit.timesheet.repository.ContratRepository;
import tn.esprit.timesheet.repository.DepartementRepository;
import tn.esprit.timesheet.repository.EmployeRepository;
import tn.esprit.timesheet.services.IEmployeService;

@SpringBootTest
public class EmployeServiceTest {

	@Autowired
	IEmployeService es ;
	@Autowired
	EmployeRepository er;
	@Autowired
	DepartementRepository dr;
	@Autowired
	ContratRepository cr;
	@Test
	public void testAjouterEmploye()
	{

		es.ajouterEmploye(new Employe("john", "doe","john@doe.com", true, Role.CHEF_DEPARTEMENT));
		Employe e = er.findByEmail("john@doe.com");
		assertEquals(e.getEmail(),"john@doe.com");
		er.deleteById(e.getId());
		
	}
	@Test
	public void testMettreAjourEmailByEmployeId()
	{
		Employe e1=new Employe("nom", "prenom", "test@test.com",true,Role.ADMINISTRATEUR);
		es.ajouterEmploye(e1);
		es.mettreAjourEmailByEmployeId("email@test.tn",e1.getId());
		Employe e2=er.findByEmail("email@test.tn");
		assertEquals(e2.getEmail(),"email@test.tn");
		er.deleteById(e1.getId());
		
	}
	@Test
	public void testAffecterEmployeADepartement()
	{
		dr.save(new Departement("DEP20"));
		Departement d=dr.findByName("DEP20");
		es.ajouterEmploye(new Employe("first", "last","user@user.com", true, Role.CHEF_DEPARTEMENT));
		Employe e=er.findByEmail("user@user.com");
		es.affecterEmployeADepartement(e.getId(), d.getId());
		Departement d1=dr.findByName("DEP20");
		assertTrue(d1.getEmployes().contains(e));
		
		
	} 
	@Test
	public void testDeleteContratById()
	{
		cr.save(new Contrat(new Date("06/10/2021"),"CONTRACT", 1500));
		Contrat c = cr.findByTypeContrat("CONTRACT");
		cr.deleteById(c.getReference());
		assertEquals(0, cr.findAll().size());
		
	}
}
