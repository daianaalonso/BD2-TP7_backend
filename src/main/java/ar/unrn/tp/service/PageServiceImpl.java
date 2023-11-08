package ar.unrn.tp.service;

import ar.unrn.tp.api.PageService;
import ar.unrn.tp.domain.Page;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl implements PageService {

    private MongoClient getMongoClient() {
        return MongoClients.create("mongodb://root:test.123@localhost:27017/?authSource=admin");
    }

    public void insertPage(Page page) {
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("pages");

            Document document = new Document("title", page.getTitle())
                    .append("text", page.getTitle())
                    .append("author", page.getAuthor())
                    .append("date", page.getDate().toString());

            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Document findPage(String id) {
        Document page = null;
        try (MongoClient mongoClient = getMongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("pages");

            page = collection
                    .find(Filters.eq("_id", id))
                    .first();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
}
