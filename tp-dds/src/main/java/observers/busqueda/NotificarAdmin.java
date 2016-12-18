package observers.busqueda;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;


public class NotificarAdmin implements ObserverBusqueda {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    private static void generateServerProperties() {
        // Step1
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        generateMailMessage = new MimeMessage(getMailSession);
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);

        // Step2


    }

    public static void generateAndSendEmail(List<String> stringBuscado, String nombreTerminal) throws AddressException, MessagingException {
        generateServerProperties();

        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("figueroa.a.mj@gmail.com"));
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("gabriel.dyck@despegar.com"));
        generateMailMessage.setSubject("Exceso de tiempo de busqueda");
        String emailBody = "Las busquedas en la terminal: " + nombreTerminal + " estan tardando mas de lo esperado." + "<br><br> El texto ingresado de la busqueda fue: " + stringBuscado + "." + "<br>CentroDeNotificaciones";
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
    public void update(List<String> stringBuscado, String nombreTerminal, int cantPois, int segundosQueTardo) throws AddressException, MessagingException {
        if (segundosQueTardo > 5) {
            generateAndSendEmail(stringBuscado, nombreTerminal);
        }
    }

    public static void processExecutionError(String mail, String process) throws AddressException, MessagingException {
        generateServerProperties();
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        generateMailMessage.setSubject("Error en proceso" + process);
        String emailBody = "El proceso " + process + "no pudo ejecutarse correctamente";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        //Step3
        System.out.println("Enviando Email ==========>>>>");
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "figueroa.a.mj@gmail.com", "seven15868");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }


}
