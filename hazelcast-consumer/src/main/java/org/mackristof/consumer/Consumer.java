package org.mackristof.consumer;

import com.hazelcast.config.Config;
import com.hazelcast.core.*;

import java.util.Date;

/**
 * Created by christophem on 22/10/15.
 */
public class Consumer {

    public Consumer(){
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        ITopic<Date> topic = hz.getTopic("topic");
        topic.addMessageListener((Message<Date> m)->
                        System.out.println("Received: "+m.getMessageObject())
        );
        System.out.println("Subscribed");
    }
    public static void main(String [] args) throws InterruptedException {
        new Consumer();
    }


}
