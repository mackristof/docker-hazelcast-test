package org.mackristof.producer;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

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
            topic.publish(new Date());
            System.out.println("Published");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void main(String [] args) throws InterruptedException {
        new Producer();
        System.out.println("Bye Bye !!");
        System.exit(0);
    }



}
