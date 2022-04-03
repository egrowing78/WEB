package na.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class HelloServlet extends HttpServlet //퍼블릭 클래스는 파일명과 동일해야함
{
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw=res.getWriter();//글자 입력
		pw.println("<center>");
		pw.println("<script>");
		pw.println("alert('망고 귀여워!')");
		pw.println("</script>");
		pw.println("<h3 style='color:skyblue'>Hi Sevlet~~ 한글나와라</h3>"); //클립텍스트 css2,css3 에서 볼 수 있다
		pw.println("<a href='http://www.naver.com'>네이버 바로가기 </a>");
		pw.println("<a href='index.html'>인덱스</a>");
		pw.println("</cneter>");
	}

}  

//cd C:\NA\Web\tomcat10\webapps\ROOT\WEB-INF\src
//set classpath=.;C:\Na\Web\tomcat10\lib\servlet-api.jar 