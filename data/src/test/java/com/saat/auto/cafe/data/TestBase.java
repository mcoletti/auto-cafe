package com.saat.auto.cafe.data;


import com.saat.auto.cafe.common.interfaces.CassandraInstance;

import org.apache.cassandra.utils.UUIDGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mcoletti on 6/1/16.
 */
@ContextConfiguration(classes = DataConfiguration.class)
public class TestBase extends AbstractTestNGSpringContextTests {



    @Autowired
    public CassandraInstance cassandraInstance;



    @Test
    public void testGetTImeUUid(){


        UUID timeUUid = UUIDGen.getTimeUUID();
        assertThat(timeUUid).isNotNull();




    }


}