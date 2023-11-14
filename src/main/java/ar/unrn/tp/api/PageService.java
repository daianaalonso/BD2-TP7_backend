package ar.unrn.tp.api;

import ar.unrn.tp.domain.Page;

import java.util.List;

public interface PageService {

    Page insertPage(Page page);

    List<Page> findPage(String id);

}
