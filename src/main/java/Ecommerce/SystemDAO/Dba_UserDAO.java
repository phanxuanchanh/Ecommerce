package Ecommerce.SystemDAO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Ecommerce.SystemEntity.Dba_User;
import Ecommerce.SystemEntity.Dba_UserMapper;

@SuppressWarnings("restriction")
@Repository
public class Dba_UserDAO {
	@Autowired
	@Resource(name = "jdbcTemplate2")
	private JdbcTemplate jdbcTemplate;
	
	public List<Dba_User> getDba_Users() {
		List<Dba_User> dba_users = new ArrayList<Dba_User>();
		String query = "Select * from dba_users";
		dba_users = jdbcTemplate.query(query, new Dba_UserMapper());
		return dba_users;
	}
}