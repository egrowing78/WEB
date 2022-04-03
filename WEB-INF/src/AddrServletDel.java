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
   public void init(){ //DB���� 
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
			int seq = -1; //�ǹ� ���� �ʱ�ȭ�� �� �� ���ʻ� -1�� �ʱ�ȭ + �ε����� 0���� ������ �� �ֱ� ������
			String seqStr = req.getParameter("seq");

			//��ȿ�� ó�� : ������ ������ �����͸� �޾Ƽ� ó������ �ʵ��� üŷ�ϴ� ��
			if(seqStr==null){
				res.sendRedirect("list.do"); //�ΰ��̸� ����Ʈ�� ���ư��� ������ ���� ����X
				return;
			}//end of if

			seqStr=seqStr.trim();
			if(seqStr.length()==0){
				res.sendRedirect("list.do"); //�Ѿ���� ���� �����̸� ����Ʈ�� ���ư��� ������ ���� ����X
				return;
			}else{
				try{
					seq=Integer.parseInt(seqStr);
				}catch(NumberFormatException nfe){
					res.sendRedirect("list.do"); //������ �ƴϸ�.. ����Ʈ�� ���ư��� ������ ���� ����X
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
					pw.println("alert('��������')");
				}else{
					pw.println("alert('��������')");
				}
			}catch(SQLException se){
				 pw.println("alert('��������:::')");
			}
			  pw.println("location.href='list.do'");
			  pw.println("</script>");

		  }
	
	public void destroy(){//DB ���� ����
		try{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException se){
		}
	}

}  

//cd C:\NA\Web\tomcat10\webapps\ROOT\WEB-INF\src
//set classpath=.;C:\Na\Web\tomcat10\lib\servlet-api.jar 