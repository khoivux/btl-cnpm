package test.unit;

import java.sql.Connection;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dao.DAO;
import dao.RaceResultDAO;
import model.RaceResult;

public class RaceResultDAOTest {
	RaceResultDAO dao = new RaceResultDAO();
	
	@Test
	public void testGetListException1(){
		int invalidTrackId = 4;
		ArrayList<RaceResult> results = dao.getList(invalidTrackId);
		Assert.assertNotNull(results);
		Assert.assertEquals(0, results.size());
		return;
	}            
	
	
	@Test
    public void testGetListStandard1() {
        int validTrackId = 2; 
        ArrayList<RaceResult> results = dao.getList(validTrackId);
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());

        for (RaceResult r : results) {
            Assert.assertNotNull(r.getMembership());
            Assert.assertNotNull(r.getRaceTrack());
            Assert.assertEquals(validTrackId, r.getRaceTrack().getId());
        }
    }
	
	@Test
    public void testGetListStandard2() {
        int validTrackId = 2; 
        ArrayList<RaceResult> results = dao.getList(validTrackId);
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());

        for (RaceResult r : results) {
            Assert.assertNotNull(r.getMembership());
            Assert.assertNotNull(r.getRaceTrack());
            Assert.assertEquals(validTrackId, r.getRaceTrack().getId());
        }
    }
	
	@Test
	public void testUpdateRaceResult() {
	    Connection con = DAO.con;
	    LocalTime newTime = LocalTime.of(1, 23, 45, 123_000_000); // 01:23:45.123
	    int newLaps = 57;
	    int testId = 1;
	    int invalidTrack = 1;

	    try {
	        con.setAutoCommit(false);
	        ArrayList<RaceResult> list = dao.getList(invalidTrack); 
	        RaceResult testRR = null;
	        for (RaceResult r : list) {
	            if (r.getId() == testId) {
	            	testRR = r;
	                break;
	            }
	        }
	        Assert.assertNotNull(testRR);

	        testRR.setCompletionTime(newTime);
	        testRR.setCompletedLaps(newLaps);
	        boolean updated = dao.updateRaceResult(testRR);
	        Assert.assertTrue(updated);

	        RaceResult updatedResult = null;
	        list = dao.getList(1);
	        for (RaceResult r : list) {
	            if (r.getId() == testId) {
	                updatedResult = r;
	                break;
	            }
	        }

	        Assert.assertNotNull(updatedResult);
	        Assert.assertEquals(newTime, updatedResult.getCompletionTime());
	        Assert.assertEquals(newLaps, updatedResult.getCompletedLaps());

	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("Exception during test: " + e.getMessage());
	    } finally {
	        try {
	            con.rollback(); 
	            con.setAutoCommit(true);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}

}
