package ar.unrn.tp.api;

import ar.unrn.tp.domain.Post;
import org.bson.Document;

import java.util.List;

public interface PostService {

    void insertPost(Post post);

    Document findPost(String id);

    List<Document> findLastPosts();

    List<Document> findPostsByAuthor(String author);

    List<Document> findPostsByText(String text);

    List<Document> countPostsByAuthor();
}
