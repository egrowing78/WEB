package na.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

//�ð� ��������
import java.time.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class LifeServlet extends HttpServlet //�ۺ� Ŭ������ ���ϸ�� �����ؾ���
{

	public void init(){ //ù��° ��û�� ���� �޸𸮿� �ε� �� �� (�� �ڹ� ������)
		System.out.println("init()");

	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{ //Ŭ���̾�Ʈ�� ��û�Ҷ����� ����
		System.out.println("service()");
	}
	public void destroy(){//�޸𸮿� ��ε�(�����) �� ���� -->������ ���涧(shutdown or ������å )
		System.out.println("destroy()");
		//�α� ������ ���ÿ� â�� ������ ������ Ȯ�� �� �� �ִ� ������ �ۼ��Ѵ�
		FileWriter fw= null;
		PrintWriter pw=null;
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);

		try{
			fw=new FileWriter("log_destroy().txt");
			pw=new PrintWriter(fw,true);
			pw.println("destroy() ����Ǿ����ϴ�."+time1);
		
			pw.close();
			fw.close();
		}catch(IOException e){
		}
	}

}  

//cd C:\NA\Web\tomcat10\webapps\ROOT\WEB-INF\src
//set classpath=.;C:\Na\Web\tomcat10\lib\servlet-api.jar 