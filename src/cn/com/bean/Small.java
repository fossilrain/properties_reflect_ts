package cn.com.bean;
import java.io.Serializable;

public class Small implements Serializable {

	private static final long serialVersionUID = 1L;
	private int nums;
	private int num;
	private String names;
	private String info;
	
	public int getNums (){
		return nums;
	}
	public void setNums(int nums){
		this.nums=nums;
	}
	public int getNum (){
		return num;
	}
	public void setNum(int num){
		this.num=num;
	}
	public String getNames (){
		return names;
	}
	public void setNames(String names){
		this.names=names;
	}
	public String getInfo (){
		return info;
	}
	public void setInfo(String info){
		this.info=info;
	}
	
}