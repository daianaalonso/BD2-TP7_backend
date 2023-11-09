package ar.unrn.tp.api;

import ar.unrn.tp.domain.Post;

import java.util.List;

public interface PostService {

    void insertPost(Post post);

    Post findPost(String id);

    List<Post> findLatestPosts();

    List<Post> findPostsByAuthor(String author);

    List<Post> findPostsByText(String text);

    String countPostsByAuthor();
}
