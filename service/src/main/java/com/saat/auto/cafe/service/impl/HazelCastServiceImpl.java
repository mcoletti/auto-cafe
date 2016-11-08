package com.saat.auto.cafe.service.impl;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.monitor.LocalMapStats;
import com.saat.auto.cafe.service.HazelCastProperties;
import com.saat.auto.cafe.common.interfaces.services.HazelCastService;
import com.saat.auto.cafe.common.models.HzCluster;
import com.saat.auto.cafe.common.models.HzMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by micahcoletti on 7/26/16.
 */
@Service
public class HazelCastServiceImpl implements HazelCastService {


    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public HazelCastServiceImpl(HazelCastProperties config) {

        this.hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Load up Cache Map Config settings
        final MapConfig[] mapConfig = new MapConfig[1];
        config.getCaches().forEach(cache -> {
            mapConfig[0] = new MapConfig();
            mapConfig[0].setName(cache.getName());
            mapConfig[0].setTimeToLiveSeconds(cache.getTtl());
            mapConfig[0].setMaxIdleSeconds(cache.getMaxIdleSecs());
            this.hazelcastInstance.getConfig().addMapConfig(mapConfig[0]);
        });

    }

    @Override
    public HzCluster getCluster() {


        Cluster cluster = hazelcastInstance.getCluster();


        HzCluster.HzClusterBuilder hzClusterBuilder = HzCluster.builder();
        Member member = cluster.getLocalMember();
        HzMember hzMember = HzMember.builder()
                .attributes(member.getAttributes())
                .socketAddress(member.getSocketAddress()).build();
        hzClusterBuilder.localMember(hzMember);

        List<HzMember> members = new ArrayList<>();
        cluster.getMembers().forEach(m -> {
                members.add(HzMember.builder()
                        .attributes(m.getAttributes())
                        .socketAddress(m.getSocketAddress()).build());
        });

        return hzClusterBuilder.build();

    }

    @Override
    public LocalMapStats getLocalMapStats(String map) {
        return hazelcastInstance.getMap(map).getLocalMapStats();
    }

    @Override
    public ConcurrentMap<String, String> getMap(String map){


        return hazelcastInstance.getMap(map);

    }

    @Override
    public Set<Member> getHazelcastMembers() {
        return hazelcastInstance.getCluster().getMembers();
    }
}
