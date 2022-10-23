package ExceptionHandling;

import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Main {

    private static List<String> readFile(int i) throws IOException {
        return Files.readAllLines(Path.of("word-occurrence.csv"));
    }

    private static CompletableFuture<Void> getResult(Integer i){
        return CompletableFuture.supplyAsync(() -> new Random().nextInt()*i)
                .thenApply(a->""+a*2)
                .exceptionally(throwable -> throwable.getMessage())
                .thenAccept(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4);
//
//        integers.stream()
//                .map(CheckedFunction1.liftTry(i->readFile(i)))
//        .forEach(a->a.toString());


        List<CompletableFuture<Void>> collect = integers.stream()
               // .peek(System.out::println)
                .map(a -> getResult(a))
                .collect(Collectors.toList());


    }
}
