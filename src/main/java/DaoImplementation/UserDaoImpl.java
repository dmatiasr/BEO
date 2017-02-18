package DaoImplementation;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Dao.UserDao;
import Model.User;

public class UserDaoImpl implements UserDao {
	SessionManager session;
	Session services;
	public UserDaoImpl(){
		session = new SessionManager();
		services = session.getSession();
	}
	
	public List<User> getAll() {
		List<User> users = new LinkedList<User>();
		//"from user" must be from Model.User
		users.addAll( services.createQuery("from User").list() );
		return users;	
	}

	public User findOne(String id) {
		if (id == null) throw new IllegalArgumentException("UserDaoImpl.findOne() : id empty to search");
		User userFound;
		Query<User> query = services.createQuery("from User where id=:id", User.class);
		query.setParameter("id", id);
		userFound = query.getSingleResult();
		if (userFound==null) return null;
		return userFound;
	}

	public boolean create(User us) {
		if (us == null) return false;
		services.beginTransaction();
		services.save(us);
		services.getTransaction().commit();
		//services.close();
		return true;
	}

	public boolean delete(String id) {
		if (id.isEmpty() || id==null) return false;		
		if (this.findOne(id)==null) return false;
		services.beginTransaction();
		services.delete(this.findOne(id));
		services.getTransaction().commit();;
		
		return true;
	}

	public boolean update(User us) {
		if (us==null) return false;
		services.update(us);
		return true;
	}
	
//	private Session session=null;
//
//	public UserDaoImpl(Session session) {
//		this.session = session;
//	}
//	public List<User> getAll(){
//		List<User> users = new LinkedList<User>();
//		users.addAll( session.getSessionFactory()
//		.getCurrentSession()
//		.createQuery("from user", User.class).getResultList() ) ;
//		if (users.isEmpty()) return null;
//		return users;
//	}
//	public User findOne(String id){
//		if (id == null) throw new IllegalArgumentException("UserDaoImpl.findOne() : id empty to search");
//		User userFound;
//		Query<User> query = session.getSessionFactory().getCurrentSession().createQuery("from user where id = : id", User.class);
//		query.setParameter("id", id);
//		userFound = query.getSingleResult();
//		if (userFound==null) return null;
//		return userFound;
//	}
//	public boolean create(User us){
//		if (us == null) return false;
//		session.getSessionFactory().getCurrentSession().save(us);
//		return true;
//	}
//	public boolean delete(String id){
//		if (id.isEmpty() || id==null) return false;
//		User toDelete = this.findOne(id);		
//		session.getSessionFactory().getCurrentSession().delete(toDelete);
//		return true;
//	}
//	//TO-DO
//	public boolean update(User us){
//		return false;
//	}
	
}