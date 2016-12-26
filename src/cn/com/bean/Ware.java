package cn.com.bean;
import java.io.Serializable;

public class Ware implements Serializable {

	private static final long serialVersionUID = 1L;
	private String number;
	private String name;
	private String description;
	private String daytime;
	private double price;
	private String address1;
	private String address2;
	private String address3;
	
	public String getNumber (){
		return number;
	}
	public void setNumber(String number){
		this.number=number;
	}
	public String getName (){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getDescription (){
		return description;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDaytime (){
		return daytime;
	}
	public void setDaytime(String daytime){
		this.daytime=daytime;
	}
	public double getPrice (){
		return price;
	}
	public void setPrice(double price){
		this.price=price;
	}
	public String getAddress1 (){
		return address1;
	}
	public void setAddress1(String address1){
		this.address1=address1;
	}
	public String getAddress2 (){
		return address2;
	}
	public void setAddress2(String address2){
		this.address2=address2;
	}
	public String getAddress3 (){
		return address3;
	}
	public void setAddress3(String address3){
		this.address3=address3;
	}
	
}