package camel;


import org.apache.camel.main.Main;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
     //   AbstractXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //TODO URGENT restriction of threads for activeMQ. Persisance for Aggregation Stragtegy, or a way to ack only after the bulk insert
        String db = "taxis";
        String url = "jdbc:postgresql://localhost:5432/" + db;
        DataSource dataSource = setupDataSource(url);


        Main main = new Main();
        main.bind("myDataSource", dataSource);
        main.enableHangupSupport();

        main.getOrCreateCamelContext().addComponent("activemq", activeMQComponent("vm://localhost:61616?broker.persistent=true"));
        main.addRouteBuilder(new MyRouteBuilder("8181"));
        main.addRouteBuilder(new RouteBuilderFromBrokerToFile());
        main.addRouteBuilder(new RouteBuilderFromFileToPostgres());
        main.run(args);

        //camelContext.addComponent();
    }

    private static DataSource setupDataSource(String connectURI) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername("webradar");
        ds.setPassword("webr@d@rdd38");
        ds.setUrl(connectURI);
        return ds;
    }

}

