package camel;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcelo on 07/09/2015.
 */
public class SplitStrategy  {
    public List<Message> splitFunc(@Body String body){
        Config cfg = ConfigFactory.parseString(body);

        List<Message> answer = new ArrayList<>();
        DefaultMessage messageMeasurements = new DefaultMessage();
        messageMeasurements.setHeader("id", "measurement");

        String meas = cfg.getString("driver.latitude") +"|"+ cfg.getString("driver.longitude");
        messageMeasurements.setBody(meas);

        answer.add(messageMeasurements);

        DefaultMessage messageServer = new DefaultMessage();
        messageServer.setHeader("id", "server");
        return answer;
    }


}
