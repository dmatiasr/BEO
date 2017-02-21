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
	
	public String create(Request req, Response resp){
		
		UserDao udao = new UserDaoImpl();
		if (req.queryParams("name")==null || req.queryParams("address")== null){
			resp.status(400);
            resp.body("name or address are null");
            return resp.body();
		}
		
		System.out.println("pase 32");
		
		String nam = req.queryParams("name");
		String addrs= req.queryParams("address");
		User newu = new User(nam,addrs);
		
		System.out.println("TOSTRING "+newu.toString());
		
		boolean res = udao.create(newu);
		
		int status = res ? 201 : 409;
		System.out.println(status+ " status");
		resp.status(status);
		if (res){
			resp.body("User created");
		}else{
			resp.body("User no created, duplicated");
		}
		return resp.body();
		
	}
	
	public String delete(Request req, Response resp){
		UserDao udao= new UserDaoImpl();
		if (req.queryParams("id").isEmpty() || req.queryParams("id")==null){
			resp.status(400);
			resp.body("id empty or null");
			return resp.body();
		}
		boolean res =udao.delete(req.params("id"));
		int status = res ? 201 :409;
		resp.status(status);
		if (res) {
			resp.body("User delete");
		}else{
			resp.body("User doesnt exist");
		}
		return resp.body();
	}
	
	public User findById(Request req, Response resp){
		if (req.queryParams("id")==null || req.queryParams("id").equals("")){
			resp.status(400);
			resp.body("id empty or null");
			return null;
		}
		
		UserDao udao = new UserDaoImpl();
		User userf = udao.findOne(req.params("id"));
		if (userf==null){
			resp.status(409);
			resp.body("User not found");
			return null;
		}
		resp.status(200);
		resp.body();
		return userf;
	}
	
	public String update(Request req, Response resp){
		if ( (req.queryParams("id")==null || req.queryParams("id").equals("")) ){
			resp.status(400);
			resp.body("Bad Parameters to update user");
			return resp.body();
		}
		User toupdate = new User();
		toupdate.setId(req.params("id"));
		toupdate.setName(req.params("name"));
		toupdate.setAddress(req.params("address"));
		UserDao udao = new UserDaoImpl();
		boolean res = udao.update(toupdate);
		int status = res ? 201 :409;
		resp.status(status);
		if (res){
			resp.body("User actualized");
		}else{
			resp.body("User not actualized");
		}
		return resp.body();
	}
}
