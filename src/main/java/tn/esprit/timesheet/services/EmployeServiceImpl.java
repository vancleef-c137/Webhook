package tn.esprit.timesheet.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.entities.Timesheet;
import tn.esprit.timesheet.repository.ContratRepository;
import tn.esprit.timesheet.repository.DepartementRepository;
import tn.esprit.timesheet.repository.EmployeRepository;
import tn.esprit.timesheet.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Optional<Employe> oe = employeRepository.findById(employeId);
		if(oe.isPresent()){
			Employe employe = oe.get();
			employe.setEmail(email);
			employeRepository.save(employe);
		}
		

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Optional<Departement> odep=deptRepoistory.findById(depId);
		Optional<Employe> oemp=employeRepository.findById(employeId);
		if(odep.isPresent()&&oemp.isPresent()){
			Departement depManagedEntity =odep.get();
			Employe employeManagedEntity = oemp.get();

			if(depManagedEntity.getEmployes() == null){

				List<Employe> employes = new ArrayList<>();
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
			}else{

				depManagedEntity.getEmployes().add(employeManagedEntity);

			}
		}
	

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Optional<Departement>  odep = deptRepoistory.findById(depId);
		Optional<Employe> oe = employeRepository.findById(employeId);
		if(odep.isPresent()&&oe.isPresent()){
			Departement dep = odep.get();
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;
			}
		}
		Employe e = oe.get();
		int depNb = e.getDepartements().size();
		for(int index = 0; index < depNb; index++){
			if(e.getDepartements().get(index).getId() == depId){
				e.getDepartements().remove(index);
				break;
			}
		}
		}
		


		
	}

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

	public String getEmployePrenomById(int employeId) {
		Optional<Employe> oe = employeRepository.findById(employeId);
		if(oe.isPresent())
		{
			Employe employeManagedEntity = oe.get();
			return employeManagedEntity.getPrenom();
			
		}
		return "not found" ;
		
	}
	public void deleteEmployeById(int employeId)
	{
		Optional<Employe> oe=employeRepository.findById(employeId);
		if(oe.isPresent()){
			Employe employe = oe.get();

			for(Departement dep : employe.getDepartements()){
				dep.getEmployes().remove(employe);
			}

			employeRepository.delete(employe);
		}
		
	}

	public void deleteContratById(int contratId) {
		Optional<Contrat> oc=contratRepoistory.findById(contratId);
		if(oc.isPresent()){
			Contrat contratManagedEntity = oc.get();
			contratRepoistory.delete(contratManagedEntity);
		}
		
		

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
				return (List<Employe>) employeRepository.findAll();
	}

}
