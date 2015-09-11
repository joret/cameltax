package camel;


import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.copy.CopyManager;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.util.logging.Level;

/**
 * A Camel Java DSL Router
 */
public class RouteBuilderFromBrokerToFile extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        String outDir = "f:\\teste";
        from("activemq:taxis99").split().method("SplitStrategy", "splitFunc").aggregate(header("id"), new AgreggationStrategy1()).completionSize(2).
                completionTimeout(60000).log(LoggingLevel.INFO, "Process bulk copy").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
/*                CopyManager copyManager = CopyManager.

                byte[]
                        insert_values="1|10\n2|20\n3|30\n4|40".getBytes();

//These are my connection parameters
                String url = "jdbc:edb://SAMEER:5444/edb";
                String user = "enterprisedb";
                String password = "ashnik";
                CopyIn cpIN=null;
                String driver="com.edb.Driver";
                try{
                    Class.forName(driver);
                    con = DriverManager.getConnection(
                            url, user, password);

                    CopyManager cm = new CopyManager(
                            (BaseConnection) con);
////*Copy command
// Replace public.test(col1, col2) with you table Name and
//* replace | with the delimeter of you choice.
//* It should be same as the delimeter used in defining
//* the variable byte[] insert_values
//*
                    cpIN=
                            cm.copyIn(
                                    "COPY public.test(col1, col2) FROM STDIN WITH DELIMITER '|'"
                            );

                    cpIN.writeToCopy(insert_values,0,insert_values.length);
                    cpIN.endCopy();
                    System.out.println("Below Values are inserted");
                    System.out.println(new String(insert_values));
                }
                catch (SQLException ex)
                {
                    Logger lgr =
                            Logger.getLogger(CopyFrom.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
                }
                catch (ClassNotFoundException ex)
                {
                    Logger lgr =
                            Logger.getLogger(CopyFrom.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
                }
                finally
                {
                    try
                    {
                        if (con!=null)
                            con.close();
                    }
                    catch (SQLException ex)
                    {
                        Logger lgr =
                                Logger.getLogger(CopyFrom.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);
                    }}}*/
            }
        });//.to("jdbc:myDataSource");
    }

}
