package tn.esprit.timesheet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.repository.DepartementRepository;
import tn.esprit.timesheet.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
				Optional<Entreprise> oe = entrepriseRepoistory.findById(entrepriseId);
				Optional<Departement> od = deptRepoistory.findById(depId);
				if(oe.isPresent()&&od.isPresent()){
					Entreprise entrepriseManagedEntity =oe.get();
					Departement depManagedEntity = od.get();
					
					depManagedEntity.setEntreprise(entrepriseManagedEntity);
					deptRepoistory.save(depManagedEntity);
				}
			
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<Entreprise> oe = entrepriseRepoistory.findById(entrepriseId);
		List<String> depNames = new ArrayList<>();
		if(oe.isPresent()){
			Entreprise entrepriseManagedEntity = oe.get();
			
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			
			return depNames;
		}
		return depNames;
		}
		

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		Optional<Entreprise> oe = entrepriseRepoistory.findById(entrepriseId);
		if(oe.isPresent()){
			entrepriseRepoistory.delete(oe.get());	
		}
		
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		Optional<Departement> od = deptRepoistory.findById(depId) ; 
		if(od.isPresent()){
			deptRepoistory.delete(od.get());	
		}
		
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Optional<Entreprise> oe = entrepriseRepoistory.findById(entrepriseId);
		if(oe.isPresent()){
			return oe.get();	
		}
		return null ;
	}

}
