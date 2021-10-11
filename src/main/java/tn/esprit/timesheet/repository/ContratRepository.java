package tn.esprit.timesheet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.timesheet.entities.Contrat;

public interface ContratRepository extends CrudRepository<Contrat, Integer>{

	public Contrat findByTypeContrat(String type);
	public List<Contrat> findAll();

} 
