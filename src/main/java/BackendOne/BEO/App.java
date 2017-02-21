package BackendOne.BEO;

import java.util.List;

import org.hibernate.Session;

import static spark.Spark.*;
import static Controller.JsonUtil.*;
import Controller.UserController;
import Dao.UserDao;
import DaoImplementation.SessionManager;
import DaoImplementation.UserDaoImpl;
import Model.User;

/**
 * Hello world!
 *
 */
public class App {
	private static UserController ucontroller;
    
	public static void main( String[] args ){
    	ucontroller = new UserController();
    	
    	get("/index",(req,res)->  "HelloWorld");
    	get("/users",(req,res)-> ucontroller.getAll(req,res), json() );
    	get("users/findById/:id",(req,res)->ucontroller.findById(req, res),json() );
    	post("/users",(req,res)-> ucontroller.create(req, res) );
    	put("/users/:id",(req,res)-> ucontroller.update(req,res));
    	delete("/users/:id", (req,res)-> ucontroller.delete(req, res));
    	
    	
    	after((req, res) -> {res.type("application/json");});
    }	
}
