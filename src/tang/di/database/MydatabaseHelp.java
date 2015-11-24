package tang.di.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tangdi on 2015/11/13.
 */
public class MydatabaseHelp extends SQLiteOpenHelper{

    /**
     * 建立三张表,三张表互相关联，可以通过table1查找到table2中的数据，通过table2查找到table3的数据
     */


    /**
     * 更改数据库存储，将Boolean值全部转化为Int型。
     * 2015-11-23
     */


    /**
     * table1
     */
    private static final String CREATE_TABLE_1 = "create table " + PublicWorld.TABLE_NAME_1
            + " (" + PublicWorld.TABLE1_KEY + " integer primary key autoincrement, "
            + PublicWorld.TABLE1_OTHER_KEY + " text not null);";

    /**
     *table2
     */
    private static final String CREATE_TABLE_2 = "create table " + PublicWorld.TABLE_NAME_2
            + " (" + PublicWorld.TABLE2_KEY + " integer primary key autoincrement, "
            + PublicWorld.TABLE2_KEY_GOLD_BRITH_DAY_COLUM + " String, " +
            PublicWorld.TABLE2_KEY_GOLD_DATA_COLUM + " String, " +
            PublicWorld.TABLE2_KEY_GOLD_HEAT_COLUM + " String, " +
            PublicWorld.TABLE2_KEY_GOLD_FECUNDATION_COLUM + " String, " +
            PublicWorld.TABLE2_KEY_GOLD_VISIBALE + " String, " +
            PublicWorld.TABLE2_KEY_GOLD_WHERE_COLUM + " String);";

    /**
     *table3
     */
    private static final String CREATE_TABLE_3 = "create table " + PublicWorld.TABLE_NAME_3
            + " (" + PublicWorld.TABLE3_KEY + " integer primary key autoincrement, "
            + PublicWorld.SELECT_KEY + " String, "
            + PublicWorld.TABLE3_KEY_GOLD_NUMBER + " integer, " +
            PublicWorld.TABLE3_KEY_GOLD_LITTLE_BRITH_COLUM + " String, " +
            PublicWorld.TABLE3_KEY_GOLD_CASTRAT_TIME_COLUM + " String, " +
            PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_DATE_COLUM + " String, " +
            PublicWorld.TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM + " String, " +
            /**
             * Boolean值转为integer
             */
            PublicWorld.TABLE3_KEY_GOLD_IS_HE_COLUM + " integer, " +
            PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_NUMBER_COLUM + " integer, " +
            /**
             * Boolean值转为integer
             */
            PublicWorld.TABLE3_KET_GOLD_IS_NO_MILK + " integer, " +
            PublicWorld.TABLE3_ADRESS + " String, " +
            /**
             * Boolean值转为integer
             */
            PublicWorld.TABLE3_IS_SELL + " integer, " +

            PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_PRICE_COLUM + " float);";


    /**
     * table4
     */

    private static final String CREATE_TABLE_4 = "create table " + PublicWorld.TABLE_NAME_4
            + " (" + PublicWorld.TABLE4_KEY + " integer primary key autoincrement, "
            + PublicWorld.TABLE4_KEY_GOLD_NUMBER + " integer, " +
            PublicWorld.TABLE4_KEY_GOLD_CASTRAT_TIME_COLUM + " String, " +
            PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_DATE_COLUM + " String, " +
            /**
             * Boolean值转为integer
             */
            PublicWorld.TABLE4_KEY_GOLD_IS_HE_COLUM + " integer, " +
            PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_NUMBER_COLUM + " integer, " +
            PublicWorld.TABLE4_KET_GOLD_IS_NO_MILK + " String, " +
            PublicWorld.TABLE4_ADRESS + " String, " +
            /**
             * Boolean值转为integer
             */
            PublicWorld.TABLE4_IS_SELL + " integer, " +
            PublicWorld.TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM + " String, " +
            PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_PRICE_COLUM + " float);";


    private static final String CREATE_TABLE_5 = "create table " + PublicWorld.TABLE5_NAME
            + " (" + PublicWorld.TABLE5_KEY + " integer primary key autoincrement, " +
            PublicWorld.TABLE5_DATE + " String, " + PublicWorld.TABLE5_KG + " float, " +
            PublicWorld.TABLE5_REMARK + " String);";


    private static final String CREATE_TABLE_6 = "create table " + PublicWorld.TABLE6_NAME
            + " (" + PublicWorld.TABLE6_KEY + " integer primary key autoincrement, " +
            PublicWorld.TABLE6_DATE + " String, " + PublicWorld.TABLE6_MONEY +
            " float, " + PublicWorld.TABLE6_DETALE + " String);";

    public MydatabaseHelp(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_1);
        db.execSQL(CREATE_TABLE_2);
        db.execSQL(CREATE_TABLE_3);
        db.execSQL(CREATE_TABLE_4);
        db.execSQL(CREATE_TABLE_5);
        db.execSQL(CREATE_TABLE_6);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


}
