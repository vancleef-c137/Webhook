package tn.esprit.timesheet;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.entities.Role;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.repository.DepartementRepository;
import tn.esprit.timesheet.repository.EmployeRepository;
import tn.esprit.timesheet.repository.MissionRepository;
import tn.esprit.timesheet.repository.TimesheetRepository;
import tn.esprit.timesheet.services.ITimesheetService;

@SpringBootTest
public class TimesheetServiceTest {
	
	
	@Autowired
	DepartementRepository dr;
	@Autowired
	MissionRepository mr;
	@Autowired 
	ITimesheetService ts ;
	@Autowired
	EmployeRepository er;
	@Autowired
	TimesheetRepository tr;
	
	
	
	
	
	@Test
	void testAffecterMissionADepartement()
	{
		String mis = "MISSION3";
		mr.save(new Mission(mis, "DESC"));
		dr.save(new Departement("DEV"));
		Mission m = mr.findByName(mis);
		Departement d = dr.findByName("DEV");
		ts.affecterMissionADepartement(m.getId(), d.getId());
		Mission m2 = mr.findByName(mis);
		assertEquals(m2.getDepartement().getName(), "DEV");
	}
	@Test
	void testAjouterMission()
	{
		int id = ts.ajouterMission(new Mission("Mission2", "DESC"));
		assertEquals(id, mr.findByName("Mission2").getId());
		mr.deleteById(id);
	}
	@Test
	void testAjouterTimesheet()
	{
		mr.save(new Mission("MISSION300", "DESC"));
		er.save(new Employe("jane", "doe", "jane@doe.com", false, Role.TECHNICIEN));
		Mission m = mr.findByName("MISSION300");
		Employe e = er.findByEmail("jane@doe.com");
		LocalDate db = LocalDate.of(2021, 06, 02);
		LocalDate df = LocalDate.of(2021, 7, 10);
		int size = tr.findAll().size();
		ts.ajouterTimesheet(m.getId(), e.getId(), db, df);
		assertNotEquals(size, tr.findAll().size());

		
	} 

}
