package domain;

import controller.response.PoiDTO;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="search")
public class Search {
@Id
@GeneratedValue
@Column
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="fk_search")
    private List<PoiDTO> poiDTOs;
    @Column
    private Date date;
    @Column
    private String user;
    @Column
    private String palabraBuscada;
    @Column
    private int cantPoisEncontrados;

    public Search(List<PoiDTO> poiDTOs, Date date, String user, String palabraBuscada, int cantPoisEncontrados) {
        this.poiDTOs = poiDTOs;
        this.date = date;
        this.user = user;
        this.palabraBuscada = palabraBuscada;
        this.cantPoisEncontrados = cantPoisEncontrados;
    }

    public Search() {
    }

    public List<PoiDTO> getPoiDTOs() {
        return poiDTOs;
    }

    public void setPoiDTOs(List<PoiDTO> poiDTOs) {
        this.poiDTOs = poiDTOs;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPalabraBuscada() {
        return palabraBuscada;
    }

    public void setPalabraBuscada(String palabraBuscada) {
        this.palabraBuscada = palabraBuscada;
    }

    public int getCantPoisEncontrados() {
        return cantPoisEncontrados;
    }

    public void setCantPoisEncontrados(int cantPoisEncontrados) {
        this.cantPoisEncontrados = cantPoisEncontrados;
    }
}
