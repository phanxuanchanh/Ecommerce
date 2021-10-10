package Ecommerce.SystemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce.SystemDAO.Dba_UserDAO;
import Ecommerce.SystemEntity.Dba_User;

@Service
public class Dba_UserServiceImpl implements IDba_UserService {
	@Autowired
	private Dba_UserDAO dba_UserDAO;
	
	public List<Dba_User> GetDba_Users() {
		return dba_UserDAO.GetDba_Users();
	}

	public Dba_User GetDba_User(String username) {
		if(dba_UserDAO.IsExistDba_UserByUsername(username))
			return dba_UserDAO.GetDba_User(username);
		return null;
	}
}
