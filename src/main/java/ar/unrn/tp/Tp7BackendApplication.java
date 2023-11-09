package ar.unrn.tp;

import ar.unrn.tp.api.PageService;
import ar.unrn.tp.api.PostService;
import ar.unrn.tp.domain.Page;
import ar.unrn.tp.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Tp7BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(Tp7BackendApplication.class, args);
    }

    @Autowired
    PageService pageService;
    @Autowired
    PostService postService;

    private void insertPage() {
        Page page1 = new Page("Sobre las Infusiones", "Una infusión es una bebida", "Daiana Alonso", LocalDate.now().minusDays(2));
        Page page2 = new Page("Titulo de la página", "Texto de la página", "Autor de la página", LocalDate.now().minusDays(5));

        pageService.insertPage(page1);
        pageService.insertPage(page2);
    }

    private void insertPost() {
        Post post1 = new Post("Té", "El té es una infusión", Arrays.asList("1", "2", "3"), "resumen aaaa", Arrays.asList("link"), "Daiana", LocalDate.now().minusDays(10));
        Post post2 = new Post("Té Verde", "El té verde es una infusión", Arrays.asList("1", "2"), "resumen bbbb", Arrays.asList("link"), "Xiomara", LocalDate.now().minusDays(7));
        Post post3 = new Post("Matecocido", "El matecocido es una infusión", Arrays.asList("1", "2"), "resumen ccc ", Arrays.asList("link"), "Valentin", LocalDate.now().minusDays(8));
        Post post4 = new Post("Mate", "El mate es una infusión", Arrays.asList("1", "2"), "resumen dddd", Arrays.asList("link"), "Andres", LocalDate.now().minusDays(5));
        Post post5 = new Post("Té de boldo", "El té de boldo es una infusión", Arrays.asList("1", "2"), "resumen eeee", Arrays.asList("link"), "Sabrina", LocalDate.now().minusDays(2));

        postService.insertPost(post1);
        postService.insertPost(post2);
        postService.insertPost(post3);
        postService.insertPost(post4);
        postService.insertPost(post5);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            //insertPage();
            //insertPost();
            /*Page page = pageService.findPage("654b6fef6e64905432608c37");
            System.out.println(page.getTitle());
            List<Post> posts = postService.findLastPosts();
            for (Post p : posts) {
                System.out.println(p.getTitle());
            }*/
        };
    }
}
