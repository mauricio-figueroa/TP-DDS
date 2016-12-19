package mainApplication;

import controller.controllers.AdminController;
import dao.AdminDAO;
import dao.EntityManagerProvider;
import dao.TerminalDao;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;
import users.Admin;
import users.Terminal;

import javax.persistence.EntityManager;


public class Application {

		private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

		public static void main(String[] args) {
			LOGGER.info("Starting the application...");
			
			
			DispatcherServlet dispatcherServlet = new DispatcherServlet();
			dispatcherServlet.setContextConfigLocation("classpath:context.xml");

			ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
			handler.setContextPath("/diseno-de-sistemas");
			handler.addServlet(new ServletHolder(dispatcherServlet), "/*");

			Server server = new Server();
			ServerConnector serverConnector = new ServerConnector(server);
			serverConnector.setPort(8080);
			server.setConnectors(new Connector[] { serverConnector });
			server.setHandler(handler);

			try {
				server.start();
				server.join();
			} catch (Exception exception) {
				LOGGER.error("Error starting the application", exception);
				System.exit(1);
			}




			LOGGER.info("Application started");
		}

	}

