package tn.esprit.timesheet.services;

import java.util.Calendar;
import java.util.List;

import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Mission;



public interface ITimesheetService {
	
	public int ajouterMission(Mission mission);
	public void affecterMissionADepartement(int missionId, int depId);
	public void ajouterTimesheet(int missionId, int employeId, Calendar dateDebut, Calendar dateFin);
	public void validerTimesheet(int missionId, int employeId, Calendar dateDebut, Calendar dateFin, int validateurId);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
}
