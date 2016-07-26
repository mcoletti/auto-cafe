package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.models.HzCluster;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by micahcoletti on 7/26/16.
 */
public interface HazelCastService {
    HzCluster getCluster();

    ConcurrentMap<String, String> getMap(String map);
}
