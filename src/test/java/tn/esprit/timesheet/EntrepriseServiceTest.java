package tn.esprit.timesheet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.repository.EntrepriseRepository;
import tn.esprit.timesheet.services.IEntrepriseService;

@SpringBootTest
public class EntrepriseServiceTest {
	
	@Autowired
	IEntrepriseService ens;
	
	@Autowired
	EntrepriseRepository enrep;
	
	@Test
	public void testAjoutEntreprise(){
		Entreprise en = new Entreprise("Testname", "raisonSocial");
		ens.ajouterEntreprise(en);
		Entreprise en1 = enrep.findByName("Testname");
		assert en1.getName().equals(en.getName()) && en1.getRaisonSocial().equals(en.getRaisonSocial());
		enrep.deleteById(en1.getId());
	}

	
	//affecterDepartementAEntreprise
}
