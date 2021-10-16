package tn.esprit.timesheet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.timesheet.entities.Entreprise;

public interface EntrepriseRepository extends CrudRepository<Entreprise, Integer>  {

	Entreprise findByName(String name);
	List<Entreprise> findAll();
	
	
}
