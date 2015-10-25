package org.mackristof.producer;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by christophem on 22/10/15.
 */
public class Producer {

    public Producer() throws InterruptedException {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        ITopic<Date> topic = hz.getTopic("topic");
        for (int i = 0; i < 1000; i++) {
            Date date = new Date();
            writeDataInFile(String.valueOf(date.getSeconds()));
            topic.publish(date);
            System.out.println("Published data : " + date.getSeconds());
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void main(String [] args) throws InterruptedException {
        new Producer();
        System.out.println("Bye Bye !!");
        System.exit(0);
    }

    private static void writeDataInFile(String dataToWrite){
        Path path = Paths.get(System.getenv("HOME")+ File.separator + "data", "data.txt");
        OpenOption[] options = new OpenOption[] { StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE,
                StandardOpenOption.DSYNC
        };

        try {
            Files.write(path, (dataToWrite + "/n").getBytes(),options);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("could not write to " + path.toString());
        }
    }


}
