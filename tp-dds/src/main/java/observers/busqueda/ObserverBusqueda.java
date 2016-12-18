package observers.busqueda;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.List;

public interface ObserverBusqueda {
	
	public void update(List<String> stringBuscado, String nombreTerminal, int i, int segundosQueTardo) throws AddressException, MessagingException;

}
