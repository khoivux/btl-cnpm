package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import model.Driver;
import model.Membership;
import model.RaceResult;
import model.RaceTeam;
import model.RaceTrack;


public class RaceResultDAO extends DAO {
	public RaceResultDAO() {
		super();
	}
	
	public ArrayList<RaceResult> getList(int idRaceTrack) {
		ArrayList<RaceResult> result = new ArrayList<RaceResult>();
		String sql = "SELECT r.id as idraceresult, r.completionTime, r.completedLaps, "
				+ " m.id as idmembership, m.startTime, m.endTime, "
				+ " t.id as idraceteam, t.name as nameteam, t.brand, t.des, "
				+ " d.id as iddriver, d.name as namedriver, d.dob, d.national, d.bio, "
				+ " rt.id as idracetrack, rt.name as nameracetrack, rt.location, rt.numOfLaps, rt.time as rttime, rt.des as rtdes"
				+ " FROM tblraceresult r, tblmembership m, tblraceteam t, tbldriver d , tblracetrack rt"
				+ " WHERE r.idRaceTrack = ? AND rt.id = r.idRaceTrack AND m.id = r.idMembership AND t.id = m.idRaceTeam AND d.id = m.idDriver";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idRaceTrack);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Driver d = new Driver();
				d.setName(rs.getString("namedriver"));
				d.setBio(rs.getString("bio"));
				d.setDob(rs.getDate("dob"));
				d.setNational(rs.getString("national"));
				d.setId(rs.getInt("iddriver"));
				
				RaceTeam t = new RaceTeam();
				t.setId(rs.getInt("idraceteam"));
				t.setBrand(rs.getString("brand"));
				t.setDes(rs.getString("des"));
				t.setName(rs.getString("nameteam"));
				
				Membership m = new Membership();
				m.setId(rs.getInt("idmembership"));
				m.setStartTime(rs.getDate("startTime"));
				m.setEndTime(rs.getDate("endTime"));
				m.setDriver(d);
				m.setRaceTeam(t);
				
				RaceTrack rt =  new RaceTrack();
				rt.setId(rs.getInt("idracetrack"));
				rt.setName(rs.getString("nameracetrack"));
				rt.setDes(rs.getString("rtdes"));
				rt.setTime(rs.getDate("rttime"));
				rt.setLocation(rs.getString("location"));
				rt.setNumOfLaps(rs.getInt("numOfLaps"));
				
				RaceResult r = new RaceResult();
				r.setId(rs.getInt("idraceresult"));
				r.setCompletedLaps(rs.getInt("completedLaps"));
				r.setMembership(m);
				r.setCompletionTime(rs.getObject("completionTime", LocalTime.class));
				r.setRaceTrack(rt);
				if(r.getCompletionTime() != null) {
					r.setCompletedLaps(r.getRaceTrack().getNumOfLaps());
				}
				result.add(r);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateRaceResult(RaceResult r){
		String sql = "UPDATE tblraceresult SET completionTime = ?, completedLaps = ? WHERE id= ?";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, r.getCompletionTime());
			ps.setInt(2, r.getCompletedLaps());
			ps.setInt(3, r.getId());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
		return true;
	}
}
