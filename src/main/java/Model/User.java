package Model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "User")
public class User {
//	@Id
//	@Column(name = "id")
	private String id;
	
//	@Column (name = "name")
	private String name;
	
//	@Column (name = "address")
	private String address;

	public User(){
		
	}
	public User(String id, String name, String address){
		this.id=id;
		this.name=name;
		this.address=address;
	}
	
	public User(String name, String address){
		this.id= UUID.randomUUID().toString();
		this.name=name;
		this.address=address;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name=name;
	}	
	public void setAddress(String address){
		this.address=address;
	}
	public String getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String toString (){
		return "id: "+id+" name: "+name+" address: "+address;
	}

}
