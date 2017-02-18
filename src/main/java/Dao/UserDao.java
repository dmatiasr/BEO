package Dao;

import java.util.List;

import Model.User;

public interface UserDao {

	public List<User> getAll();
	public User findOne(String id);
	public boolean create(User us);
	public boolean delete(String id);
	public boolean update(User us);
	
}
