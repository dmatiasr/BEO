package Controller;

import java.util.List;

import spark.Request;
import spark.Response;
import Dao.UserDao;
import DaoImplementation.UserDaoImpl;
import Model.User;

public class UserController {
	
	public List<User> getAll (Request req, Response resp){
		UserDao udao = new UserDaoImpl();
		List<User> all = udao.getAll();
		int status = all.size()>0 ? 200 : 204;
		resp.status(status);
		resp.body(all.toString());
		return all;
	}
//	public boolean create(Request req, Response resp){
//		UserDao udao = new UserDaoImpl();
//		String name = req.params("name").toString();
//		udao.create(us);
//		return false;
//	}
	
}
