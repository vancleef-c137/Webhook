package tn.esprit.timesheet;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.timesheet.entities.Contrat;
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
		String email ="john@doe.com";
		es.ajouterEmploye(new Employe("john", "doe",email, true, Role.CHEF_DEPARTEMENT));
		Employe e = er.findByEmail(email);
		assert e.getEmail().equals(email);
		er.deleteById(e.getId());

	}
	@Test
	public void testMettreAjourEmailByEmployeId()
	{
		String email = "email@test.tn";
		Employe e1=new Employe("nom", "prenom", email,true,Role.ADMINISTRATEUR);
		es.ajouterEmploye(e1);
		es.mettreAjourEmailByEmployeId(email,e1.getId());
		Employe e2=er.findByEmail(email);
		assert e2.getEmail().equals(email);
		er.deleteById(e2.getId());

	}

	 @Test
	    public void testEmployeActive() {

	    	Employe emp = new Employe();
	    	
	    	emp.setActif(true);
	    	
	    	boolean actualEmployeeMaritalStatus = emp.isActif();
	    	
	    	assert actualEmployeeMaritalStatus;
	    	
	    }

	@Test
	public void testDeleteContratById()
	{ 
		 Calendar date = Calendar.getInstance();
		    date.set(Calendar.YEAR, 2021);
		    date.set(Calendar.MONTH, 10);
		    date.set(Calendar.DAY_OF_MONTH, 10);
		cr.save(new Contrat(date,"CONTRACT", 1500));
		Contrat c = cr.findByTypeContrat("CONTRACT");
		cr.deleteById(c.getReference());
		assert (cr.findAll().isEmpty());
		
	}
}
