package DesignPatternLearnings.Fluency;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class Example {

    public static void main(String[] args) {

        // fluent pattern
        Event pushedEvent = new Event()
                .setEventId(UUID.randomUUID().toString())
                .setGenerationTimeStamp(getTimeStamp())
                .setMessage("body: Test message")
                .setIngestionTimeStamp(getTimeStamp())
                .encrypt()
                .push();

        System.out.println(pushedEvent.toString());

        // fluent builder pattern

    }


    private static String getTimeStamp(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new java.util.Date());
    }
}
