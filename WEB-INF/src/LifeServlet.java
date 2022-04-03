package na.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

//시간 찍을려고
import java.time.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class LifeServlet extends HttpServlet //퍼블릭 클래스는 파일명과 동일해야함
{

	public void init(){ //첫번째 요청에 의해 메모리에 로딩 될 때 (이 자바 파일이)
		System.out.println("init()");

	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{ //클라이언트가 요청할때마다 실행
		System.out.println("service()");
	}
	public void destroy(){//메모리에 언로딩(사라질) 때 실행 -->서버가 끊길때(shutdown or 서버정책 )
		System.out.println("destroy()");
		//로그 찍힘과 동시에 창이 꺼지기 때문에 확인 할 수 있는 파일을 작성한다
		FileWriter fw= null;
		PrintWriter pw=null;
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);

		try{
			fw=new FileWriter("log_destroy().txt");
			pw=new PrintWriter(fw,true);
			pw.println("destroy() 수행되었습니다."+time1);
		
			pw.close();
			fw.close();
		}catch(IOException e){
		}
	}

}  

//cd C:\NA\Web\tomcat10\webapps\ROOT\WEB-INF\src
//set classpath=.;C:\Na\Web\tomcat10\lib\servlet-api.jar 