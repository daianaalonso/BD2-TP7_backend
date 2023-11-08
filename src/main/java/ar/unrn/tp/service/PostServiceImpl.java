package ar.unrn.tp.service;

import ar.unrn.tp.api.PostService;
import ar.unrn.tp.domain.Post;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Projections.*;

@Service
public class PostServiceImpl implements PostService {

    private MongoClient getMongoClient() {
        return MongoClients.create("mongodb://root:test.123@localhost:27017/?authSource=admin");
    }

    @Override
    public void insertPost(Post post) {
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("posts");

            Document document = new Document("title", post.getTitle())
                    .append("text", post.getTitle())
                    .append("tags", post.getTags())
                    .append("resume", post.getResume())
                    .append("related-links", post.getRelatedLinks())
                    .append("author", post.getAuthor())
                    .append("date", post.getDate().toString());

            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Document findPost(String id) {
        Document post = null;
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("posts");

            post = collection
                    .find(Filters.eq("_id", new ObjectId(id)))
                    .first();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public List<Document> findLastPosts() {
        List<Document> documents = new ArrayList<>();
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("posts");

            documents = collection
                    .find()
                    .sort(Sorts.descending("date"))
                    .projection(
                            fields(
                                    include("id", "title", "resume"),
                                    exclude("author", "date", "text", "tags", "related-links")
                            )
                    )
                    .limit(4)
                    .into(new ArrayList<>());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return documents;
    }

    @Override
    public List<Document> findPostsByAuthor(String author) {
        List<Document> documents = new ArrayList<>();
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("posts");

            documents = collection
                    .find(Filters.eq("author", author))
                    .into(new ArrayList<>());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return documents;
    }

    @Override
    public List<Document> findPostsByText(String text) {
        List<Document> documents = new ArrayList<>();
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("posts");

            collection.createIndex(Indexes.text("text"));

            documents = collection
                    .find(Filters.text(text))
                    .projection(
                            fields(
                                    include("id", "title", "resume", "author", "date"),
                                    exclude("text", "tags", "relatedLinks")))
                    .into(new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documents;
    }

    @Override
    public List<Document> countPostsByAuthor() {
        List<Document> documents = new ArrayList<>();
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("posts");

            documents = collection.aggregate(
                            Arrays.asList(
                                    Aggregates.project(
                                            fields(
                                                    Projections.exclude("title", "text", "tags", "resume", "relatedLinks", "date"),
                                                    include("author")
                                            )
                                    ),
                                    Aggregates.group("$author", Accumulators.sum("count", 1))))
                    .into(new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documents;
    }
}
