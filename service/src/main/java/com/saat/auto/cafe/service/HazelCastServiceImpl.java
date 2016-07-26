package com.saat.auto.cafe.service;

import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.interfaces.HazelCastService;
import com.saat.auto.cafe.common.models.HzCluster;
import com.saat.auto.cafe.common.models.HzMember;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;

/**
 * Created by micahcoletti on 7/26/16.
 */
public class HazelCastServiceImpl implements HazelCastService {


    @Inject
    private HazelcastInstance hazelcastInstance;

    public HazelCastServiceImpl(HazelcastInstance instance) {

        hazelcastInstance = instance;
        MapConfig mapConfig = new MapConfig();
        mapConfig.setMaxIdleSeconds(14400);
        mapConfig.setTimeToLiveSeconds(28800);
        mapConfig.setName(AutoCafeConstants.Caches.VEHICLE_CACHE);
        mapConfig.setInMemoryFormat(InMemoryFormat.OBJECT);
        hazelcastInstance.getConfig().addMapConfig(mapConfig);

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
    public ConcurrentMap<String, String> getMap(String map){


        return hazelcastInstance.getMap(map);

    }
}
