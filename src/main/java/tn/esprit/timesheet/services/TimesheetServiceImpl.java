package tn.esprit.timesheet.services;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.entities.Role;
import tn.esprit.timesheet.entities.Timesheet;
import tn.esprit.timesheet.entities.TimesheetPK;
import tn.esprit.timesheet.repository.DepartementRepository;
import tn.esprit.timesheet.repository.EmployeRepository;
import tn.esprit.timesheet.repository.MissionRepository;
import tn.esprit.timesheet.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	public int ajouterMission(Mission mission) {
		missionRepository.save(mission);
		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		Optional<Mission> om = missionRepository.findById(missionId);
		Optional<Departement> od=deptRepoistory.findById(depId);
		if(om.isPresent()&&od.isPresent()){
			Mission mission = om.get();
			Departement dep = od.get();
			mission.setDepartement(dep);
			missionRepository.save(mission);
		}
		
	
		
	}

	public void ajouterTimesheet(int missionId, int employeId, LocalDate dateDebut, LocalDate dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		
	}

	
	public void validerTimesheet(int missionId, int employeId, LocalDate dateDebut, LocalDate dateFin, int validateurId) {
		
		
		Optional<Employe> oe =  employeRepository.findById(validateurId);
		Optional<Mission> om = missionRepository.findById(missionId);
		if(oe.isPresent()&&om.isPresent()){
			Employe validateur =oe.get();
			Mission mission = om.get();
			//verifier s'il est un chef de departement (interet des enum)
			if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
				return;
			}
			//verifier s'il est le chef de departement de la mission en question
			
			for(Departement dep : validateur.getDepartements()){
				if(dep.getId() == mission.getDepartement().getId()){
					break;
				}
			}

	//
			TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
			Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
			timesheet.setValide(true);
		}
		
		

		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
