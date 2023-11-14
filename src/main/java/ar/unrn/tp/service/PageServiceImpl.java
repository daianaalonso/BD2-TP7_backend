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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class PageServiceImpl implements PageService {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public Page insertPage(Page page) {
        return inTx(collection -> {
            collection.insertOne(
                    new Document(
                            "title", page.getTitle())
                            .append("text", page.getText())
                            .append("author", page.getAuthor())
                            .append("date", page.getDate().toString())
            );
            return page;
        });
    }

    @Override
    public List<Page> findPage(String id) {
        return inTx(collection ->
                collection
                        .find(Filters.eq("_id", new ObjectId(id)))
                        .map(document -> Page.builder()
                                .id(String.valueOf(document.getObjectId("_id")))
                                .title(document.getString("title"))
                                .text(document.getString("text"))
                                .author(document.getString("author"))
                                .date(LocalDate.parse(document.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                                .build())
                        .into(new ArrayList<>())
        );
    }

    private <T> T inTx(Function<MongoCollection<Document>, T> toExecute) {
        mongoClient = MongoClients.create("mongodb://root:test.123@localhost:27017/?authSource=admin");
        database = mongoClient.getDatabase("blog");
        collection = database.getCollection("pages");
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
