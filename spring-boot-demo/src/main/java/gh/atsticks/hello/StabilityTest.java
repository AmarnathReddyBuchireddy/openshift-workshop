package gh.atsticks.hello;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class StabilityTest {

    public static void main(String[] args) {
        Map<String, AtomicInteger> accessCounters = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long nextTimestamp = 0;
        while(true){
            executorService.submit(() -> access(accessCounters));
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(System.currentTimeMillis() >= nextTimestamp){
                printAccessStats(accessCounters);
                nextTimestamp = System.currentTimeMillis() + 1000L;
            }
        }
    }

    private static void printAccessStats(Map<String, AtomicInteger> accessCounters) {
        for(Map.Entry<String, AtomicInteger> counter: accessCounters.entrySet()){
            System.out.println(counter.getKey() + " : " + counter.getValue().get());
        }
        System.out.println("--------------");
    }

    private static void access(Map<String, AtomicInteger> accessCounters){
        try(InputStream is = new URL("http://minishift.local/host").openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));){
            String result = reader.readLine();
            accessCounters.computeIfAbsent(result, (key) -> new AtomicInteger()).incrementAndGet();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
