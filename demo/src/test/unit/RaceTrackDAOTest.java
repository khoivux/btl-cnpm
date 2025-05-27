package test.unit;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dao.RaceTrackDAO;
import model.RaceTrack;



public class RaceTrackDAOTest {
	RaceTrackDAO dao = new RaceTrackDAO();
	
	@Test
	public void testGetListStandard(){
		ArrayList<RaceTrack> results = dao.getList();
		Assert.assertNotNull(results);
		Assert.assertEquals(3, results.size());
		return;
	}
}
