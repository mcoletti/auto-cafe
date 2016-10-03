package com.saat.auto.cafe.common.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcoletti on 5/18/16.
 */
public class SchemaConstants {

    public static final String DB = "autocafe";

    public static class Common{
        public static class Columns {
            public static final String Id = "id";
            public static final String CreatedBy = "created_by";
            public static final String CreatedDtm = "created_dtm";
            public static final String ModifiedBy = "modified_by";
            public static final String ModifiedDtm = "modified_dtm";

        }
    }

    public static class ClientsTable {

        public static final String NAME = String.format("%s.%s",DB,"clients");

        public static class Columns {
            public static final String ClientName = "client_name";
        }

    }

    public static class ClientsLocationTable {

        public static final String NAME = String.format("%s.%s",DB,"client_locations");

        public static class Columns {
            public static final String ClientId = "client_id";
            public static final String Location = "locations";
        }

    }

    public static class VehicleDetailsTable {
        public static final String NAME = String.format("%s.%s",DB,"vehicle_details");


        public static class Columns {
            public static final String KeyName = "key_name";
            public static final String StockNum = "stock_num";
            public static final String ExtColor = "ext_color";
            public static final String IntColor = "int_color";
            public static final String Trim = "trim";
            public static final String VehiclePrice = "price";
            public static final String VehicleMileage = "mileage";
            public static final String Location = "locations";
            public static final String Category = "category";
            public static final String ManufactureId = "manufacture";
            public static final String VehicleModelId = "model";
            public static final String VehicleYearId = "year_value";
            public static final String BodyStyleId = "body_style";
            public static final String ClientId = "client_id";

        }

        /**
         * Method to Get a List of Columns names
         * @return
         */
        public static String[] GetColumnNames() {

            List<String> columnNames = new ArrayList<>();
            columnNames.add(Common.Columns.CreatedBy);
            columnNames.add(Common.Columns.CreatedDtm);
            columnNames.add(Common.Columns.ModifiedBy);
            columnNames.add(Common.Columns.ModifiedDtm);
            columnNames.add(Columns.KeyName);
            columnNames.add(Columns.StockNum);
            columnNames.add(Columns.ExtColor);
            columnNames.add(Columns.IntColor);
            columnNames.add(Columns.Trim);
            columnNames.add(Columns.VehiclePrice);
            columnNames.add(Columns.VehicleMileage);
            columnNames.add(Columns.Category);
            columnNames.add(Columns.ManufactureId);
            columnNames.add(Columns.VehicleModelId);
            columnNames.add(Columns.VehicleYearId);
            columnNames.add(Columns.BodyStyleId);
            columnNames.add(Columns.ClientId);
            columnNames.add(Columns.Location);
            String[] returnArray = new String[columnNames.size()];
            returnArray = columnNames.toArray(returnArray);
            return returnArray;
        }





    }


    public static class CatalogImagesTable {
        public static final String NAME = String.format("%s.%s",DB,"catalog_images");

        public static class Columns {
            public static final String CdnUrlLoc = "cdn_url_loc";
            public static final String VehicleInvId = "vehicle_inventory_id";
            public static final String ImageTypeId = "image_type_id";
        }
    }
}
