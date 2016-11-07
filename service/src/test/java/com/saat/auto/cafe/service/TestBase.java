package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.services.CacheService;
import com.saat.auto.cafe.common.interfaces.services.HazelCastService;
import com.saat.auto.cafe.data.DataConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by micahcoletti on 7/27/16.
 */
@ContextConfiguration(classes = {DataConfiguration.class,ServiceConfiguration.class}, loader = SpringApplicationContextLoader.class)
@ActiveProfiles("dev")
public class TestBase extends AbstractTestNGSpringContextTests {


    // @Autowired
    // HazelCastService hazelCastService;
    //
    // @Autowired
    // CacheService cacheService;




}
