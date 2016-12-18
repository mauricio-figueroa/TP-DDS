package observers.subjectBusqueda;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import observers.busqueda.ObserverBusqueda;
import poi.Poi;

public interface Subject {

	void agregarObservador(ObserverBusqueda observer, List<ObserverBusqueda> observers);

	public void eliminarObservador();

	void notificarObservador(List<String> stringBuscado, String nombreTerminal, List<Poi> poisEncontrados,
			int segundosQueTardo) throws AddressException, MessagingException;

}
