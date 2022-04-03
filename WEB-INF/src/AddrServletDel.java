package na.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

import java.sql.*;

//select * from address order by seq desc;
public class AddrServletDel extends HttpServlet {
   Connection conn;
   PreparedStatement pstmt;
   String sql="delete from ADDRESS where SEQ=?";
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

	public void doGet(HttpServletRequest req, HttpServletResponse res)
		  throws ServletException, IOException { 
			int seq = -1; //의미 없는 초기화를 할 때 관례상 -1로 초기화 + 인덱스가 0으로 시작할 수 있기 때문에
			String seqStr = req.getParameter("seq");

			//유효성 처리 : 서버가 엉뚱한 데이터를 받아서 처리하지 않도록 체킹하는 것
			if(seqStr==null){
				res.sendRedirect("list.do"); //널값이면 리스트로 돌아가고 이후의 문장 실행X
				return;
			}//end of if

			seqStr=seqStr.trim();
			if(seqStr.length()==0){
				res.sendRedirect("list.do"); //넘어오는 값이 공백이면 리스트로 돌아가고 이후의 문장 실행X
				return;
			}else{
				try{
					seq=Integer.parseInt(seqStr);
				}catch(NumberFormatException nfe){
					res.sendRedirect("list.do"); //정수가 아니면.. 리스트로 돌아가고 이후의 문장 실행X
					return;
				}//end of try-catch
			}//end of if-else
			res.setContentType("text/html;charset=utf-8"); 
			PrintWriter pw = res.getWriter();
			pw.println("<script>");
			try{
				pstmt.setInt(1, seq);
				int i = pstmt.executeUpdate();
				if(i>0){
					pw.println("alert('삭제성공')");
				}else{
					pw.println("alert('삭제실패')");
				}
			}catch(SQLException se){
				 pw.println("alert('삭제실패:::')");
			}
			  pw.println("location.href='list.do'");
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