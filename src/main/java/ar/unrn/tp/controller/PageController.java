package ar.unrn.tp.controller;

import ar.unrn.tp.api.PageService;
import ar.unrn.tp.domain.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pages")
@AllArgsConstructor
public class PageController {
    private final PageService pageService;

    @GetMapping("/{id}")
    public Page find(@PathVariable String id) {
        return pageService.findPage(id);
    }
}
