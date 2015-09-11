package camel;


import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {
    private String port = "";
    private final String ACCESS_TOKEN = "67b2517efd721ac7f0faf66b4341f204f73a90197c22f861c7fd4b74d348370b";

    public MyRouteBuilder(String port){
        this.port = port;
    }
    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
        /*from("file:src/data?noop=true")
            .choice()
                .when(xpath("/person/city = 'London'"))
                    .log("UK message")
                    .to("file:target/messages/uk")
                .otherwise()
                    .log("Other message")
                    .to("file:target/messages/others");*/

        from("restlet:http://localhost:" + port + "/measurements?restletMethod=post").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                String query = (String)exchange.getIn().getHeader("CamelHttpQuery");
                if(query.contains(ACCESS_TOKEN)) {
                    String contentType = (String)exchange.getIn().getHeader("Content-Type");
                    if(!contentType.equalsIgnoreCase("application/json")){
                        exchange.getOut().setHeader("CamelHttpResponseCode", 415);
                    }else {
                        exchange.getOut().setHeader("id", 1);
                        exchange.getOut().setBody(exchange.getIn().getBody());
                        exchange.getOut().setHeader("CamelHttpResponseCode", 204);
                    }
                }else{
                    exchange.getOut().setHeader("CamelHttpResponseCode", 401);
                }
            }
        }).setExchangePattern(ExchangePattern.InOnly).to("activemq:taxis99");
    }

}
