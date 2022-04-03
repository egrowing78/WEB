package na.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddrServletList extends HttpServlet {
   Connection conn;
   Statement stmt;
   public void init(){ //DB���� 
      String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
      String usr = "servlet";
      String pwd = "java";
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection(url, usr, pwd);
         stmt = conn.createStatement();
      }catch(ClassNotFoundException cnfe){
         System.out.println("#Oracle driver loading failed");
      }catch(SQLException se){}
   }

	public void service(HttpServletRequest req, HttpServletResponse res) //Ŭ���̾�Ʈ�� ��û�Ҷ����� �����
		throws ServletException, IOException{ //SQL�� ���� -> list.html
		res.setContentType("text/html;charset=utf-8");//utf-16 :2byte --> ó�� ������ �Ѱ�
		PrintWriter pw=res.getWriter();
		pw.println();
		pw.println("<meta charset='utf-8'>");
			pw.println("<style>");
				pw.println("table, th, td {");
				   pw.println("border: 1px solid black;");
				   pw.println("border-collapse: collapse;");
				pw.println("}");
				pw.println("th, td {");
				   pw.println("padding: 5px;");
				pw.println("}");
				pw.println("a { text-decoration:none }");
			pw.println("</style>");
			pw.println("<center>");
				pw.println("<h1>");
					pw.println("Address List ");
				pw.println("</h1>");

				pw.println("<a href='input.html'>�Է�</a>");
				pw.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				pw.println("<a href='..'>INDEX</a>");

				pw.println("<table border='1' cellpadding='7' cellspacing='2' width='50%'>");
					pw.println("<tr>");
						pw.println("<th>��ȣ</th>");
						pw.println("<th>�̸�</th>");
						pw.println("<th>�ּ�</th>");
						pw.println("<th>��¥</th>");
						pw.println("<th>����</th>");
					pw.println("</tr>");
					ResultSet rs=null;
					String sql="select * from address order by seq desc";
					try{
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							int seq=rs.getInt(1);
							String name=rs.getString(2);
							String addr=rs.getString(3);
							Date rdate=rs.getDate(4);
							pw.println("<tr>");
						pw.println("<td align='center'>"+seq+"</td>");
						pw.println("<td>"+name+"</td>");
						pw.println("<td>"+addr+"</td>");
						pw.println("<td>"+rdate+"</td>");
						pw.println("<td align='center'><a href='del.do?seq="+seq+"'>����</a></td>"); //�̷��� �ѱ�� ������ �����غ�
					pw.println("</tr>");
						}
					}catch(SQLException se){
					}finally{
						try{
							if(rs!=null) rs.close();
						}catch(SQLException se){
						}
					}
					
				pw.println("</table>");
			pw.println("</center>");
	}
	public void destroy(){//DB ���� ���� --> ������ ������ŷ�� �����Ǹ� �޸𸮿��� ������
		try{
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException se){
		}
	}

}  

//cd C:\NA\Web\tomcat10\webapps\ROOT\WEB-INF\src
//set classpath=.;C:\Na\Web\tomcat10\lib\servlet-api.jar 