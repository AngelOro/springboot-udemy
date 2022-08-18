package co.com.curso.springbootreactor.map;

import co.com.curso.springbootreactor.SpringbootReactorApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.Locale;

@SpringBootApplication
public class Map implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Map.class);

    public static void main(String[] args) {
        SpringApplication.run(Map.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        * Para realizar algún tipo de transformación a los datos, se deben usar los operadores
        *
        * Map -> Recibe cada elemento, transforma los datos y retorna una nueva instancia con los nuevos datos
        *        no modifica los datos originales, mantiene el estado de los datos intactos, inmutable
        * */

        Flux<String> apellidos = Flux.just("OROZCO", "MESA", "RUIZ","HOLI", "VALENCIA")
                .doOnNext(e -> {
                    if (e.isEmpty()) {
                        throw new RuntimeException("Los apellidos no pueden estar vacíos");
                    }
                    System.out.println(e);
                })
                .map(String::toLowerCase);

        apellidos.subscribe(e -> log.info(e), error -> log.error(error.getMessage()));

    }
}
