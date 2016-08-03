package com.saat.auto.cafe.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by mcoletti on 6/1/16.
 */
@Configuration
@ComponentScan(value = {"com.saat.auto.cafe.*"})
@PropertySource("classpath:application.yml")
public class Beans {

//    @Bean
//    HazelCastService hazelCastService(){
//
//        return new HazelCastServiceImpl(null);
//
//    }

//
//    @Autowired
//    VehicleDao vehicleDao;
//
//
//    @Bean
//    HazelCastService hazelCastService(){
//
//        return new HazelCastServiceImpl(Hazelcast.newHazelcastInstance());
//    }
//
//
//    @Bean
//    CacheService cacheService(){
//        return new HazelCastCacheServiceImpl(hazelCastService());
//    }
//
//    @Bean
//    VehicleService vehicleService(){
//        return new VehicleServiceImpl(cacheService(),vehicleDao);
//    }
//
//    @Autowired
//    HazelCastService hazelCastService;
//
////    @Bean
////    VehicleDao vehicleDao(){
////        return new VehicleDaoImpl(cassandraInstance());
////    }
//
//
//
//    /**
//     * Bean for initializing a local hazelcast instance
//     * @return
//     */
//    @Bean
//    public HazelcastInstance hazelcastInstance(){
//        return Hazelcast.newHazelcastInstance();
//    }
//
////    /**
////     * Bean for initializing the HazelCast Service
////     * @return
////     */
////    @Bean
////    public HazelCastService getHazelCastService(){
////        return new HazelCastServiceImpl(hazelcastInstance());
////    }
//
//    @Bean
//    CacheService cacheService(){
//        return new HazelCastCacheServiceImpl("autoCafeCache",new Gson());
//    }
//
//    // Initialize services
//
//    @Bean
//    VehicleService vehicleService(){
//        return new VehicleServiceImpl(cacheService(),vehicleDao);
//    }

}
