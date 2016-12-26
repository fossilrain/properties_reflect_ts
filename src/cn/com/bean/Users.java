package cn.com.bean;
import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String pass;
	private String email;
	private int rightx;
	private String registetime;
	private int status;
	private Date d;
	
	public String getId (){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getName (){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getPass (){
		return pass;
	}
	public void setPass(String pass){
		this.pass=pass;
	}
	public String getEmail (){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public int getRightx (){
		return rightx;
	}
	public void setRightx(int rightx){
		this.rightx=rightx;
	}
	public String getRegistetime (){
		return registetime;
	}
	public void setRegistetime(String registetime){
		this.registetime=registetime;
	}
	public int getStatus (){
		return status;
	}
	public void setStatus(int status){
		this.status=status;
	}
	
}