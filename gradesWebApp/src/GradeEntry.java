

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GradeEntry
 */
@WebServlet("/GradeEntry")
public class GradeEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeEntry() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Connection getConnection()
  	{
  		Connection conn = null;
  		try
  		{
  			//URL of Oracle database server
  			String url = "jdbc:oracle:thin:system/password@localhost"; 
  			 try {
  					Class.forName("oracle.jdbc.driver.OracleDriver");
  				} catch (ClassNotFoundException e1) {
  					// TODO Auto-generated catch block
  					e1.printStackTrace();
  				}
  			//properties for creating connection to Oracle database
  			Properties props = new Properties();
  			props.setProperty("user", "testuserdb");
  			props.setProperty("password", "password");

  			//creating connection to Oracle database using JDBC
  			conn = DriverManager.getConnection(url,props);
  			
  		}
  		catch(SQLException e)
  		{
  			System.out.println(e.getMessage());
  			e.printStackTrace();
  			return null;
  		}
  		
  		return conn;
  	}
      
      
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		// TODO Auto-generated method stub
  		// Set response content type
  	      response.setContentType("text/html");
  	      
  	      // Actual logic goes here.
  	      
  	    	//URL of Oracle database server
  	        String url = "jdbc:oracle:thin:system/password@localhost"; 
  	        try {
  				Class.forName("oracle.jdbc.driver.OracleDriver");
  			} catch (ClassNotFoundException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
  	        //properties for creating connection to Oracle database
  	        Properties props = new Properties();
  	        props.setProperty("user", "testuserdb");
  	        props.setProperty("password", "password");
  	      
  	        //creating connection to Oracle database using JDBC
  	        Connection conn = null;
  			try {
  				conn = DriverManager.getConnection(url,props);
  			} catch (SQLException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}

  	        String sql ="INSERT INTO grades_db (assignment,grades) values ( '" + request.getParameter("assignment")+"',"+request.getParameter("grade")+")";
  	        PrintWriter out = response.getWriter();
    	   
    	   
  	        //creating PreparedStatement object to execute query
    	    PreparedStatement preStatement = null;
  	      	ResultSet result= null;
  	      	String ass = null, grade=null;
  	       
  				try {
					preStatement = conn.prepareStatement(sql);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  				
  				try {
					preStatement.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
  				
  				String sql2 ="select * from grades_db";
  	  	        //creating PreparedStatement object to execute query
  	  	        PreparedStatement preStatement2 = null;
  	  	        out.println(sql2);
  	  	       
  	  				try {
  						preStatement2 = conn.prepareStatement(sql2);
  						
  					} catch (SQLException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  	  				
  	  				try {
  						result = preStatement2.executeQuery();
  						
						
  						
  					} catch (SQLException e) {
  						// TODO Auto-generated catch block
  						e.getMessage();
  					}
  	  				 
  				 
  	  			out.println("<!DOCTYPE html>");
  	  			out.println("<html lang='en'>");
  	  			out.println("<head>");
  	  			out.println("<title>" + "Bloody Servlets" + "</title>");
  	  			out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">");
  	  			out.println("<body>");
  	  			
  	  			out.println(" <div class=\"container\">");
  	  			out.println("<h2>Database Updated</h2>");
  	  			out.println("<table class='table-bordered' border=1 width=50% height=50%>");
  	  			out.println("<tr><th>Assignment</th><th>Grade</th><tr>");
  	  			try {
					while(result.next())
					{
						ass = result.getString(1);
						grade=result.getString(2);
						out.println("<tr>"+"<td>"+ass+"</td>"+"<td>"+grade+"</td>"+"<tr>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  	  			out.println("</div>");
  	  			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
  	  			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>");
  	  			out.println("</body>");
  	  			out.println("</html>");	
  	    	  
  				
  			
			
  	     
  	   
	
  	     
  	}

  	/**
  	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  	 */
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		doGet(request,response);
  		
  		// TODO Auto-generated method stub
  	}
}
