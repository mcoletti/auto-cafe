package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.VehicleInvException;
import com.saat.auto.cafe.common.models.CatalogImage;

import java.util.List;

/**
 * Created by mcoletti on 6/7/16.
 */
public interface CatalogImagesDao {

    /**
     * Get a Catalog Images Record
     * @param id the id to get
     * @return an instance of the CatalogImage object
     */
    CatalogImage get(int id);

    /**
     * Add or Update a CatalogImage record
     * @param catalogImage the catalog images object to add or update
     * @return a new or update instance of the CatalogImage object
     */
    CatalogImage upsertCatalogImages(CatalogImage catalogImage);

    /**
     * Gets a list of catalog images based of Id
     *
     * @param vehicleInventoryId the unique Id in the inventory Table
     */
    List<CatalogImage> getCatalogImages(int vehicleInventoryId) throws VehicleInvException;




}
