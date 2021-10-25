package tn.esprit.timesheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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
	@Rollback
	public void testAjouterEmploye()
	{
		String email ="john@doe.com";
		es.ajouterEmploye(new Employe("john", "doe",email, true, Role.CHEF_DEPARTEMENT));
		Employe e = er.findByEmail(email);
		assertEquals(e.getEmail(),email);
		
		
	}
	@Test
	@Rollback
	public void testMettreAjourEmailByEmployeId()
	{
		String email = "email@test.tn";
		Employe e1=new Employe("nom", "prenom", email,true,Role.ADMINISTRATEUR);
		es.ajouterEmploye(e1);
		es.mettreAjourEmailByEmployeId(email,e1.getId());
		Employe e2=er.findByEmail(email);
		assertEquals(e2.getEmail(),email);
		
	}
	@Test
	@Rollback
	public void testAffecterEmployeADepartement()
	{
		 
		String depName="DEP66";
		dr.save(new Departement(depName));
		Departement d=dr.findByName(depName);
		es.ajouterEmploye(new Employe("new", "employe","new@employe.com", true, Role.CHEF_DEPARTEMENT));
		Employe e=er.findByEmail("new@employe.com");
		es.affecterEmployeADepartement(e.getId(), d.getId());
		d=dr.findByName(depName);
		assertTrue(d.getEmployes().contains(e));
		es.desaffecterEmployeDuDepartement(e.getId(), d.getId());
		
		
	} 
	 @Test
	    public void testEmployeActive() {

	    	// Type variable = value;
	    	// ClassName variableName = new ClassName();
	    	Employe emp = new Employe();
	    	
	    	// To use any method on the object: objectName.methodName();
	    	emp.setActif(true);
	    	
	    	boolean actualEmployeeMaritalStatus = emp.isActif();
	    	
	    	assertEquals(true, actualEmployeeMaritalStatus);
	    	
	    	
	    }
	@Test 
	public void testEmployeeName() {
		
		//create an instance of the Employee class
		Employe emp = new Employe();
		
		//Specify the employee name for the object
		emp.setNom("Femi");
		
		//Fetch the employee name from the object
		String employeeName = emp.getNom();
		
		
		//Verify that the retrieved value for the name is accurate
		assertEquals("kodo", employeeName);
	}
	@Test
	@Rollback
	public void testDeleteContratById()
	{ 
		cr.save(new Contrat(LocalDate.of(2021, 10, 06),"CONTRACT", 1500));
		Contrat c = cr.findByTypeContrat("CONTRACT");
		cr.deleteById(c.getReference());
		assertEquals(0, cr.findAll().size());
		
	}
}
