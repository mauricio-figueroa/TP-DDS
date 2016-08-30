package Dto.bankDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class BankDTO {
    @Id
    @GeneratedValue
    public long id;

    private String banco;
    private double x;
    private double y;
    private String sucursal;
    private String gerente;
    //private List<String> servicios;


    public BankDTO(String bankName, double x, double y, String sucursal) {
        this.banco = bankName;
        this.x = x;
        this.y = y;
        this.sucursal = sucursal;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }
//
    //public List<String> getServicios() {
    //	return servicios;
//	}

//	public void setServicios(List<String> servicios) {
    //this.servicios = servicios;
    //}

}
