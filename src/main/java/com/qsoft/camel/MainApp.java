package com.qsoft.camel;

import com.qsoft.camel.persistence.control.CamelRoutingActiceWithDBThrowService;

/**
 * User: anhnt
 * Date: 9/3/13
 * Time: 4:47 PM
 */
public class MainApp
{
    public static void main(String[] args) throws Exception
    {
//        new CamelRoutingActiveWithDBOnly();
        new CamelRoutingActiceWithDBThrowService();
    }
}
