package observers.busqueda;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface ObserverBusqueda {
	
	public void update(String stringBuscado, String nombreTerminal, int i,int segundosQueTardo) throws AddressException, MessagingException;

}
