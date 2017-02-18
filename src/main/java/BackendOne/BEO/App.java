package BackendOne.BEO;

import java.util.List;

import org.hibernate.Session;

import Dao.UserDao;
import DaoImplementation.SessionManager;
import DaoImplementation.UserDaoImpl;
import Model.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    	User user = new User("Matias","Address1");
    	UserDao udao = new UserDaoImpl();
    	
    	//Create
    	System.out.println("CREATION");
    	udao.create(user);
    	
    	List<User> allUser = udao.getAll();
    	for(int i=0;i< allUser.size();i++){
    		System.out.println("User "+allUser.toString() );
    	}
    	
    	//Update
    	System.out.println("UPDATE");
    	
    	user.setAddress("address2");
    	udao.update(user);
    	
    	allUser = udao.getAll();
    	System.out.println("--- 2nd search ---");
    	for(int i=0;i< allUser.size();i++){
    		System.out.println("User "+allUser.toString() );
    	}
    	
    	//Delete
    	System.out.println("DELETE");
    	String id = user.getId();
    	udao.delete(id);
    	allUser = udao.getAll();
    	System.out.println("--- 3rd search --- "+allUser.size() );
    	
    	for(int i=0;i< allUser.size();i++){
    		System.out.println("User "+allUser.toString() );
    	}
    }
}
