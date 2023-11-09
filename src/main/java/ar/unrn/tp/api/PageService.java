package ar.unrn.tp.api;

import ar.unrn.tp.domain.Page;

public interface PageService {

    void insertPage(Page page);

    Page findPage(String id);

}
