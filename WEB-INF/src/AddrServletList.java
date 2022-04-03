package na.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddrServletList extends HttpServlet {
   Connection conn;
   Statement stmt;
   public void init(){ //DB연결 
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

	public void service(HttpServletRequest req, HttpServletResponse res) //클라이언트가 요청할때마다 실행됨
		throws ServletException, IOException{ //SQL문 수행 -> list.html
		res.setContentType("text/html;charset=utf-8");//utf-16 :2byte --> 처리 문자의 한계
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

				pw.println("<a href='input.html'>입력</a>");
				pw.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				pw.println("<a href='..'>INDEX</a>");

				pw.println("<table border='1' cellpadding='7' cellspacing='2' width='50%'>");
					pw.println("<tr>");
						pw.println("<th>번호</th>");
						pw.println("<th>이름</th>");
						pw.println("<th>주소</th>");
						pw.println("<th>날짜</th>");
						pw.println("<th>삭제</th>");
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
						pw.println("<td align='center'><a href='del.do?seq="+seq+"'>삭제</a></td>"); //이렇게 넘기는 이유를 생각해봐
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
	public void destroy(){//DB 연결 해제 --> 서버의 안전파킹이 해제되면 메모리에서 내려감
		try{
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException se){
		}
	}

}  

//cd C:\NA\Web\tomcat10\webapps\ROOT\WEB-INF\src
//set classpath=.;C:\Na\Web\tomcat10\lib\servlet-api.jar 