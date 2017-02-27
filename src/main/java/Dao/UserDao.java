package Dao;

import java.util.List;

import Model.User;

public interface UserDao {

	public List<User> getAll();
	public User findOne(String id);
	public boolean create(User us);
	public boolean delete(User us);
	public boolean update(User us);
	public List<User> findByName(String name);
	public List<User> findByAddrs(String addrs);
}
