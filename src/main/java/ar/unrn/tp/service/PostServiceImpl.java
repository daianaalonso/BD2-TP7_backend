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
                    .append("relatedLinks", post.getRelatedLinks())
                    .append("author", post.getAuthor())
                    .append("date", post.getDate().toString());

            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Document findPost(String id) {
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("posts");

            Document post = collection
                    .find(Filters.eq("_id", new ObjectId(id)))
                    .first();

            if (post != null) {
                return post;
            } else {
                System.out.println("No se encontró ningun post.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Document> findLastPosts() {
        return null;
    }

    @Override
    public List<Document> findPostsByAuthor(String author) {
        return null;
    }

    @Override
    public List<Document> findPostsByText(String text) {
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("posts");
            collection.createIndex(Indexes.text("text"));
//usar projecciones para devolver excluir campos?
            List<Document> documents = collection
                    .find(Filters.text(text))
                    .into(new ArrayList<>());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                                            Projections.fields(
                                                    Projections.exclude("title", "text", "tags", "resume", "relatedLinks", "date"),
                                                    Projections.include("author")
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
