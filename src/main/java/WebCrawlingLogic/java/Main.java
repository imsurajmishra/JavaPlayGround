package WebCrawlingLogic.java;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    // visited set to avoid cycle in crawling
    static Set<String> visited = new HashSet<>();
    // target links
    static Map<String, List<String>> links = getTargetLinks();

    // client code
    public static void main(String[] args) {
        for(Map.Entry<String,List<String>> link:links.entrySet()){
                crawl(link.getKey());
        }
    }

    // link is not visited
    private static boolean linkIsNotVisited(Map.Entry<String, List<String>> link) {
        return !visited.contains(link.getKey());
    }

    // crawl links recursively
    private static void crawl(String url){
       // if this link is already visited then no need to return, avoid having cycle
        if(visited.contains(url)){
            return;
        }

        // crawling this url
        System.out.println("crawling url: " + url);

        // marking this url visited since we have already crawled it
        visited.add(url);

        // if this link is in the target list
        // then crawl all the urls listed on the page
        if(links.containsKey(url)) {
            for ( String link : links.get(url) ) { // make crawl call for list of links
                crawl(link);
            }
        }
    }

    /**
     * get target links
     * @return
     */
    private static Map<String, List<String>> getTargetLinks(){
        return Map.of("https://abc.com", List.of("https://pqr.com", "https://tuf.com")
                , "https://pqr.com", List.of("https://mmm.com", "https://nnn.com")
                , "https://mmm.com", List.of("https://abc.com", "https://pqr.com"));
    }
}
