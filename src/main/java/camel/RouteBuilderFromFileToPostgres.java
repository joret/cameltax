package camel;


import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class RouteBuilderFromFileToPostgres extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        final String outDir = "f:\\teste";
        from("file:" + outDir).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                String fileName = (String)exchange.getIn().getHeader("CamelFileAbsolutePath");


                exchange.getOut().setBody("copy measures FROM " + fileName);
            }
        }).log(LoggingLevel.INFO, "Sending bulk insert").to("jdbc:myDataSource");
    }

}
