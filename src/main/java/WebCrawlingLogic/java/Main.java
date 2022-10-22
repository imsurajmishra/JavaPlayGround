package WebCrawlingLogic.java;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main1 {

    Set<String> visited = new HashSet<>();
    Map<String, List<String>> links = getLinks();

    public static void main(String[] args) {
    }

    private void crawl(String url){
        if(visited.contains(url)){
            return;
        }

        System.out.println("crawling url: " + url);

        for(String link: links.get(url)){

            visited.add(link);
            crawl(link);
        }
    }

    private Map<String, List<String>> getLinks(){
        return Map.of("https://abc.com", List.of("https://pqr.com", "https://tuf.com")
                , "https://pqr.com", List.of("https://mmm.com", "https://nnn.com")
                , "https://mmm.com", List.of("https://abc.com", "https://pqr.com"));
    }
}
