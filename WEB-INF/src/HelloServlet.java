package na.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class HelloServlet extends HttpServlet //�ۺ� Ŭ������ ���ϸ�� �����ؾ���
{
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw=res.getWriter();//���� �Է�
		pw.println("<center>");
		pw.println("<script>");
		pw.println("alert('���� �Ϳ���!')");
		pw.println("</script>");
		pw.println("<h3 style='color:skyblue'>Hi Sevlet~~ �ѱ۳��Ͷ�</h3>"); //Ŭ���ؽ�Ʈ css2,css3 ���� �� �� �ִ�
		pw.println("<a href='http://www.naver.com'>���̹� �ٷΰ��� </a>");
		pw.println("<a href='index.html'>�ε���</a>");
		pw.println("</cneter>");
	}

}  

//cd C:\NA\Web\tomcat10\webapps\ROOT\WEB-INF\src
//set classpath=.;C:\Na\Web\tomcat10\lib\servlet-api.jar 