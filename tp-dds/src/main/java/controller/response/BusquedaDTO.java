package controller.response;

public class BusquedaDTO {

    private String date;
    private String user;
    private String palabraBuscada;
    private int cantPoisEncontrados;


    public BusquedaDTO(String date, String user, String palabraBuscada, int cantPoisEncontrados) {
        this.date = date;
        this.user = user;
        this.palabraBuscada = palabraBuscada;
        this.cantPoisEncontrados = cantPoisEncontrados;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPalabraBuscada(String palabraBuscada) {
        this.palabraBuscada = palabraBuscada;
    }

    public String getPalabraBuscada() {
        return this.palabraBuscada;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String getUser() {
        return this.user;
    }

    public void setCantPoisEncontrados(int cantPoisEncontrados) {
        this.cantPoisEncontrados = cantPoisEncontrados;
    }

    private int getCantPoisEncontrados() {
        return this.cantPoisEncontrados;
    }
}
