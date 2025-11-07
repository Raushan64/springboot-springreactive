package com.kumar.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {


    @Test
    public void testMono() {

        //onComplete() - workflow of reactive stream
        Mono<?> monoString1 = Mono.just("kumar")
                .log();
        monoString1.subscribe((e) -> System.out.println(e));

        //onError() - workflow of reactive stream
        Mono<?> monoString2 = Mono.just("kumar")
                .then(Mono.error(new RuntimeException("Exception occured in Mono")))
                .log();
        monoString2.subscribe((e) -> System.out.println(e), (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {

        //onComplete() - workflow of reactive stream
        Flux<String> fluxString1 = Flux.just("Spring", "Spring Boot", "Hibernate", "microservice")
                .concatWithValues("AWS")
                .log();
        fluxString1.subscribe((e) -> System.out.println(e), (e) -> System.out.println(e.getMessage()));

        //onError() - workflow of reactive stream
        Flux<String> fluxString2 = Flux.just("Spring", "Spring Boot", "Hibernate", "microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occured in Flux")))
                .concatWithValues("cloud")
                .log();

        fluxString2.subscribe((e) -> System.out.println(e), (e) -> System.out.println(e.getMessage()));
    }
}
