package cn.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import cn.com.properties.PropertiesManager;

public class PropertiesSL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PropertiesSL() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PropertiesManager pm=PropertiesManager.getInstance();
		System.out.println(pm.getProperty("ceshi"));
//		pm.load(pm.getInputStream("/3.properties"));
//		System.out.println(PropertiesManager.class.getPackage().getName());
//		System.out.println(pm.getProperty("cn.com.3"));
//		File fi=new File("xxx.txt");
//		fi.createNewFile();
//		System.out.println(fi.getAbsolutePath());
		//获取当前运行程序的完整路径,绝对的路径,并且适应操作系统
//		System.out.println(System.getProperty("java.class.path")+"@@@@@@@@");
		//获取当前运行程序的完整目录
//		System.out.println(System.getProperty("usr.dir")+"############");
		//获取到编译的根路径
/*
		String url=PropertiesManager.class.getResource("/").getPath();
		System.out.println(url+"SL");
		pm.load(pm.getInputStream(url+"3.properties"));
		System.out.println(pm.getProperty("cn.com.3")+"ceshi..++++++++++....._____");
		System.out.println(PropertiesManager.class.getResource("").getPath()+"qubie1");
		System.out.println(PropertiesManager.class.getResource("/").getPath()+"qubie2");
		*/
//		ArrayList al=new ArrayList();
		//jdk类库获取异常
//		System.out.println(ArrayList.class.getResource("").getPath()+"JDK类");
		//第三方类库可以直接获取
		System.out.println(Element.class.getResource("/").getPath()+"第三方类");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
