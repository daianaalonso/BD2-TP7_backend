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
        Page page1 = Page.builder()
                .title("Explorando la Patagonia Argentina")
                .text("Descubre la belleza natural y la rica cultura de la Patagonia Argentina...")
                .author("Daiana Alonso")
                .date(LocalDate.now().minusDays(10))
                .build();

        pageService.insertPage(page1);
    }

    private void insertPost() {
        Post post1 = Post.builder()
                .title("Explorando El Calafate: Glaciares y Aventuras")
                .text("Embárcate en una aventura única en El Calafate, donde la majestuosidad de los glaciares se combina con emocionantes actividades al aire libre. Descubre un destino que te dejará sin aliento.")
                .resume("Descubre la maravilla de los glaciares en El Calafate y las emocionantes aventuras que te esperan en esta región única.")
                .tags(Arrays.asList("El Calafate", "Glaciares", "Aventuras"))
                .relatedLinks(Arrays.asList("link1.com", "link2.com"))
                .author("Daiana Alonso")
                .date(LocalDate.now().minusDays(8))
                .build();
        Post post2 = Post.builder()
                .title("Senderismo en El Chaltén: Rutas Escénicas y Naturaleza Imparable")
                .text("Sumérgete en la maravilla natural de El Chaltén, donde cada sendero ofrece vistas panorámicas inigualables. Explora la belleza virgen de la Patagonia a cada paso.")
                .resume("Sumérgete en la belleza natural de El Chaltén mientras exploras rutas de senderismo escénicas y te conectas con la naturaleza virgen.")
                .tags(Arrays.asList("El Chaltén", "Senderismo", "Naturaleza"))
                .relatedLinks(Arrays.asList("link1.com", "link2.com"))
                .author("Andres Blanco")
                .date(LocalDate.now().minusDays(9))
                .build();
        Post post3 = Post.builder()
                .title("La Fauna Única de la Patagonia")
                .text("Adéntrate en la riqueza de la vida silvestre patagónica, desde la elegancia de los pingüinos hasta la gracia de los guanacos. Descubre un ecosistema único en el mundo.")
                .resume("Conoce la diversa vida silvestre que habita en la Patagonia, desde adorables pingüinos hasta majestuosos guanacos.")
                .tags(Arrays.asList("Fauna", "Patagonia"))
                .relatedLinks(Arrays.asList("link1.com", "link2.com"))
                .author("Sabrina Ramos")
                .date(LocalDate.now().minusDays(5))
                .build();
        Post post4 = Post.builder()
                .title("Cocina Patagónica: Sabores Tradicionales y Delicias Gastronómicas")
                .text("Explora la riqueza de la cocina patagónica, donde los sabores auténticos se mezclan con delicias gastronómicas modernas. Una experiencia culinaria que deleitará tus sentidos.")
                .resume("Explora los sabores únicos de la cocina patagónica, desde platos tradicionales hasta delicias gastronómicas modernas.")
                .tags(Arrays.asList("Gastronomía", "Patagonia", "Comida Tradicional"))
                .relatedLinks(Arrays.asList("link1.com", "link2.com"))
                .author("Daiana Alonso")
                .date(LocalDate.now().minusDays(3))
                .build();
        Post post5 = Post.builder()
                .title("Puerto Madryn: Encuentro con Ballenas y Vida Marina")
                .text("Explora las aguas de Puerto Madryn, donde el asombroso espectáculo de ballenas se une a la rica diversidad de la vida marina. Una experiencia única en el corazón de la Patagonia.")
                .resume("Explora las aguas de Puerto Madryn, donde tendrás la oportunidad de presenciar el asombroso espectáculo de ballenas y la rica vida marina.")
                .tags(Arrays.asList("Puerto Madryn", "Ballenas", "Vida Marina"))
                .relatedLinks(Arrays.asList("link1.com", "link2.com"))
                .author("Andres Blanco")
                .date(LocalDate.now().minusDays(6))
                .build();
        Post post6 = Post.builder()
                .title("Ushuaia: Fin del Mundo y Naturaleza Salvaje")
                .text("Descubre la ciudad más austral del mundo, Ushuaia, rodeada de una naturaleza salvaje y paisajes impresionantes. Adéntrate en el fin del mundo y déjate cautivar por su belleza única.")
                .resume("Descubre la ciudad más austral del mundo, Ushuaia, rodeada de una naturaleza salvaje y paisajes impresionantes.")
                .tags(Arrays.asList("Ushuaia", "Fin del Mundo", "Naturaleza Salvaje"))
                .relatedLinks(Arrays.asList("link1.com", "link2.com"))
                .author("Sabrina Ramos")
                .date(LocalDate.now().minusDays(1))
                .build();

        postService.insertPost(post1);
        postService.insertPost(post2);
        postService.insertPost(post3);
        postService.insertPost(post4);
        postService.insertPost(post5);
        postService.insertPost(post6);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            //  insertPage();
            // insertPost();
        };
    }
}
