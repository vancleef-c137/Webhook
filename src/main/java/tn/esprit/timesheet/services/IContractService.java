package tn.esprit.timesheet.services;

import tn.esprit.timesheet.entities.Contrat;


public interface IContractService {
	

	public int ajouterContrat(Contrat contrat);
	public void affecterContratAEmploye(int contratId, int employeId);
	public void deleteContratById(int contratId);
	public void deleteAllContratJPQL();
	
	
}
