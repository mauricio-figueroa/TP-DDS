package tp.dds.domain.observers.subjectBusqueda;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import tp.dds.domain.observers.busqueda.ObserverBusqueda;
import tp.dds.domain.poi.Poi;

public interface Subject {

	void agregarObservador(ObserverBusqueda observer, List<ObserverBusqueda> observers);

	public void eliminarObservador();

	void notificarObservador(String stringBuscado, String nombreTerminal, List<Poi> poisEncontrados,
			int segundosQueTardo) throws AddressException, MessagingException;

}
