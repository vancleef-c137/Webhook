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
	
	@Autowired
	IContractService cs;
	
	@Autowired
	ContratRepository crep;
	
	@Test
	public void testAjoutContrat(){
		Contrat c = new Contrat(Calendar.getInstance(), "tcTestAdd", 1000.0f);
		cs.ajouterContrat(c);
		Contrat c2 = crep.findByTypeContrat("tcTestAdd");
		crep.delete(c2);
		assertTrue(c.getSalaire() == c2.getSalaire());
	}
	
	@Test
	public void testDeleteContrat(){
		Contrat c = new Contrat(Calendar.getInstance(), "tcTestDelete", 1000.0f);
		cs.ajouterContrat(c);
		Contrat c2 = crep.findByTypeContrat("tcTestDelete");
		assertTrue(c2.getTypeContrat().equals("tcTestDelete"));
		crep.delete(c2);
		Contrat c3 = crep.findByTypeContrat("tcTestDelete");
		assertNull(c3);
	}

	
	//affecterDepartementAEntreprise
}
