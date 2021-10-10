package Ecommerce.SystemDAO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Ecommerce.SystemDTO.ProfileInput;
import Ecommerce.SystemEntity.Profile;
import Ecommerce.SystemEntity.ProfileMapper;

@SuppressWarnings("restriction")
@Repository
public class ProfileDAO {
	@Autowired
	@Resource(name = "jdbcTemplate2")
	private JdbcTemplate jdbcTemplate;
	
	public List<Profile> GetProfiles(){
		List<Profile> profiles = new ArrayList<Profile>();
		String query = "Select * from dba_profiles";
		profiles = jdbcTemplate.query(query, new ProfileMapper());
		return profiles;
	}
	
	public void CreateProfile(ProfileInput profileInput) {
		String query = String.format(
				"Create profile %s limit connect_time %s sessions_per_user %s failed_login_attempts %s password_lock_time %s",
				profileInput.getProfile_name(), profileInput.getConnect_time(), profileInput.getSessions_per_user(), 
				profileInput.getFailed_login_attempts(), profileInput.getPassword_lock_time());
		jdbcTemplate.execute(query);
	}
	
	public boolean IsExistProfileByName(String profileName) {
		String query = "Select count(*) from dba_profiles where profile = ?";
		int count = jdbcTemplate.queryForObject(query, new Object[] { profileName }, Integer.class);
		return (count > 0);
	}
}
