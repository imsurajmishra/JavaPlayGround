package DebugExcercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

import org.junit.Assert;

public class Main {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
       // getRequest();
        postRequest();

    }

    private static void postRequest() throws IOException, InterruptedException {
        String baseUrl = "https://jsonplaceholder.typicode.com/posts";

        TODO todo = new TODO();
        todo.setId(1);
        todo.setTitle("test");
        todo.setUserId(11);
        todo.setBody("more tests");

        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(todo);

        List<TODO> todos = Arrays.asList(todo,todo);

        String data1 = objectMapper.writeValueAsString(todos);

        System.out.println("data1 "+data1);


        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(data);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .POST(bodyPublisher)
                .headers()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        Assert.assertEquals(response.statusCode(),201);


        readFileNIO();

//        InputStream in = new FileInputStream("/Users/surajmishra/workspace/spring_boot/java-interview-prep/output.json");
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//        bufferedReader.readLine();
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/surajmishra/workspace/spring_boot/java-interview-prep/output.json");
        fileOutputStream.write(body.getBytes(StandardCharsets.UTF_8));

        Path path = Path.of("/Users/surajmishra/workspace/spring_boot/java-interview-prep/out1.json");

        try(BufferedWriter bufferedWriter = getBufferedReader(path)){
            bufferedWriter.write(body);
        }

    }

    private static BufferedWriter getBufferedReader(Path path) throws IOException {
        return Files.newBufferedWriter(path);
    }

    private static void readFileNIO() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of("/Users/surajmishra/workspace/spring_boot/java-interview-prep/README.md"));
        String read = "";
        StringBuilder content = new StringBuilder();
        while ((read = reader.readLine())!=null){
            content.append(read).append("\n");
        }

        System.out.println(content);
    }

    private static void getRequest() throws IOException, InterruptedException, ExecutionException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .header("", "")
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        // System.out.println(response.body());

        ObjectMapper objectMapper = new ObjectMapper();
        TODO[] todos = objectMapper.readValue(response.body(), TODO[].class);

        Arrays.stream(todos).forEach(a-> System.out.println(a.toString()));


        CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture = HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response1 = httpResponseCompletableFuture.get();
        System.out.println(response1);

        
        String[] minorParts = {};
        Arrays.stream(minorParts).collect(Collectors.joining("."));
    }



}
