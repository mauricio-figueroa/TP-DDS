package tp.dds.api.cgpDto;

import java.util.List;

public class ServicioDto {

	private String nombre;
	private List<HorariosDto> horarios;

	public ServicioDto() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<HorariosDto> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorariosDto> horarios) {
		this.horarios = horarios;
	}

}
