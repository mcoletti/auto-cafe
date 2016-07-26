package com.saat.auto.cafe.common.models;

import java.util.List;

import lombok.Builder;

/**
 * Created by micahcoletti on 7/26/16.
 */
@Builder
public class HzCluster {

    private HzMember localMember;
    private List<HzMember> members;
}
