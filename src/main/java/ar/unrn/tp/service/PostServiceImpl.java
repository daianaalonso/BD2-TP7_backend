package ar.unrn.tp.service;

import ar.unrn.tp.api.PostService;
import ar.unrn.tp.controller.request.AuthorCount;
import ar.unrn.tp.domain.Post;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

@Service
public class PostServiceImpl implements PostService {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @Override
    public Post insertPost(Post post) {
        return inTx(collection -> {
            collection.insertOne(
                    new Document(
                            "title", post.getTitle())
                            .append("text", post.getText())
                            .append("tags", post.getTags())
                            .append("resume", post.getResume())
                            .append("related-links", post.getRelatedLinks())
                            .append("author", post.getAuthor())
                            .append("date", post.getDate().toString())
            );
            return post;
        });
    }

    @Override
    public List<Post> findPost(String id) {
        return inTx(collection -> collection
                .find(Filters.eq("_id", new ObjectId(id)))
                .map(document -> Post.builder()
                        .id(String.valueOf(document.getObjectId("_id")))
                        .title(document.getString("title"))
                        .text(document.getString("text"))
                        .tags(document.getList("tags", String.class))
                        .resume(document.getString("resume"))
                        .relatedLinks(document.getList("related-links", String.class))
                        .author(document.getString("author"))
                        .date(LocalDate.parse(document.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build())
                .into(new ArrayList<>())
        );
    }

    @Override
    public List<Post> findLatestPosts() {
        return inTx(collection -> collection
                .find()
                .projection(fields(include("_id", "title", "resume")))
                .sort(Sorts.descending("date"))
                .limit(4)
                .map(doc -> Post.builder()
                        .id(String.valueOf(doc.getObjectId("_id")))
                        .title(doc.getString("title"))
                        .resume(doc.getString("resume"))
                        .build())
                .into(new ArrayList<>())
        );
    }

    @Override
    public List<Post> findPostsByAuthor(String author) {
        return inTx(collection ->
                collection
                        .find(Filters.eq("author", author))
                        .map(document -> Post.builder()
                                .id(String.valueOf(document.getObjectId("_id")))
                                .title(document.getString("title"))
                                .text(document.getString("text"))
                                .tags(document.getList("tags", String.class))
                                .resume(document.getString("resume"))
                                .relatedLinks(document.getList("related-links", String.class))
                                .author(document.getString("author"))
                                .date(LocalDate.parse(document.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                                .build())
                        .into(new ArrayList<>())
        );
    }

    @Override
    public List<Post> findPostsByText(String text) {
        return inTx(collection -> {
            collection.createIndex(Indexes.text("text"));
            return collection
                    .find(Filters.text(text))
                    .projection(fields(include("id", "title", "resume", "author", "date")))
                    .map(document -> Post.builder()
                            .id(String.valueOf(document.getObjectId("_id")))
                            .title(document.getString("title"))
                            .resume(document.getString("resume"))
                            .author(document.getString("author"))
                            .date(LocalDate.parse(document.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .build())
                    .into(new ArrayList<>());
        });
    }

    @Override
    public List<AuthorCount> countPostsByAuthor() {
        return inTx(collection -> {
            AggregateIterable<Document> documents = collection.aggregate(
                    Arrays.asList(Aggregates.group("$author", Accumulators.sum("count", 1)))
            );
            return StreamSupport.stream(documents.spliterator(), false)
                    .map(document -> new AuthorCount(
                            document.getString("_id"),
                            document.getInteger("count")))
                    .collect(Collectors.toList());
        });
    }

    private <T> T inTx(Function<MongoCollection<Document>, T> toExecute) {
        mongoClient = MongoClients.create("mongodb://root:test.123@localhost:27017/?authSource=admin");
        database = mongoClient.getDatabase("blog");
        collection = database.getCollection("posts");
        try {
            T t = toExecute.apply(collection);
            return t;
        } catch (Exception e) {
            throw e;
        } finally {
            mongoClient.close();
        }
    }
}