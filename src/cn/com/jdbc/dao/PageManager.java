package cn.com.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PageManager<T> {
	PublicDao pd=new PublicDao();
	ResultSet rs=null;
	
	private int totalPage;
	private int totalRows;
	private int nowPage;
	private int beforePage;
	private int nextPage;
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getBeforePage() {
		return beforePage;
	}
	public void setBeforePage(int beforePage) {
		this.beforePage = beforePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	//判断数据库中的表中数据行数
	public int tof(String tableName) throws SQLException{
		
		int count=0;
		ResultSet rs=(ResultSet)pd.publicMethod("select count(*) from "+tableName, null,true);
		while(rs.next()){
			count=rs.getInt(1);
		}
		pd.free(rs);
		return count;
	}

	//返回总页数
	public int getTotalPage(int count,String tableName) throws SQLException{
		
		int tof=tof(tableName);
		int totalPage=1;
		if(tof>0){
			totalPage=(tof+count-1)/count;
		}
		return totalPage;
	}
	//返回分页信息
	public String getPaging(String tableName,int count,int nowPage,String url) throws SQLException{
		setTotalPage(getTotalPage(count,tableName));
		setTotalRows(tof(tableName));
		
		setNowPage(nowPage);
		setNextPage(nowPage+ 1);
		setBeforePage(nowPage - 1);
		
		StringBuffer sb=new StringBuffer();
		sb.append("记录总数&nbsp;");
		sb.append(totalRows+"&nbsp;&nbsp;&nbsp;");
		sb.append("总页数&nbsp;");
		sb.append(totalPage+"&nbsp;&nbsp;&nbsp;");
		sb.append("当前页&nbsp;");
		sb.append(nowPage+"/"+totalPage+"&nbsp;&nbsp;&nbsp;");
		if(nowPage!=1){
			sb.append("<a href='");
			sb.append(url);
			sb.append("?nowPage=1'>首页</a>&nbsp;&nbsp;&nbsp;");
			sb.append("<a href='");
			sb.append(url);
			sb.append("?nowPage=");
			sb.append(beforePage);
			sb.append("'>上一页</a>&nbsp;&nbsp;&nbsp;");
		}else{
			sb.append("首页&nbsp;&nbsp;&nbsp;上一页&nbsp;&nbsp;&nbsp;");
		}
		//起始页码
		int start=Math.max(1, nowPage-4);
		int end=Math.min(totalPage, start+9);
		for(int i=start;i<=end;i++){
			if(i==nowPage){
				sb.append(i+"&nbsp;");
			}else{
				sb.append("<a href='");
				sb.append(url);
				sb.append("?nowPage=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>&nbsp;");
			}
		}
		
		//终止页码
		if(nowPage!=totalPage){
			sb.append("<a href='");
			sb.append(url);
			sb.append("?nowPage=");
			sb.append(nextPage);
			sb.append("'>下一页</a>&nbsp;&nbsp;&nbsp;");
			sb.append("<a href='");
			sb.append(url);
			sb.append("?nowPage=");
			sb.append(totalPage);
			sb.append("'>尾页</a>&nbsp;&nbsp;&nbsp;");
		}else{
			sb.append("下一页&nbsp;&nbsp;&nbsp;尾页&nbsp;&nbsp;&nbsp;");
		}
		
		return sb.toString();
		//记录总数26总页数6当前页1/6   首页上一页<a href='list2?nowPabe=2'>下一页</a><a href='list2?nowPabe=6'>尾页</a>
	}
	//返回分页字符串
	public String getPageString(int nowPage,String url,String param,String value){
		setNowPage(nowPage);
		setNextPage(nowPage+ 1);
		setBeforePage(nowPage - 1);
		
		StringBuffer sb=new StringBuffer();
		sb.append("记录总数&nbsp;");
		sb.append(totalRows+"&nbsp;&nbsp;&nbsp;");
		sb.append("总页数&nbsp;");
		sb.append(totalPage+"&nbsp;&nbsp;&nbsp;");
		sb.append("当前页&nbsp;");
		sb.append(nowPage+"/"+totalPage+"&nbsp;&nbsp;&nbsp;");
		if(nowPage!=1){
			sb.append("<a href='");
			sb.append(url);
//			sb.append("?nowPage=1'>首页</a>&nbsp;&nbsp;&nbsp;");
			sb.append("?nowPage=1&");//+
			sb.append(param);//+
			sb.append("=");//+
			sb.append(value);//+
			sb.append("'>首页</a>&nbsp;&nbsp;&nbsp;");//+
			sb.append("<a href='");
			sb.append(url);
			sb.append("?nowPage=");
			sb.append(beforePage);
			sb.append("&");//+
			sb.append(param);//+
			sb.append("=");//+
			sb.append(value);//+
			sb.append("'>上一页</a>&nbsp;&nbsp;&nbsp;");
		}else{
			sb.append("首页&nbsp;&nbsp;&nbsp;上一页&nbsp;&nbsp;&nbsp;");
		}
		//起始页码
		int start=Math.max(1, nowPage-4);
		int end=Math.min(totalPage, start+9);
		for(int i=start;i<=end;i++){
			if(i==nowPage){
				sb.append(i+"&nbsp;");
			}else{
				sb.append("<a href='");
				sb.append(url);
				sb.append("?nowPage=");
				sb.append(i);
				sb.append("&");//+
				sb.append(param);//+
				sb.append("=");//+
				sb.append(value);//+
				sb.append("'>");
				sb.append(i);
				sb.append("</a>&nbsp;");
			}
		}
		return sb.toString();
	}
	//关于集合分页操作
	public String getPaging(List<T> li,int count,int nowPage,String url,String param,String value){
		int number=(li.size()+count-1)/count;
		if(number==0){number=1;}//特殊情况
		setTotalPage(number);
		setTotalRows(li.size());
		
		setNowPage(nowPage);
		setNextPage(nowPage+ 1);
		setBeforePage(nowPage - 1);
		
		StringBuffer sb=new StringBuffer();
		sb.append("记录总数&nbsp;");
		sb.append(totalRows+"&nbsp;&nbsp;&nbsp;");
		sb.append("总页数&nbsp;");
		sb.append(totalPage+"&nbsp;&nbsp;&nbsp;");
		sb.append("当前页&nbsp;");
		sb.append(nowPage+"/"+totalPage+"&nbsp;&nbsp;&nbsp;");
		if(nowPage!=1){
			sb.append("<a href='");
			sb.append(url);
//			sb.append("?nowPage=1'>首页</a>&nbsp;&nbsp;&nbsp;");
			sb.append("?nowPage=1&");//+
			sb.append(param);//+
			sb.append("=");//+
			sb.append(value);//+
			sb.append("'>首页</a>&nbsp;&nbsp;&nbsp;");//+
			sb.append("<a href='");
			sb.append(url);
			sb.append("?nowPage=");
			sb.append(beforePage);
			sb.append("&");//+
			sb.append(param);//+
			sb.append("=");//+
			sb.append(value);//+
			sb.append("'>上一页</a>&nbsp;&nbsp;&nbsp;");
		}else{
			sb.append("首页&nbsp;&nbsp;&nbsp;上一页&nbsp;&nbsp;&nbsp;");
		}
		//起始页码
		int start=Math.max(1, nowPage-4);
		int end=Math.min(totalPage, start+9);
		for(int i=start;i<=end;i++){
			if(i==nowPage){
				sb.append(i+"&nbsp;");
			}else{
				sb.append("<a href='");
				sb.append(url);
				sb.append("?nowPage=");
				sb.append(i);
				sb.append("&");//+
				sb.append(param);//+
				sb.append("=");//+
				sb.append(value);//+
				sb.append("'>");
				sb.append(i);
				sb.append("</a>&nbsp;");
			}
		}		
		//终止页码
		if(nowPage!=totalPage){
			sb.append("<a href='");
			sb.append(url);
			sb.append("?nowPage=");
			sb.append(nextPage);
			sb.append("&");//+
			sb.append(param);//+
			sb.append("=");//+
			sb.append(value);//+
			sb.append("'>下一页</a>&nbsp;&nbsp;&nbsp;");
			sb.append("<a href='");
			sb.append(url);
			sb.append("?nowPage=");
			sb.append(totalPage);
			sb.append("&");//+
			sb.append(param);//+
			sb.append("=");//+
			sb.append(value);//+
			sb.append("'>尾页</a>&nbsp;&nbsp;&nbsp;");
		}else{
			sb.append("下一页&nbsp;&nbsp;&nbsp;尾页&nbsp;&nbsp;&nbsp;");
		}
		
		return sb.toString();
	}
	//基础通用分页操作
	public String getPaging(int totalCount,int count,int nowPage,String url,String param,String value){
		int number=(totalCount+count-1)/count;
		if(number==0){number=1;}//特殊情况
		setTotalPage(number);
		setTotalRows(totalCount);		
		return getPageString(nowPage,url,param,value);
	}
	public static void main(String[]args){
//		PageManager pm=new PageManager();
//		try {
//			System.out.println("<a href=/>");
//			System.out.println(pm.tof("ware")+"****************************");
//			System.out.println(pm.getTotalPage(5, "ware"));
//			System.out.println(pm.getPaging("ware", 5, 1, "list2"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(pm.getPaging(50, 10, 3, "test", null, null));
	}
}
