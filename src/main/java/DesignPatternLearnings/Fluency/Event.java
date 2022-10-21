package DesignPatternLearnings.Fluency;

import DesignPatternLearnings.Fluency.util.Encryption;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Event {

    private String eventId;
    private byte[] message;
    private String generationTimeStamp;
    private String ingestionTimeStamp;


    public Event setEventId(String MessageId){

        return  this;
    }

    public Event setMessage(String message){
        this.message = message.getBytes(StandardCharsets.UTF_8);
        return  this;
    }

    public Event setGenerationTimeStamp(String generationTimeStamp){
        return  this;
    }

    public Event setIngestionTimeStamp(String ingestionTimeStamp) {
        return  this;
    }

    public Event encrypt(){
        Encryption.encrypt(this.message);
        return  this;
    }

    public Event push(){
        return  this;
    }

    @Override
    public String
    toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", message=" + Arrays.toString(message) +
                ", generationTimeStamp='" + generationTimeStamp + '\'' +
                ", ingestionTimeStamp='" + ingestionTimeStamp + '\'' +
                '}';
    }
}
