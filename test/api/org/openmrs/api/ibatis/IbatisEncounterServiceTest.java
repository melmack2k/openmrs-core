package org.openmrs.api.ibatis;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.api.EncounterService;
import org.openmrs.context.Context;
import org.openmrs.context.IbatisContext;

public class IbatisEncounterServiceTest extends TestCase {
	
	protected EncounterService es;
	protected Encounter enc;
	
	public void setUp() throws Exception{
		Context context = new IbatisContext();
		
		context.authenticate("3-4", "test");
		
		es = new IbatisEncounterService(context);
	}

	public void testEncounterCreate() throws Exception {
		
		Location loc = new Location();
		
		loc.setAddress1("123 here");
		loc.setAddress2("123 there");
		loc.setCityVillage("city");
		loc.setCountry("country");
		loc.setDescription("desc");
		loc.setLatitude("lat.03");
		loc.setLongitude("lon.03");
		loc.setName("first loc");
		loc.setPostalCode("postal");
	
		enc.setLocation(loc);
		
		List<EncounterType> encTypes = es.getEncounterType();
		
		enc.setEncounterType(encTypes.get(0));
		
		Encounter newEnc = es.createEncounter(enc);
		
		assertNotNull(newEnc);
		assertTrue(enc.equals(newEnc));
		
	}
	
	public void testEncounterDelete() throws Exception {
		
		es.deleteEncounter(enc);
		
		Encounter e = es.getEncounter(enc.getEncounterId());
		
		assertNull(e);
		
	}
	
	
	public static Test suite() {
		return new TestSuite(IbatisEncounterServiceTest.class, "Basic IbatisEncounterService functionality");
	}

}
