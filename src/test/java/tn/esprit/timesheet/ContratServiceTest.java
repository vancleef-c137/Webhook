package tn.esprit.timesheet;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.repository.ContratRepository;
import tn.esprit.timesheet.services.IContractService;

@SpringBootTest
public class ContratServiceTest {
	
	private static final String TESTSTRADD = "tcTestAdd";  
	private static final String TESTSTRDEL = "tcTestDelete";  
	
	@Autowired
	IContractService cs;
	
	@Autowired
	ContratRepository crep;
	
	@Test
	public void testAjoutContrat(){
		Contrat c = new Contrat(Calendar.getInstance(), TESTSTRADD, 1000.0f);
		cs.ajouterContrat(c);
		Contrat c2 = crep.findByTypeContrat(TESTSTRADD);
		crep.delete(c2);
		assertTrue(c.getSalaire() == c2.getSalaire());
	}
	
	@Test
	public void testDeleteContrat(){
		Contrat c = new Contrat(Calendar.getInstance(), TESTSTRDEL, 1000.0f);
		cs.ajouterContrat(c);
		Contrat c2 = crep.findByTypeContrat(TESTSTRDEL);
		assertTrue(c2.getTypeContrat().equals(TESTSTRDEL));
		crep.delete(c2);
		Contrat c3 = crep.findByTypeContrat(TESTSTRDEL);
		assertNull(c3);
	}

	
	//affecterDepartementAEntreprise
}
