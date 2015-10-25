package org.mackristof.consumer;

import com.hazelcast.config.Config;
import com.hazelcast.core.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by christophem on 22/10/15.
 */
public class Consumer {

    public Consumer(){
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        ITopic<Date> topic = hz.getTopic("topic");

        topic.addMessageListener((Message<Date> m)-> {
                    String data = readFirstLine();
                    System.out.println("Received msg : " + m.getMessageObject().getSeconds() + " / read data : " + data);
                }
        );
        System.out.println("Subscribed");
    }
    public static void main(String [] args) throws InterruptedException {
        new Consumer();
    }

    private static String readFirstLine() {
        Path path = Paths.get(System.getenv("HOME") + File.separator + "data", "data.txt");
        //When filteredLines is closed, it closes underlying stream as well as underlying file.
        try(Stream<String> lines = Files.lines(path)
                //test if file is closed or not
//                .onClose(() -> System.out.println("data File closed"))
        ){
            Optional<String> firstLine = lines.findFirst();
            if(firstLine.isPresent()){
                return ("data readed : "+ firstLine.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return path.toString() + " unreadable";
        }
        return "data not found";
    }

}
