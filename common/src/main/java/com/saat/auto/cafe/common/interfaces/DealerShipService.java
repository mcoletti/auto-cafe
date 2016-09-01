package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.models.DealerShipModel;

/**
 * Created by micahcoletti on 9/1/16.
 */
public interface DealerShipService {


    DealerShipModel upsertDealserShip(DealerShipModel dealerShipModel);

    DealerShipModel get(String dealerId);


}
