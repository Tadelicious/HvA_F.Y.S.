/* 	This is the proccessing page of the captive portal.
*	Copy right: Deben Oldert
*	Here classes are called to check and update database query's and to configure iptables.
*	Called classe are: {Database, Command}
*/
package captiveportal;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class proccess
 */
@WebServlet("/proccess")
public class proccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proccess() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set the MIME type for the page
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
		// Send user back to login form
	      try {
	    	  out.println("<script>window.location.replace('http://corendon.nl/Captive%20Portal');</script>");
	      	} finally {
	      		out.close();  // Close the output writer
	      	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Set the MIME type for the page
	      response.setContentType("text/html");
	      // Define variables
	      //#####################################
	      String ticket = request.getParameter("ticket");
	      String lastName = request.getParameter("lastname");
	      int exitCode = 1;
	      String javaReturn0 = "<script>setTimeout(function(){window.location.replace('http://corendon.nl')},10000);</script>";
	      String javaReturnErrorS = "<script>window.location.replace('http://corendon.nl/Captive_Portal/?err=";
	      String javaReturnErrorE = "');</script>";
	      //#####################################
	      PrintWriter out = response.getWriter();
	      Database SQL = new Database();
	      Command CMD = new Command();

	      try {
			if(SQL.Select(ticket, lastName)) {
				  if(SQL.Update(ticket, lastName)) {
					  if(CMD.Grand(request.getRemoteAddr())) {
						  exitCode = 0;
					  }
					  else {
						  if(SQL.Reset(ticket, lastName)) {
							  exitCode = 2;
						  }
						  else {
							  exitCode = 3;
						  }
					  }
				  
				  }
				  else {
					  exitCode = 2;
				  }
			  }
			else {
				exitCode = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			exitCode = 4;
		}
	    switch (exitCode) {
	    case 0:
	    	out.println(javaReturn0);
	    	break;
	    case 1:
	    	out.println(javaReturnErrorS+exitCode+javaReturnErrorE);
	    	break;
	    case 2:
	    	out.println(javaReturnErrorS+exitCode+javaReturnErrorE);
	    	break;
	    case 3:
	    	out.println(javaReturnErrorS+exitCode+javaReturnErrorE);
	    	break;
	    case 4:
	    	out.println(javaReturnErrorS+exitCode+javaReturnErrorE);
	    	break;
	    }
	}

}