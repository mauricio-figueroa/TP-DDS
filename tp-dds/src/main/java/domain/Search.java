package domain;

import controller.response.PoiDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "search")
public class Search {

    public Search() {
    }

    public Search(String searchKeys,List<PoiDTO> poiDTOs) {
        this.poiDTOs = poiDTOs;
        this.searchKeys=searchKeys;
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(name="search_keys")
    private String searchKeys;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private List<PoiDTO> poiDTOs;

    public void setId(long id) {
        this.id = id;
    }

    public void setPoiDTOs(List<PoiDTO> poiDTOs) {
        this.poiDTOs = poiDTOs;
    }

    public long getId() {
        return id;
    }

    public List<PoiDTO> getPoiDTOs() {
        return poiDTOs;
    }
}
