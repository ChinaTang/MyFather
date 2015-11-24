package tang.di.database;

/**
 * Created by tangdi on 2015/11/13.
 */
public class PublicWorld {

    public static final String DATABASE_NAME = "TangdiSQL.db";

    public static final String TABLE_NAME_1 = "ALL_DATA";

    public static final String TABLE1_KEY = "_id";

    /**
     *将通过地理位置查询到下一个表格
     */
    public static final String TABLE1_OTHER_KEY = "Adress";
    /**------------------------------------------------------------------------------**/

    public static final String TABLE_NAME_2 = "BIG_DATA";

    public static final String TABLE2_KEY = "_id";

    public static final String TABLE2_KEY_GOLD_WHERE_COLUM = "Adress";

    /**
     * 将使用ＵＵＩＤ作为唯一标志来查找另一张表
     */
    public static final String TABLE2_KEY_GOLD_DATA_COLUM = "UUID";

    public static final String TABLE2_KEY_GOLD_HEAT_COLUM = "HEAT_DATE";

    public static final String TABLE2_KEY_GOLD_FECUNDATION_COLUM = "FECUNDATION_DATE";

    public static final String TABLE2_KEY_GOLD_BRITH_DAY_COLUM = "BRITH_DAY";

    /**
     * 是否需要这个字段
     */
    public static final String TABLE2_KEY_GOLD_VISIBALE = "Visibale";
    /**-----------------------------------------------------------------------------**/

    public static final String TABLE_NAME_3 = "LITTLE_DATA";

    public static final String TABLE3_KEY = "_id";

    /**
     * 通过上一张表的UUID查找到这条数据
     */
    public static final String SELECT_KEY = "UUID";

    public static final String TABLE3_ADRESS = "ADRESS";

    public static final String TABLE3_KEY_GOLD_NUMBER = "Number";

    public static final String TABLE3_KEY_GOLD_LITTLE_BRITH_COLUM = "BRITH_DAY";

    public static final String TABLE3_KEY_GOLD_CASTRAT_TIME_COLUM = "Castrat_date";

    public static final String TABLE3_KEY_GOLD_OFFTAKE_DATE_COLUM = "Offtake_date";

    public static final String TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM = "No_milk_date";

    public static final String TABLE3_KEY_GOLD_IS_HE_COLUM = "IsBoolean";

    public static final String TABLE3_KEY_GOLD_OFFTAKE_NUMBER_COLUM = "Offtake_number";

    public static final String TABLE3_KEY_GOLD_OFFTAKE_PRICE_COLUM = "Offtake_proice";

    public static final String TABLE3_KET_GOLD_IS_NO_MILK = "IS_NO_MILK";

    public static final String TABLE3_IS_SELL = "SELL";

    /**
     * 独立的一张表，不与任何表关联，从lp表中取出数据
     */

    public static final String TABLE_NAME_4 = "HE_BING_TABLE";

    public static final String TABLE4_KEY = "_id";

    public static final String TABLE4_ADRESS = "ADRESS";

    public static final String TABLE4_KEY_GOLD_NUMBER = "Number";

    public static final String TABLE4_KEY_GOLD_CASTRAT_TIME_COLUM = "Castrat_date";

    public static final String TABLE4_KEY_GOLD_OFFTAKE_DATE_COLUM = "Offtake_date";

    public static final String TABLE4_KEY_GOLD_IS_HE_COLUM = "IsBoolean";

    public static final String TABLE4_KEY_GOLD_OFFTAKE_NUMBER_COLUM = "Offtake_number";

    public static final String TABLE4_KEY_GOLD_OFFTAKE_PRICE_COLUM = "Offtake_proice";

    public static final String TABLE4_IS_SELL = "SELL";

    public static final String TABLE4_KET_GOLD_IS_NO_MILK = "IS_NO_MILK";

    public static final String TABLE4_KEY_GOLD_NO_MILK_DATE_COLUM = "No_milk_date";


    /**
     * table5
     */

    public static final String TABLE5_NAME = "Feed_table";

    public static final String TABLE5_KEY = "_id";

    public static final String TABLE5_DATE = "DATE";

    public static final String TABLE5_KG = "KG";

    public static final String TABLE5_REMARK = "REMARK";


    /**
     * table6
     */
    public static final String TABLE6_NAME = "ACCOUNTS_table";

    public static final String TABLE6_KEY = "_id";

    public static final String TABLE6_DATE = "DATE";

    public static final String TABLE6_MONEY = "Money";

    public static final String TABLE6_DETALE = "DETAIL";



}
