package observers.busqueda;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 


public class NotificarAdmin implements ObserverBusqueda{
	
	
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public static void main(String args[]) throws AddressException, MessagingException {
		generateAndSendEmail("pepe","terminalPalermo");
		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}
 
	public static void generateAndSendEmail(String stringBuscado, String nombreTerminal) throws AddressException, MessagingException {
 
		// Step1

		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
	
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("figueroa.a.mj@gmail.com"));
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("gabriel.dyck@despegar.com"));
		generateMailMessage.setSubject("Excesp de tiempo de busqueda");
		String emailBody = "Las busquedas en la terminal: "+nombreTerminal +" estan tardando mas de lo esperado." + "<br><br> El texto ingresado de la busqueda fue: "+stringBuscado+"."+"<br>CentroDeNotificaciones";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("Enviando Email ==========>>>>");
		Transport transport = getMailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com", "figueroa.a.mj@gmail.com", "seven15868");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	

	@Override
	public void update(String stringBuscado, String nombreTerminal, int cantPois,int segundosQueTardo) throws AddressException, MessagingException {
		if(segundosQueTardo>5){
			generateAndSendEmail(stringBuscado, nombreTerminal);			
		}		
	}
	
	

}
