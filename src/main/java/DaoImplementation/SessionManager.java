package DaoImplementation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class SessionManager {
	public SessionManager(){
	
	}
	private SessionFactory getSessionFactory(){
		SessionFactory sf;
		
		    try 
		    { 
		    	sf = new Configuration().configure().buildSessionFactory();
		    	
		    } catch (HibernateException he) 
		    { 
		        System.err.println("Initialitation Problem : SessionFactory: " + he); 
		        throw new ExceptionInInitializerError(he); 
		    }
		    return sf;
		}
	
	public Session getSession(){
		return this.getSessionFactory().openSession();
	}
	
}
	
