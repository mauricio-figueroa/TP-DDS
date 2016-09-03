package tp.dds.domain.dao.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public  class Paginator<E, PK extends Serializable> {

    private List<E> entities;

    private GenericDao<E, PK> repository;
    private int page;

    public Paginator(GenericDao<E, PK> repository) {
        this.repository = repository;
        entities = new ArrayList<E>();
        page = 1;
    }

    public boolean hasMoreElements() {
        if (page != 1 && entities.size() < repository.page_size) {
            return false;
        }
        entities = repository.getPages(page);
        page++;  
        return true;
    }

    public List<E> getElements() {
        return entities;
    }
}
