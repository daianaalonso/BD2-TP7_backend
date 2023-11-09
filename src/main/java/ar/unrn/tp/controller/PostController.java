package ar.unrn.tp.controller;

import ar.unrn.tp.api.PostService;
import ar.unrn.tp.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public Post find(@PathVariable String id) {
        return postService.findPost(id);
    }

    @GetMapping("/latest")
    public List<Post> findLatest() {
        return postService.findLatestPosts();
    }

    @GetMapping("/byauthor")
    public String countByAuthor() {
        return postService.countPostsByAuthor();
    }

    @GetMapping("/author/{nombreautor}")
    public List<Post> findByAuthor(@PathVariable String nombreautor) {
        return postService.findPostsByAuthor(nombreautor);
    }

    @GetMapping("/search/{text}")
    public List<Post> findByText(@PathVariable String text) {
        return postService.findPostsByText(text);
    }

}
