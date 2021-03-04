package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.NullAction;

public class Controller extends HttpServlet{
	private boolean usingTemplate = false;
	
	//web.xml에서 <init-param>태그로 지정했던 parameter templatePage를 담을 예정
	private String templatePage = null;
	
	//command-Action객체를 HashMap에 넣기위해 map 생성
	//command: 명령어
	//Action: 명령어 처리 Action 객체
	private Map map = new HashMap();
	
	public void init(ServletConfig config) throws ServletException {
		
		//web.xml에서 <init-param>태그로 지정했던 parameter configFile
		String configFile = config.getInitParameter("configFile");
		
		//Properties class represent a persistent set of properties
		//Properties inherits HashTable, so can use put(), keySet()
		//Properties loads,stores properties from and to a stream
		Properties prop = new Properties();
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(configFile);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if(fis!=null) try {fis.close();} catch(IOException ex) {}
		}
		
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			System.out.println("command: " + command);
			
			String handlerClassName = prop.getProperty(command).trim();
			System.out.println("handlerClassName: " + handlerClassName);
			
			try {
				//Controller가 명령을 처리할때 필요한 Action클래스 정보를 가져와서 instance를 생성하는 작업:
				
				//1. loading class onto JVM
				 Class handlerClass = Class.forName(handlerClassName);
				
				 //2. create Class obj which will be read in order to create an Action object
				Object handlerInstance = handlerClass.newInstance();
				
				//3. store key-Action object into map
				//map에 넣어서 나중에(process() 메소드안에서) Action 인터페이스 타입의 객체를 생성할때에 사용
				map.put(command, handlerInstance);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new ServletException(e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new ServletException(e);
			}
		}
		
		//web.xml에서 <init-param>태그로 지정했던 parameter templatePage
		templatePage = config.getInitParameter("templatePage");
		
		if(templatePage != null && !templatePage.equals("")) {
			usingTemplate=true; //templatePage exists!
		}
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException { 
            process(request, response); 
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException { 
            process(request, response); 
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response)  
    	    throws ServletException, IOException { 
    	String command = request.getRequestURI();
    	System.out.println("RequestURI: " + request.getRequestURI());
    	
    	if(command.indexOf(request.getContextPath())==0) {
    		command = command.substring(request.getContextPath().length());
    		System.out.println("command:"+command);
    	}
    	
    	Action action = (Action)map.get(command);
    	
    	//if handler doesnt exist
    	if(action == null) {
    		action = new NullAction();
    	}
    	
    	String viewPage = null;
    	try {
    		//Action 인터페이스에서 선언한 execute() 메소드(abstract method)를 각Action 클래스에 구현해두었음
    		//Action 객체로 execute() 호출
    		//뭘하려고? 가려는 페이지 jsp 주소 문자열 반환해서 viewPage에 대입.
			viewPage = action.execute(request, response);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			throw new ServletException(e); 
		}
    	
    	//template page exist하면 
    	//request객체에 CONTENT_PAGE이름으로 viewPage(이동 페이지(jsp)이름) 넣기
    	if(usingTemplate) {
    		request.setAttribute("CONTENT_PAGE", viewPage);
    		
    	}
    	
    	//if usingTemplate, then dispatcher directs to templatePage
    	//if not, dispatcher directs to viewPage
    	RequestDispatcher dispatcher = request.getRequestDispatcher(usingTemplate? templatePage : viewPage);
    	dispatcher.forward(request, response);
    	
    	
    	
    	
    	
    }
	
	
}
