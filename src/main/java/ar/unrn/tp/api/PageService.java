package ar.unrn.tp.api;

import ar.unrn.tp.domain.Page;
import org.bson.Document;

public interface PageService {

    void insertPage(Page page);

    Document findPage(String id);

}
