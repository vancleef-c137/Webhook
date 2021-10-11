package tn.esprit.timesheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.entities.Role;
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
		mr.save(new Mission("MISSION3", "DESC"));
		dr.save(new Departement("DEV"));
		Mission m = mr.findByName("MISSION3");
		Departement d = dr.findByName("DEV");
		ts.affecterMissionADepartement(m.getId(), d.getId());
		Mission m2 = mr.findByName("MISSION3");
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
		Date db = new Date("02/06/2021");
		Date df = new Date("07/10/2021");
		int size = tr.findAll().size();
		ts.ajouterTimesheet(m.getId(), e.getId(), db, df);
		assertNotEquals(size, tr.findAll().size());

		
	}

}
