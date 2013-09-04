package com.qsoft.test;

import com.qsoft.camel.business.EquipmentService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * User: anhnt
 * Date: 9/4/13
 * Time: 8:46 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class EquipmentServiceTest
{
    @Autowired
    EquipmentService equipmentService;

    @Test
    public void testAddEquipment()
    {
        Assert.assertNotNull(equipmentService.add("anhnt").getWebId());
    }
}
