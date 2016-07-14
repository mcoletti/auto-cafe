package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.exceptions.VehicleInvException;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.interfaces.VehicleService;
import com.saat.auto.cafe.common.models.CatalogImage;
import com.saat.auto.cafe.common.models.VehicleDetails;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by mcoletti on 6/1/16.
 */
public class VehicleServiceImpl implements VehicleService {



    @Inject
    private VehicleDao vehicleDao;


    @Override
    public List<CatalogImage> getCatalogImages(int vehicleInventoryId) throws VehicleInvException {
        return null;
    }

    @Override
    public List<CatalogImage> getCatalogImages(String vehicleKeyName) throws VehicleInvException {
        return null;
    }

    @Override
    public VehicleDetails getVehicleInventory(int id) throws VehicleInvException {
        return null;
    }

    @Override
    public VehicleDetails getVehicleInventory(String keyName) throws VehicleInvException {
        return null;
    }
}
