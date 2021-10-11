package tn.esprit.timesheet.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.timesheet.entities.Mission;


public interface MissionRepository extends CrudRepository<Mission, Integer> {

	Mission findByName(String name);

}
