package com.saat.auto.cafe.common.models;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by mcoletti on 5/17/16.
 */
@Table
@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel(description = "The Clients Data Entity", parent = BaseModelExtended.class)
public class Clients {
    @PrimaryKey
    @ApiModelProperty(position = 1, required = true, value = "The Clients Id")
    private UUID id;
    @Column(value = "client_name")
    @ApiModelProperty(position = 2, required = true, value = "The Clients Name")
    private String clientName;
    @Column(value = "created_by")
    @ApiModelProperty(position = 3, required = true, value = "Created By")
    private String createdBy;
    @Column(value = "created_dtm")
    @ApiModelProperty(position = 4, required = true, value = "Created Date")
    private Date createdDtm;
    @Column(value = "modified_by")
    @ApiModelProperty(position = 5, required = true, value = "Modified By")
    private String modifiedBy;
    @Column(value = "modified_dtm")
    @ApiModelProperty(position = 6, required = true, value = "Modified Date")
    private Date modifiedDtm;
}
