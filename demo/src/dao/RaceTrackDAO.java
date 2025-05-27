package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.RaceTrack;


public class RaceTrackDAO extends DAO {
	public RaceTrackDAO() {
		super();
	}
	public ArrayList<RaceTrack> getList() {
		ArrayList<RaceTrack> result = new ArrayList<RaceTrack>();
		String sql = "SELECT * FROM tblracetrack";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				RaceTrack rt = new RaceTrack();
				rt.setId(rs.getInt("id"));
				rt.setName(rs.getString("name"));
				rt.setLocation(rs.getString("location"));
				rt.setTime(rs.getDate("time"));
				rt.setNumOfLaps(rs.getInt("numOfLaps"));
				rt.setDes(rs.getString("des"));
				result.add(rt);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
}
