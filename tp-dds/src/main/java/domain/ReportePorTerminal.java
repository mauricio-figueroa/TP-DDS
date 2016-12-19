package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportePorTerminal {

	private String nombreTerminal;
	private List<LineaReporte> busquedas;

	
	
	
	
	public ReportePorTerminal(String nombreTerminal) {
		super();
		this.nombreTerminal = nombreTerminal;
		this.busquedas= new ArrayList<LineaReporte>();
	}

	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public void setNombreTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}

	public List<LineaReporte> getBusquedas() {
		return busquedas;
	}

	public void setBusquedas(List<LineaReporte> busquedas) {
		this.busquedas = busquedas;
	}

	public void agregarReporteAterminal(int cantPoisBusqueda,String palabraBuscada) {
		busquedas.add(new LineaReporte(new Date(), cantPoisBusqueda, palabraBuscada));
	}

}
