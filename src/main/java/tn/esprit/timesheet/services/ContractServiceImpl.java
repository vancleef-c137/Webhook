package tn.esprit.timesheet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.repository.ContratRepository;
import tn.esprit.timesheet.repository.EmployeRepository;

@Service
public class ContractServiceImpl implements IContractService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	ContratRepository contratRepoistory;
	
	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Optional<Contrat> oc = contratRepoistory.findById(contratId);
		Optional<Employe> oe =  employeRepository.findById(employeId);
		if(oc.isPresent()&&oe.isPresent()){
			Contrat contratManagedEntity = oc.get();
			Employe employeManagedEntity =oe.get();
			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
		}
	}
	
	public void deleteContratById(int contratId) {
		Optional<Contrat> oc=contratRepoistory.findById(contratId);
		if(oc.isPresent()){
			Contrat contratManagedEntity = oc.get();
			contratRepoistory.delete(contratManagedEntity);
		}
	}

	public void deleteAllContratJPQL() {
        employeRepository.deleteAllContratJPQL();
	}
	
}
