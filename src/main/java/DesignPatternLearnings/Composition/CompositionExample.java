package DesignPatternLearnings.Composition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CompositionExample {


    public static void main(String[] args) {

        String inputFile = "/Users/surajmishra/workspace/spring_boot/test.txt";

        // read file
        Function<String, List<String>> fileRead = CompositionExample::FileRead;
        // validate records
        Predicate<String> isEmptyOrNull = (a) -> a == null && a.isEmpty();
        Function<List<String>,  List<String>> validate = (a) -> validateRecords(a, isEmptyOrNull);
        // compute metrics
        Function<List<String>, Map<String, Long>> computeOccurence = CompositionExample::computeOccurence;
        // write to local file
        Function<Map<String, Long>, Boolean> writeToLocal = CompositionExample::write;

        // setup pipeline
        Function<String, Boolean> pipeline = fileRead
                .andThen(validate)
                .andThen(computeOccurence)
                .andThen(writeToLocal);

        Boolean result = pipeline.apply(inputFile);
        System.out.println("does pipeline ran successfully ? : " + result);
    }

    private static boolean write(Map<String, Long> a) {
        boolean result = false;
        try {
           result = writeToLocal(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<String> FileRead(String FilePath){
        List<String> readLines = null;
        try {
            readLines = Files.readAllLines(Path.of(FilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("readLines : "+readLines);
        return readLines;
    }

    private static List<String> validateRecords(List<String> content, Predicate<String> logic){
        return content.stream()
                .filter(logic.negate())
                .collect(Collectors.toList());
    }

    private static Map<String, Long> computeOccurence(List<String> content){
        return content
                .stream()
                .peek(System.out::println)
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting())
                );
    }

    private static boolean writeToLocal(Map<String, Long> occurrenceMap) throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/surajmishra/workspace/spring_boot/word-occurrence.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        boolean isCompleteWrite = true;
        for(Map.Entry<String, Long> entry: occurrenceMap.entrySet()){
            try {
                bufferedWriter.write(entry.getKey()+","+entry.getValue());
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
                isCompleteWrite = false;
            }
        }
        bufferedWriter.close();
        return isCompleteWrite;
    }
}
