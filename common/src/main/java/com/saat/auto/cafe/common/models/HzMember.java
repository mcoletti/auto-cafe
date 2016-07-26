package com.saat.auto.cafe.common.models;

import java.net.InetSocketAddress;
import java.util.Map;

import lombok.Builder;

/**
 * Created by micahcoletti on 7/26/16.
 */
@Builder
public class HzMember {

    private String uuid;
    private InetSocketAddress socketAddress;
    private Map<String,Object> attributes;
}
