package tn.esprit.timesheet.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.timesheet.entities.Departement;

public interface DepartementRepository extends CrudRepository<Departement, Integer>{

	public Departement findByName(String name);

}
