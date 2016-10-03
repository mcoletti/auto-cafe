package com.saat.auto.cafe.common.interfaces.services;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.monitor.LocalMapStats;
import com.saat.auto.cafe.common.models.HzCluster;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by micahcoletti on 7/26/16.
 */
public interface HazelCastService {

    HzCluster getCluster();

    LocalMapStats getLocalMapStats(String map);

    ConcurrentMap<String, String> getMap(String map);

    Set<Member> getHazelcastMembers();
}
