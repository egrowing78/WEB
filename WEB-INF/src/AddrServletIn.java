package na.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

import java.sql.*;

//select * from address order by seq desc;
public class AddrServletIn extends HttpServlet {
   Connection conn;
   PreparedStatement pstmt;
   String sql="insert into address values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
   public void init(){ //DB연결 
      String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
      String usr = "servlet";
      String pwd = "java";
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection(url, usr, pwd);
         pstmt = conn.prepareStatement(sql);
      }catch(ClassNotFoundException cnfe){
         System.out.println("#Oracle driver loading failed");
      }catch(SQLException se){}
   }

	public void doPost(HttpServletRequest req, HttpServletResponse res)
		  throws ServletException, IOException { 
		  req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		  String addr = req.getParameter("addr");

			res.setContentType("text/html;charset=utf-8"); 
		  PrintWriter pw = res.getWriter();
		  pw.println("<script>");
			try{
			 pstmt.setString(1, name);
			 pstmt.setString(2, addr);
			 int i = pstmt.executeUpdate();
			 if(i>0){
				pw.println("alert('입력성공')");

				//pw.println("입력성공");
			 }else{
				pw.println("alert('입력실패')");
				//pw.println("입력실패");
			 }
		  }catch(SQLException se){
			 pw.println("입력실패: "+ se);
		  }
		  pw.println("location.href='list.do'"); //alert 팝업창에서 확인 누르면 이 경로로 이동
		 // pw.println("<br/><a href='list.do'>리스트</a><br/>");
		  pw.println("</script>");
	   }
		public void destroy(){//DB 연결 해제
			try{
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(SQLException se){
			}
		}

	}  

//cd C:\NA\Web\tomcat10\webapps\ROOT\WEB-INF\src
//set classpath=.;C:\Na\Web\tomcat10\lib\servlet-api.jar 