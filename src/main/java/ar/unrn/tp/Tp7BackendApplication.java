package ar.unrn.tp;

import ar.unrn.tp.api.PageService;
import ar.unrn.tp.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Tp7BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(Tp7BackendApplication.class, args);
    }

    @Autowired
    PageService pageService;

    private void insertPage() {
        Page page1 = new Page("Sobre las Infusiones", "Una infusión es una bebida", "Daiana Alonso", LocalDate.now().minusDays(2));
        Page page2 = new Page("Titulo de la página", "Texto de la página", "Autor de la página", LocalDate.now().minusDays(5));

        pageService.insertPage(page1);
        pageService.insertPage(page2);
    }

    /*private void insertPost(){
        Post post1 = new Post("Sobre las Infusiones", "Una infusión es una bebida", "Daiana Alonso", LocalDate.now().minusDays(2));
        Post post2 = new Post("", "Texto de la página", "Autor de la página", LocalDate.now().minusDays(5));

        pageService.insertPage(post1);
        pageService.insertPage(post2);
    }*/

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            //insertPage();
            //Document document = pageService.findPage("654b6fef6e64905432608c39");
            //System.out.println(document);

        };
    }
}
