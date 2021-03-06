package com.saat.auto.cafe.common;

/**
 * Created by micahcoletti on 7/20/16.
 */
public class AutoCafeConstants {
    public static final String READ_CONSITENCY = "QUORUM";
    public static final String WRITE_CONSITENCY = "QUORUM";


    public class Caches {
        public static final String VEHICLE_CACHE = "vehicleCache";
    }

    public static class UdtTypes{

        public class Address {
            public static final String NAME = "address";
        }
    }

    public static class Schema {
        public static final String KEY_SPACE = "autocafe";

        public static class VehicleImagesTbl {
            public static final String NAME = "vehicle_images";

            public class Columns {

                public static final String VEHICLE_ID = "vehicle_id";
            }
        }
    }
}
