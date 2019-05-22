package restServer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class RestServerMain {
	
		  static class ExampleServlet extends HttpServlet {
			  
		    @Override
		    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		        throws ServletException, IOException {
		    	UserDetails car = new UserDetails();
		    	car.setCarNumber(3);
		    	car.setStatus("detected");

		      resp.getWriter().println(car);
		    }
		  }


	public static void main(String[] args) throws Exception {
		 Server server = new Server(8080);
	      ServletContextHandler context = new ServletContextHandler();
	      context.setContextPath("/");
	      server.setHandler(context);
	      // Expose our example servlet.
	      context.addServlet(new ServletHolder(new ExampleServlet()), "/UserDetails");
	      // DefaultExports.initialize();
	      // Start the webserver.
	      server.start();
	      server.join();
	}

}
