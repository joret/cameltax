package camel;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * Created by Marcelo on 07/09/2015.
 */
public class AgreggationStrategy1 implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            // the first time we only have the new exchange so it wins the first round


            return newExchange;
        }
       /*  String oldPrice = oldExchange.getIn().getBody(String.class);
        String newPrice = newExchange.getIn().getBody(String.class);

        oldExchange.getIn().setBody(newPrice + "," + oldPrice);
        */

      //  oldExchange.getIn()




        String insert = "INSERT INTO measurements(\n" +
                "             x, y"+/*, created_at, updated_at, meastime, timezone_offset, \n" +
                "            driverid, imsi, imei, msisdn, sim_operator, technology, network_operator, \n" +
                "            data_calc_throughput, data_calc_latency*/")\n" +
                "    VALUES ( 1, 1"+/*, ?, ?, ?, ?, \n" +
                "            ?, ?, ?, ?, ?, ?, ?, \n" +
                "            ?, ?*/ ");\n";
     //   oldExchange.getOut().setBody(insert);
        oldExchange.getIn().setBody(insert);
        return oldExchange;
    }
}
