package controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BusquedaDTO {

    private String date;
    private String user;
    private String palabraBuscada;
    private int cantPoisEncontrados;


    @JsonCreator
    public BusquedaDTO(@JsonProperty("user") String user, @JsonProperty("date") String date, @JsonProperty("palabraBuscada") String palabraBuscada, @JsonProperty("cantPoisEncontrados") int cantPoisEncontrados) {
        this.date = date;
        this.user = user;
        this.palabraBuscada = palabraBuscada;
        this.cantPoisEncontrados = cantPoisEncontrados;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("palabraBuscada")
    public void setPalabraBuscada(String palabraBuscada) {
        this.palabraBuscada = palabraBuscada;
    }

    @JsonProperty("palabraBuscada")
    public String getPalabraBuscada() {
        return this.palabraBuscada;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("user")
    private String getUser() {
        return this.user;
    }

    @JsonProperty("cantPoisEncontrados")
    public void setCantPoisEncontrados(int cantPoisEncontrados) {
        this.cantPoisEncontrados = cantPoisEncontrados;
    }

    @JsonProperty("cantPoisEncontrados")
    private int getCantPoisEncontrados() {
        return this.cantPoisEncontrados;
    }
}
