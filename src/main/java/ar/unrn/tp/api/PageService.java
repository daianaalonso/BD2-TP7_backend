package ar.unrn.tp.api;

import ar.unrn.tp.domain.Page;

import java.util.List;

public interface PageService {

    void insertPage(Page page);

    List<Page> findPage(String id);

}
