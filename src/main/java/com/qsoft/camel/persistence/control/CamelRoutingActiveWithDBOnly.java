package com.qsoft.camel.persistence.control;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * User: anhnt
 * Date: 9/4/13
 * Time: 4:11 PM
 */
public class CamelRoutingActiveWithDBOnly
{
    public CamelRoutingActiveWithDBOnly() throws Exception
    {
        ApplicationContext springContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        CamelContext camelContext = springContext.getBean(CamelContext.class);

        camelContext.addRoutes(new RouteBuilder()
        {
            @Override
            public void configure() throws Exception
            {
                from("jetty:http://localhost:9123/equipment").process(new Processor()
                {
                    @Override
                    public void process(Exchange exchange) throws Exception
                    {
                        HttpMessage httpMessage = (HttpMessage) exchange.getIn();
                        Object body = exchange.getIn().getBody();

                        log.debug("Retrieving the parameters...");
                        Map<String, Object> parameters = httpMessage.getRequest().getParameterMap();
                        for (Map.Entry<String, Object> entry : parameters.entrySet())
                        {
                            log.debug("Entry: " + entry);
                            Object firstValue = null;
                            if (entry.getValue().getClass().isArray())
                            {
                                int length = Array.getLength(entry.getValue());
                                for (int i = 0; i < length; i++)
                                {
                                    Object arrayElement = Array.get(entry.getValue(), i);
                                    firstValue = arrayElement;
                                    break;
                                }
                            }
                            log.debug("Parameter Name: " + entry.getKey()
                                    + ", Parameter value: " + (firstValue != null ? firstValue : entry.getValue()));
                            exchange.setProperty(entry.getKey(), firstValue);
                        }
                    }
                }).to("direct:retrieveEquipmentByManufactor");

                from("direct:retrieveEquipmentByManufactor").process(new Processor()
                {
                    @Override
                    public void process(Exchange exchange) throws Exception
                    {
                        log.debug("Retrieve the equipment for: " + exchange);
                        exchange.getIn().setHeader("value", property("value"));
//                        exchange.getIn().setBody(constant("SELECT * FROM equipment WHERE value = 'anhnt'"));
                        exchange.getIn().setBody(constant("SELECT * FROM equipment"));
                        System.out.println(exchange.getIn().getBody());
                    }
                })
                        .to("jdbc:rideCamelDS").process(new Processor()
                {
                    @Override
                    public void process(Exchange exchange) throws Exception
                    {
                        log.debug(exchange.toString());
                    }
                });
            }
        });
        camelContext.start();
        Thread.sleep(100000);
        camelContext.stop();
    }
}
