package tn.esprit.timesheet.services;

import java.time.LocalDate;
import java.util.List;

import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Mission;



public interface ITimesheetService {
	
	public int ajouterMission(Mission mission);
	public void affecterMissionADepartement(int missionId, int depId);
	public void ajouterTimesheet(int missionId, int employeId, LocalDate dateDebut, LocalDate dateFin);
	public void validerTimesheet(int missionId, int employeId, LocalDate dateDebut, LocalDate dateFin, int validateurId);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
}
