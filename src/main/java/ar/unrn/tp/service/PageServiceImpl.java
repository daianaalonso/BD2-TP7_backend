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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PageServiceImpl implements PageService {
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    private MongoClient getMongoClient() {
        return MongoClients.create("mongodb://root:test.123@localhost:27017/?authSource=admin");
    }

    public void insertPage(Page page) {
        try (MongoClient mongoClient = getMongoClient()) {
            database = mongoClient.getDatabase("blog");
            collection = database.getCollection("pages");
            Document document = new Document(
                    "title", page.getTitle())
                    .append("text", page.getText())
                    .append("author", page.getAuthor())
                    .append("date", page.getDate().toString());
            collection.insertOne(document);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Page findPage(String id) {
        Page page;
        try (MongoClient mongoClient = getMongoClient()) {
            database = mongoClient.getDatabase("blog");
            collection = database.getCollection("pages");

            //o findOne?
            Document document = collection
                    .find(Filters.eq("_id", new ObjectId(id)))
                    .first();
            page = Page.builder()
                    .id(String.valueOf(document.getObjectId("_id")))
                    .title(document.getString("title"))
                    .text(document.getString("text"))
                    .author(document.getString("author"))
                    .date(LocalDate.parse(document.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .build();
        } catch (Exception e) {
            throw e;
        }
        return page;
    }
}
