package DaoImplementation;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import DaoImplementation.SessionManager;
import org.hibernate.query.Query;

import Dao.UserDao;

import Model.User;

public class UserDaoImpl implements UserDao {
	Session services;
	public UserDaoImpl(Session s){
		services=s;
	}
	
	public List<User> getAll() {
		List<User> users = new LinkedList<User>();
		//"from user" must be from Model.User
		users.addAll( services.createQuery("from User").list() );
		return users;	
	}

	public User findOne(String id) {
		if (id == null || id.isEmpty()) return null ;
		User userFound;
		Query<User> query = services.createQuery("from User where id=:id", User.class);
		query.setParameter("id", id);
		userFound = query.getSingleResult();
		if (userFound==null) return null;
		return userFound;
	}
	
	public List<User> findByName(String name){
		List<User> byname = new LinkedList<User>();
		if (name==null || name.isEmpty() ) return byname;
		Query<User> query = services.createQuery("from User where name=:name",User.class); 
		query.setParameter("name", name);
		byname.addAll(query.getResultList());
		return byname;
		
	}
	
	public List<User> findByAddrs(String addrss){
		List<User> byaddrs = new LinkedList<User>();
		if (addrss==null || addrss.isEmpty() ) return byaddrs;
		Query<User> query =services.createQuery("from User where address=:addrss",User.class);
		query.setParameter("addrss", addrss);
		byaddrs.addAll(query.getResultList());
		return byaddrs;
	}
	
	public boolean create(User us) {
		if (us == null) return false;
		if (!us.repOk()) return false;
		
		List<User> byname = this.findByName(us.getName());
		for(int i=0; i<byname.size();i++){
			if (byname.get(i).getAddress().equals( us.getAddress() ) )
				return false;
		}
		List<User> byaddrs= this.findByAddrs(us.getAddress());
		for(int i=0; i<byaddrs.size();i++){
			if(byaddrs.get(i).getName().equals(us.getName()))
				return false;
		}
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
		User exist = this.findOne(us.getId());
		if (exist==null) return false;
		services.update(us);
		return true;
	}
	
}