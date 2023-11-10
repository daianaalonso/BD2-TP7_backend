package ar.unrn.tp.controller;

import ar.unrn.tp.api.PageService;
import ar.unrn.tp.domain.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pages")
@AllArgsConstructor
public class PageController {
    private final PageService pageService;

    @GetMapping("/{id}")
    public List<Page> find(@PathVariable String id) {
        return pageService.findPage(id);
    }
}
