package co.com.curso.springbootreactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringbootReactorApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SpringbootReactorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*Flux es un observable
        doOnNext -> Hacer algo siguiente, se ejecuta cuando el flujo notifica que ha llegado un elemento
        Cada vez que se emita un elementos lo mostramos por consola*/
        Flux<String> nombres = Flux.just("Angelica", "Jimena", "Elena", "Jose", "Alvaro")
                .doOnNext(System.out::println);

        /*Si no nos subcribimos el método no hará nada*/
        nombres.subscribe();

        /*
        * Otra manera de usar el subscribe
        * */
        nombres.subscribe(log::info);

        Flux<String> apellidos = Flux.just("OROZCO", "MESA", "RUIZ","HOLI", "VALENCIA")
                .doOnNext(e -> {
                    if (e.isEmpty()) {
                        throw new RuntimeException("Los apellidos no pueden estar vacíos");
                    }
                        System.out.println(e);
                });

        apellidos.subscribe(e -> log.info(e), error -> log.error(error.getMessage()));

        /*
        * OnComplete -> Dar por finalizado un observable
        * */

        apellidos.subscribe(e -> log.info(e),
                error -> log.error(error.getMessage()),
                new Runnable() {
                    @Override
                    public void run() {
                        log.info("Ha finalizado la ejecución del observable con éxito");
                    }
                });
    }
}
