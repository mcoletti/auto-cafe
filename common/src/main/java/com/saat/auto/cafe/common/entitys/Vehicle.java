package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.VehicleModel;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

//import org.springframework.data.cassandra.mapping.Column;
//import org.springframework.data.cassandra.mapping.PrimaryKey;

//import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by mcoletti on 6/17/16.
 */
@Table(
        name = "vehicles",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@NoArgsConstructor
@Data
public class Vehicle {

    @PartitionKey
    @Column(name = "dealership_id")
    private UUID dealershipId;
    @Column(name = "stock_num")
    private String stockNum;
    @Column(name = "vin")
    private String vin;
    @Column(name = "options")
    private String options;
    @Column(name = "price")
    private double price;
    @Column(name = "invoice_amount")
    private double invoiceAmount;
    @Column(name = "ext_color")
    private String extColor;
    @Column(name = "int_color")
    private String intColor;
    @Column
    private String trim;
    @Column(name = "body_style")
    private String bodyStyle;
    @Column
    private int year;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private double mileage;
    @Column(name = "lot_location")
    private String lotLocation;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "created_user")
    private String createdUser;
    @Column
    private UUID created;
    @Column(name = "modified_user")
    private String modifiedUser;
    @Column
    private UUID modified;

    /**
     * Method to covent the Entity object to the Model Object
     * @return
     */
    public VehicleModel toModel(){
        return VehicleModel.builder()
                .dealerId(dealershipId.toString())
                .stockNum(stockNum)
                .vin(vin)
                .options(options)
                .invoiceAmount(invoiceAmount)
                .price(price)
                .extColor(extColor).intColor(intColor)
                .bodyStyle(bodyStyle).mileage(mileage)
                .make(make).model(model).year(year)
                .trim(trim)
                .lotLocation(lotLocation)
                .createdUser(createdUser)
                .createdDtm(new DateTime(UUIDGen.unixTimestamp(created)).toString())
                .modifiedUser(modifiedUser)
                .modifiedDtm(new DateTime(UUIDGen.unixTimestamp(modified)).toString()).build();

    }

}
