package Controller;

import java.util.List;

import org.hibernate.Session;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import Dao.UserDao;
import DaoImplementation.SessionManager;
import DaoImplementation.UserDaoImpl;
import Model.User;

public class UserController {
	
	private SessionManager sm = null;
	private Session ss = null;

	public UserController(){
		sm = new SessionManager();
		ss= sm.getSession();
	}
	
	public List<User> getAll (Request req, Response resp){
		UserDao udao = new UserDaoImpl(ss);
		List<User> all = udao.getAll();
		int status = all.size()>0 ? 200 : 204;
		resp.status(status);
		if(status == 204){
			resp.body("No hay contenido");
		}else{
			resp.body(all.toString());
		}
		return all;
	}
	
	public String create(Request req, Response resp){
		Gson gson = new Gson();		
		String json = req.body();
		User requser= gson.fromJson(json,User.class);
		if (requser.getName()==null || requser.getAddress()== null || requser.getName().isEmpty() || requser.getAddress().isEmpty()){
			resp.status(400);
            resp.body("name or address are null or empty");
            return resp.body();
		}
		UserDao udao = new UserDaoImpl(ss);
	
		String nam = requser.getName();
		String addrs= requser.getAddress();
		User newu = new User(nam,addrs);
		
		
		boolean res = udao.create(newu);
		int status = res ? 201 : 409;
		resp.status(status);
		if (res){
			resp.body("User created");
		}else{
			resp.body("User no created");
		}
		return resp.body();
	}
	
	public String delete(Request req, Response resp){
//		Gson gson = new Gson();		
//		String json = req.body();
//		System.out.println("body"+req.body());
//		User requser= gson.fromJson(json,User.class);
//		System.out.println("usuario a eliminar "+requser.toString());	
//		
//		if (requser.getName()==null || requser.getAddress()== null || requser.getName().isEmpty() || requser.getAddress().isEmpty()){
//			resp.status(400);
//            resp.body("name or address are null or empty");
//            return resp.body();
//		}

		System.out.println("name "+req.queryParams("name"));
		System.out.println("address"+req.queryParams("address"));
		if (req.queryParams("name")==null || req.queryParams("name").isEmpty() || req.queryParams("address")==null || req.queryParams("address").isEmpty()){
			resp.status(400);
			resp.body("name or address are null or empty");
			return resp.body();
		}
		System.out.println("77");
		User requser = new User();
		requser.setName(req.queryParams("name"));
		requser.setAddress(req.queryParams("address"));
		UserDao udao= new UserDaoImpl(ss);
		boolean res =udao.delete(requser);
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
		
		UserDao udao = new UserDaoImpl(ss);
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
	public List<User> findByName(Request req, Response resp){
		System.out.println(req.params("name"));
		if (req.params("name")==null || req.params("name").equals("")){
			resp.status(400);
			resp.body("id empty or null");
			return null;
		}
		UserDao udao = new UserDaoImpl(ss);
		List<User> toname= udao.findByName(req.params("name"));
		if (toname.isEmpty()){
			resp.status(409);
			resp.body("Empty List");
		}
		resp.status(200);
		resp.body(toname.toString());
		return toname;
	}
	
	public List<User> findByAddress (Request req, Response resp){
		if (req.params("id")==null || req.params("id").equals("")){
			resp.status(400);
			resp.body("id empty or null");
			return null;
		}
		UserDao udao = new UserDaoImpl(ss);
		List<User> toaddr= udao.findByAddrs(req.params("address"));
		if(toaddr.isEmpty()){
			resp.status(409);
			resp.body("Empty List");
		}
		resp.status(200);
		resp.body(toaddr.toString());
		return toaddr;
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
		UserDao udao = new UserDaoImpl(ss);
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
