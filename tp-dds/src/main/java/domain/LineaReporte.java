package domain;

import java.util.Date;

public class LineaReporte {
	
	private Date fechaBusqueda;
	private int cantPoisBusqueda ;
	private String palabraBuscada;
	
	
	
	public String getPalabraBuscada() {
		return palabraBuscada;
	}
	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}
	public LineaReporte(Date fechaBusqueda, int cantPoisBusqueda,String palabraBscada) {
		super();
		this.fechaBusqueda = fechaBusqueda;
		this.cantPoisBusqueda = cantPoisBusqueda;
		this.palabraBuscada=palabraBscada;
	}
	public Date getFechaBusqueda() {
		return fechaBusqueda;
	}
	public void setFechaBusqueda(Date fechaBusqueda) {
		this.fechaBusqueda = fechaBusqueda;
	}
	public int getCantPoisBusqueda() {
		return cantPoisBusqueda;
	}
	public void setCantPoisBusqueda(int cantPoisBusqueda) {
		this.cantPoisBusqueda = cantPoisBusqueda;
	}
	
	
	

}
