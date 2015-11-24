package tang.di.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tang.di.date.FeedData;
import tang.di.date.LittlePigDate;
import tang.di.date.MotherPigDate;
import tang.di.date.ZhangMuDate;

/**
 * Created by tangdi on 2015/11/16.
 */
public class DataBaseTools {

    public static final String TAG = "DataBaseTools";

    private Context mApplicationContext;

    private MydatabaseHelp mydatabaseHelp;

    private SQLiteDatabase db;

    private static DataBaseTools dataBaseTools;

    private DataBaseTools(Context context){
        mApplicationContext = context.getApplicationContext();
        mydatabaseHelp = new MydatabaseHelp(mApplicationContext, PublicWorld.DATABASE_NAME, null, 1);
        try{
            db = mydatabaseHelp.getWritableDatabase();
        }catch (SQLiteException ex){
            db = mydatabaseHelp.getReadableDatabase();
        }
    }

    public static DataBaseTools getInstance(Context context){
        if(dataBaseTools == null){
            return dataBaseTools = new DataBaseTools(context);
        }
        return dataBaseTools;
    }

    /**
     * 获取所有bp数据并返回ArrayList
     */
    public ArrayList<MotherPigDate> getMotherPigDataFromDB(String str){
        ArrayList<MotherPigDate> temp = new ArrayList<MotherPigDate>();
        Cursor cursor;
        String[] selectionArgs;
        /**
         * 查询Table2的数据
         */

        String where;

        if(str == null){
            where = null;
            selectionArgs = null;
        }else{
            where = PublicWorld.TABLE2_KEY_GOLD_DATA_COLUM + " = ?";
            selectionArgs = new String[]{str};
        }

        String whereArgs[] = new String[]{
                PublicWorld.TABLE2_KEY_GOLD_WHERE_COLUM, PublicWorld.TABLE2_KEY_GOLD_DATA_COLUM,
                PublicWorld.TABLE2_KEY_GOLD_HEAT_COLUM, PublicWorld.TABLE2_KEY_GOLD_FECUNDATION_COLUM,
                PublicWorld.TABLE2_KEY_GOLD_BRITH_DAY_COLUM, PublicWorld.TABLE2_KEY_GOLD_VISIBALE,
        };

        cursor = db.query(PublicWorld.TABLE_NAME_2, null, where, selectionArgs, null, null, null);
        while(cursor.moveToNext()){
            MotherPigDate item = new MotherPigDate();
            item.setAdress(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE2_KEY_GOLD_WHERE_COLUM)));
            item.setmFaQingdate(StringToDate(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE2_KEY_GOLD_HEAT_COLUM))));
            item.setmShoujingDate(StringToDate(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE2_KEY_GOLD_FECUNDATION_COLUM))));
            item.setmChanzaiDate(StringToDate(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE2_KEY_GOLD_BRITH_DAY_COLUM))));
            /**
             * 标志查询下一个表
             */
            item.setQunID(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE2_KEY_GOLD_DATA_COLUM)
            ));
            temp.add(item);
        }
        return temp;
    }

    /**
     * 查询下一lp表格,根据mp查询他下面的lp当参数为null时查询整张表
     */
    public ArrayList<LittlePigDate> getLittlePigDataFormDB(MotherPigDate motherPigDate){

        String where;
        String[] selectionArgs;
        ArrayList<LittlePigDate> temp = new ArrayList<LittlePigDate>();
        if(motherPigDate != null) {
            /**
             * 是否查询整张表
             */
            where = PublicWorld.SELECT_KEY + " = ?";
            selectionArgs = new String[]{ motherPigDate.getQunID()};

        }else{
            where = null;
            selectionArgs = null;
        }

            /**
             * table3查询
             */
            String[] whereArgs = new String[]{
                    PublicWorld.SELECT_KEY, PublicWorld.TABLE2_KEY,
                    PublicWorld.TABLE3_KEY_GOLD_NUMBER, PublicWorld.TABLE3_KEY_GOLD_LITTLE_BRITH_COLUM,
                    PublicWorld.TABLE3_KEY_GOLD_CASTRAT_TIME_COLUM, PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_DATE_COLUM,
                    PublicWorld.TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM, PublicWorld.TABLE3_KEY_GOLD_IS_HE_COLUM,
                    PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_NUMBER_COLUM, PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_PRICE_COLUM
            };

            Cursor cursor = db.query(PublicWorld.TABLE_NAME_3, null, where, selectionArgs, null, null, null);
            while (cursor.moveToNext()) {
                LittlePigDate item = new LittlePigDate();
                item.setmPigs(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_NUMBER)));
                Log.d(TAG, "getmPigs===>db" + cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_NUMBER)));
                item.setmLittlePigBrithDay(StringToDate(cursor.getString(
                        cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_LITTLE_BRITH_COLUM))));
                item.setmLittleCutDay(StringToDate(cursor.getString(
                                cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_CASTRAT_TIME_COLUM))
                ));
                item.setmSellLittlePigDay(StringToDate(cursor.getString(
                        cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_DATE_COLUM))));
                item.setmLittlePigMilk(StringToDate(cursor.getString(
                        cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM)
                )));
                item.setmSellLittlePig(cursor.getInt(
                        cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_NUMBER_COLUM)));
                item.setmSellLittlePigPrice(
                        cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_PRICE_COLUM));
                item.setISADD(changeBoolean(
                        cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_IS_HE_COLUM))));
                item.setQUUID(cursor.getString(cursor.getColumnIndex(PublicWorld.SELECT_KEY)));
                item.set_id(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE2_KEY)));
                item.setmIsNoMilk(changeBoolean(
                        cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KET_GOLD_IS_NO_MILK))));
                Log.d(TAG, "get()" + "TABLE3_KET_GOLD_IS_NO_MILK" + "===>" + String.valueOf(
                        changeBoolean(
                                cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KET_GOLD_IS_NO_MILK)))));

                item.setmAdress(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE3_ADRESS)));
                item.setIsSell(changeBoolean(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_IS_SELL))));
                temp.add(item);
            }
        cursor.close();
            return temp;

    }
    /***
     * 查询某一个数据
     */

    public LittlePigDate getLittlePigDataFormDB(String UUID, int id){

        String where;
        String[] selectionArgs;
        LittlePigDate item;

        //String SQL = "select * from " + PublicWorld.TABLE_NAME_3 + " where "
                //+ PublicWorld.TABLE2_KEY + " = ? and " + PublicWorld.SELECT_KEY + " =?";

        where = PublicWorld.TABLE2_KEY + " = ? " + " and " + PublicWorld.SELECT_KEY + " = ?";


        /**
         * table3查询
         */
        String[] whereArgs = new String[]{
                PublicWorld.SELECT_KEY, PublicWorld.TABLE2_KEY,
                PublicWorld.TABLE3_KEY_GOLD_NUMBER, PublicWorld.TABLE3_KEY_GOLD_LITTLE_BRITH_COLUM,
                PublicWorld.TABLE3_KEY_GOLD_CASTRAT_TIME_COLUM, PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_DATE_COLUM,
                PublicWorld.TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM, PublicWorld.TABLE3_KEY_GOLD_IS_HE_COLUM,
                PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_NUMBER_COLUM, PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_PRICE_COLUM
        };

        selectionArgs = new String[]{String.valueOf(id), UUID};


        //Cursor cursor = db.rawQuery(SQL, new String[]{String.valueOf(id), "%" + UUID + "%"});
        Cursor cursor = db.query(PublicWorld.TABLE_NAME_3, null, where, selectionArgs, null, null, null);
        item = new LittlePigDate();
        while(cursor.moveToNext()) {


            item.setmPigs(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_NUMBER)));
            Log.d(TAG, "getmPigs===>getLittlePigDataFormDB(String UUID, int id)" + cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_NUMBER)));
            item.setmLittlePigBrithDay(StringToDate(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_LITTLE_BRITH_COLUM))));
            item.setmLittleCutDay(StringToDate(cursor.getString(
                            cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_CASTRAT_TIME_COLUM))
            ));
            item.setmSellLittlePigDay(StringToDate(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_DATE_COLUM))));
            item.setmLittlePigMilk(StringToDate(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM)
            )));
            item.setmSellLittlePig(cursor.getInt(
                    cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_NUMBER_COLUM)));
            item.setmSellLittlePigPrice(
                    cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_PRICE_COLUM));
            item.setISADD(changeBoolean(
                    cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KEY_GOLD_IS_HE_COLUM))));
            item.setQUUID(cursor.getString(cursor.getColumnIndex(PublicWorld.SELECT_KEY)));
            item.set_id(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE2_KEY)));
            item.setmIsNoMilk(changeBoolean(
                    cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KET_GOLD_IS_NO_MILK))));

            Log.d(TAG, "getLittlePigDataFormDB===>" + String.valueOf(changeBoolean(
                    cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_KET_GOLD_IS_NO_MILK)))));

                    item.setmAdress(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE3_ADRESS)));
            item.setIsSell(changeBoolean(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE3_IS_SELL))));
        }
        cursor.close();
            return item;

    }

    /**
     * 插入数据
     */

    public void insertDateToSQL(Object object){

        if(object instanceof MotherPigDate){
            object = (MotherPigDate)object;
            ContentValues values = new ContentValues();
            values.put(PublicWorld.TABLE2_KEY_GOLD_WHERE_COLUM, ((MotherPigDate) object).getAdress());
            values.put(PublicWorld.TABLE2_KEY_GOLD_DATA_COLUM, ((MotherPigDate) object).getQunID());
            values.put(PublicWorld.TABLE2_KEY_GOLD_HEAT_COLUM,
                    DateToString(((MotherPigDate) object).getmFaQingdate()));
            values.put(PublicWorld.TABLE2_KEY_GOLD_FECUNDATION_COLUM,
                    DateToString(((MotherPigDate) object).getmShoujingDate()));
            values.put(PublicWorld.TABLE2_KEY_GOLD_BRITH_DAY_COLUM,
                    DateToString(((MotherPigDate) object).getmChanzaiDate()));
            db.insert(PublicWorld.TABLE_NAME_2, null, values);
            Log.d(TAG, "数据保存成功===========>BigPig");
            return;
        }
        if(object instanceof LittlePigDate){
            object = (LittlePigDate)object;
            ContentValues values = new ContentValues();
            values.put(PublicWorld.SELECT_KEY, ((LittlePigDate) object).getQUUID());
            values.put(PublicWorld.TABLE3_KEY_GOLD_NUMBER, ((LittlePigDate) object).getmPigs());
            values.put(PublicWorld.TABLE3_KEY_GOLD_LITTLE_BRITH_COLUM,
                    DateToString(((LittlePigDate) object).getmLittlePigBrithDay()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_CASTRAT_TIME_COLUM,
                    DateToString(((LittlePigDate) object).getmLittleCutDay()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_DATE_COLUM,
                    DateToString(((LittlePigDate) object).getmSellLittlePigDay()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM,
                    DateToString(((LittlePigDate) object).getmLittlePigMilk()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_IS_HE_COLUM, ((LittlePigDate) object).getISADD());
            Log.d(TAG, "insertDateToSQL===>" + String.valueOf(((LittlePigDate) object).getISADD()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_NUMBER_COLUM,
                    ((LittlePigDate) object).getmSellLittlePig());
            values.put(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_PRICE_COLUM,
                    ((LittlePigDate) object).getmSellLittlePigPrice());
            values.put(PublicWorld.TABLE3_KET_GOLD_IS_NO_MILK,
                    ((LittlePigDate) object).ismIsNoMilk());
            Log.d(TAG, "TABLE3_KET_GOLD_IS_NO_MILK====>" + String.valueOf(((LittlePigDate) object).ismIsNoMilk()));
            values.put(PublicWorld.TABLE3_ADRESS,
                    ((LittlePigDate) object).getmAdress());
            values.put(PublicWorld.TABLE3_IS_SELL, ((LittlePigDate) object).getIsSell());
            db.insert(PublicWorld.TABLE_NAME_3, null, values);

            Log.d(TAG, "数据保存成功===========>littlePig");

            return;
        }
        Log.d(TAG, "输入错误的信息");

    }

    /**
     * 无法确认正确性，整个App的时间存入数据库都会通过这个方法来装换
     * @param date
     * @return
     */
    public static String DateToString(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static Date StringToDate(String str){
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(str);
        }catch(Exception e){
            Log.e("TAG", "时间转换失败，将返回当前系统时间");
            return new Date(System.currentTimeMillis());
        }
    }

    /**
     * 更新数据
     */
    public void upContentValue(Object object){
        if(object instanceof MotherPigDate){
            object = (MotherPigDate)object;
            String where = PublicWorld.TABLE2_KEY_GOLD_DATA_COLUM + " = ?";
            String[] whereArgs = new String[]{((MotherPigDate) object).getQunID()};
            ContentValues values = new ContentValues();
            values.put(PublicWorld.TABLE2_KEY_GOLD_WHERE_COLUM, ((MotherPigDate) object).getAdress());
            values.put(PublicWorld.TABLE2_KEY_GOLD_HEAT_COLUM,
                    DateToString(((MotherPigDate) object).getmFaQingdate()));
            values.put(PublicWorld.TABLE2_KEY_GOLD_FECUNDATION_COLUM,
                    DateToString(((MotherPigDate) object).getmShoujingDate()));
            values.put(PublicWorld.TABLE2_KEY_GOLD_BRITH_DAY_COLUM,
                    DateToString(((MotherPigDate) object).getmChanzaiDate()));
            db.update(PublicWorld.TABLE_NAME_2, values, where, whereArgs);
            return;
        }
        if(object instanceof  LittlePigDate){
            object = (LittlePigDate)object;
            String where = PublicWorld.TABLE3_KEY + " = ? " + " and " + PublicWorld.SELECT_KEY + " = ?";
            String[] whereArgs =  new String[]{String.valueOf(((LittlePigDate) object).get_id()),
                     ((LittlePigDate) object).getQUUID()};
            Log.d(TAG, "===saveButton===" + ((LittlePigDate) object).getQUUID() + String.valueOf(((LittlePigDate) object).get_id()));
            ContentValues values = new ContentValues();
            values.put(PublicWorld.TABLE3_KEY_GOLD_NUMBER, ((LittlePigDate) object).getmPigs());
            values.put(PublicWorld.TABLE3_KEY_GOLD_LITTLE_BRITH_COLUM,
                    DateToString(((LittlePigDate) object).getmLittlePigBrithDay()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_CASTRAT_TIME_COLUM,
                    DateToString(((LittlePigDate) object).getmLittleCutDay()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_DATE_COLUM,
                    DateToString(((LittlePigDate) object).getmSellLittlePigDay()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_NO_MILK_DATE_COLUM,
                    DateToString(((LittlePigDate) object).getmLittlePigMilk()));
            values.put(PublicWorld.TABLE3_KEY_GOLD_IS_HE_COLUM, ((LittlePigDate) object).getIsSell());
            values.put(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_NUMBER_COLUM,
                    ((LittlePigDate) object).getmSellLittlePig());
            values.put(PublicWorld.TABLE3_KEY_GOLD_OFFTAKE_PRICE_COLUM,
                    ((LittlePigDate) object).getmSellLittlePigPrice());
            values.put(PublicWorld.TABLE3_KET_GOLD_IS_NO_MILK,
                    ((LittlePigDate) object).ismIsNoMilk());
            values.put(PublicWorld.TABLE3_ADRESS, ((LittlePigDate) object).getmAdress());
            values.put(PublicWorld.TABLE3_IS_SELL, ((LittlePigDate)object).getIsSell());
            int flag = db.update(PublicWorld.TABLE_NAME_3, values, where, whereArgs);
            Log.d(TAG, "upContentValue==>更新成功" + "flag============>" + String.valueOf(flag));
            return;
        }
    }


    /**
     * 表4的方法，需要两条数据相加的方法，临时表，将会有删除方法
     */

    public ArrayList<LittlePigDate> selsetT4(){
        ArrayList<LittlePigDate> temp = new ArrayList<LittlePigDate>();
        Cursor cursor = db.query(PublicWorld.TABLE_NAME_4, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            LittlePigDate item = new LittlePigDate();
            item.setmPigs(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE4_KEY_GOLD_NUMBER)));
            item.setmLittleCutDay(StringToDate(cursor.getString(
                            cursor.getColumnIndex(PublicWorld.TABLE4_KEY_GOLD_CASTRAT_TIME_COLUM))
            ));
            item.setmSellLittlePigDay(StringToDate(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_DATE_COLUM))));
            item.setmSellLittlePig(cursor.getInt(
                    cursor.getColumnIndex(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_NUMBER_COLUM)));
            item.setmSellLittlePigPrice(
                    cursor.getColumnIndex(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_PRICE_COLUM));
            item.setISADD(Boolean.valueOf(
                    cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE4_KEY_GOLD_IS_HE_COLUM))));
            item.set_id(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE2_KEY)));
            item.setmIsNoMilk(changeBoolean(
                    cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE4_KET_GOLD_IS_NO_MILK))));
            item.setmAdress(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE4_ADRESS)));
            item.setIsSell(changeBoolean(
                    cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE4_IS_SELL))));
            item.setmLittlePigMilk(StringToDate(cursor.getString(
                    cursor.getColumnIndex(PublicWorld.TABLE4_KEY_GOLD_NO_MILK_DATE_COLUM))));
            temp.add(item);
        }
        cursor.close();
        return temp;
    }

    public void deletT4(LittlePigDate littlePigDate){

        String where = PublicWorld.TABLE4_KEY + " = ?";
        String[] whereAges = new String[]{String.valueOf(littlePigDate.get_id())};
        db.delete(PublicWorld.TABLE_NAME_4, where, whereAges);
        Log.d("tangdi", "======删除数据=======");
    }

    public void addTwo(LittlePigDate add1, LittlePigDate add2){
        LittlePigDate item = new LittlePigDate();
        item.setmAdress(add1.getmAdress() + " 和 " + add2.getmAdress() + " 的合并");
        item.setmIsNoMilk(true);
        item.setmLittlePigMilk(add1.getmLittlePigMilk());
        item.setmPigs(add1.getmPigs() + add2.getmPigs());
        deletT4(add1);
        deletT4(add2);
        ContentValues values = new ContentValues();
        values.put(PublicWorld.TABLE4_ADRESS, item.getmAdress());
        values.put(PublicWorld.TABLE4_KET_GOLD_IS_NO_MILK, item.ismIsNoMilk());
        values.put(PublicWorld.TABLE4_KEY_GOLD_NO_MILK_DATE_COLUM,
                DateToString(item.getmLittlePigMilk()));
        values.put(PublicWorld.TABLE4_KEY_GOLD_NUMBER, item.getmPigs());
        db.insert(PublicWorld.TABLE_NAME_4, null, values);
    }

    public void UpdataT4(LittlePigDate littlePigDate){

        String where = PublicWorld.TABLE4_KEY + " = ?";

        String[] whereArg = new String[]{String.valueOf(littlePigDate.get_id())};

        ContentValues values = new ContentValues();
        values.put(PublicWorld.TABLE4_KEY_GOLD_NUMBER, littlePigDate.getmPigs());
        values.put(PublicWorld.TABLE4_KEY_GOLD_CASTRAT_TIME_COLUM,
                DateToString((littlePigDate.getmLittleCutDay())));
        values.put(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_DATE_COLUM,
                DateToString(littlePigDate.getmSellLittlePigDay()));
        values.put(PublicWorld.TABLE4_KEY_GOLD_NO_MILK_DATE_COLUM,
                DateToString(littlePigDate.getmLittlePigMilk()));
        values.put(PublicWorld.TABLE4_KEY_GOLD_IS_HE_COLUM, (littlePigDate.getIsSell()));
        values.put(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_NUMBER_COLUM,
                littlePigDate.getmSellLittlePig());
        values.put(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_PRICE_COLUM,
                littlePigDate.getmSellLittlePigPrice());
        values.put(PublicWorld.TABLE4_KET_GOLD_IS_NO_MILK,
                littlePigDate.ismIsNoMilk());
        values.put(PublicWorld.TABLE4_ADRESS, littlePigDate.getmAdress());
        values.put(PublicWorld.TABLE4_IS_SELL, littlePigDate.getIsSell());
        db.update(PublicWorld.TABLE_NAME_4, values, where, whereArg);
    }

    public void insertT4(LittlePigDate littlePigDate){

        ContentValues values = new ContentValues();
        values.put(PublicWorld.TABLE4_KEY_GOLD_NUMBER, littlePigDate.getmPigs());
        values.put(PublicWorld.TABLE4_KEY_GOLD_CASTRAT_TIME_COLUM,
                DateToString((littlePigDate.getmLittleCutDay())));
        values.put(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_DATE_COLUM,
                DateToString(littlePigDate.getmSellLittlePigDay()));
        values.put(PublicWorld.TABLE4_KEY_GOLD_NO_MILK_DATE_COLUM,
                DateToString(littlePigDate.getmLittlePigMilk()));
        values.put(PublicWorld.TABLE4_KEY_GOLD_IS_HE_COLUM, (littlePigDate.getIsSell()));
        values.put(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_NUMBER_COLUM,
                littlePigDate.getmSellLittlePig());
        values.put(PublicWorld.TABLE4_KEY_GOLD_OFFTAKE_PRICE_COLUM,
                littlePigDate.getmSellLittlePigPrice());
        values.put(PublicWorld.TABLE4_KET_GOLD_IS_NO_MILK,
                littlePigDate.ismIsNoMilk());
        values.put(PublicWorld.TABLE4_ADRESS, littlePigDate.getmAdress());
        values.put(PublicWorld.TABLE4_IS_SELL, littlePigDate.getIsSell());

        db.insert(PublicWorld.TABLE_NAME_4, null, values);

    }

    /**
     * table5
     *
     */

    public void insert(FeedData feedData){

        ContentValues values = new ContentValues();
        values.put(PublicWorld.TABLE5_DATE, DateToString(feedData.getDate()));
        values.put(PublicWorld.TABLE5_KG, feedData.getKg());
        values.put(PublicWorld.TABLE5_REMARK, feedData.getRemark());
        db.insert(PublicWorld.TABLE5_NAME, null, values);

    }

    public void upDate5(FeedData feedData){

        String where = PublicWorld.TABLE5_KEY + " = ?";
        String[] whereArgs = new String[]{String.valueOf(feedData.get_id())};
        ContentValues values = new ContentValues();
        values.put(PublicWorld.TABLE5_DATE, DateToString(feedData.getDate()));
        values.put(PublicWorld.TABLE5_KG, feedData.getKg());
        values.put(PublicWorld.TABLE5_REMARK, feedData.getRemark());
        db.update(PublicWorld.TABLE5_NAME, values, where, whereArgs);
    }

    public FeedData select(FeedData feedData){
        String where = PublicWorld.TABLE5_KEY + " = ?";
        String[] whereArgs = new String[]{String.valueOf(feedData.get_id())};
        FeedData fd = new FeedData();
        Cursor cursor = db.query(PublicWorld.TABLE5_NAME, null, where, whereArgs, null, null, null);
        while(cursor.moveToNext()){
            fd.set_id(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE5_KEY)));
            fd.setDate(StringToDate(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE5_DATE))));
            fd.setKg(cursor.getFloat(cursor.getColumnIndex(PublicWorld.TABLE5_KG)));
            fd.setRemark(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE5_REMARK)));
        }
        return fd;
    }

    public ArrayList<FeedData> select(){
        ArrayList<FeedData> array = new ArrayList<FeedData>();
        Cursor cursor = db.query(PublicWorld.TABLE5_NAME, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            FeedData fd = new FeedData();
            fd.set_id(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE5_KEY)));
            fd.setDate(StringToDate(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE5_DATE))));
            fd.setKg(cursor.getFloat(cursor.getColumnIndex(PublicWorld.TABLE5_KG)));
            fd.setRemark(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE5_REMARK)));
            array.add(fd);
        }
        return array;
    }

    /**
     * table6
     * @param zhangMuDate
     */

    public void insert(ZhangMuDate zhangMuDate){
        ContentValues values = new ContentValues();
        values.put(PublicWorld.TABLE6_DATE, DateToString(zhangMuDate.getDate()));
        values.put(PublicWorld.TABLE6_MONEY, zhangMuDate.getMoney());
        values.put(PublicWorld.TABLE6_DETALE, zhangMuDate.getDetail());
        db.insert(PublicWorld.TABLE6_NAME, null, values);
    }

    public ArrayList<ZhangMuDate> select6(){
        Cursor cursor = db.query(PublicWorld.TABLE6_NAME, null, null, null, null, null, null);
        ArrayList<ZhangMuDate> arrayList = new ArrayList<ZhangMuDate>();
        while(cursor.moveToNext()){
            ZhangMuDate zhangMuDate = new ZhangMuDate();
            zhangMuDate.setDate(StringToDate(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE6_DATE))));
            zhangMuDate.set_id(cursor.getInt(cursor.getColumnIndex(PublicWorld.TABLE6_KEY)));
            zhangMuDate.setDetail(cursor.getString(cursor.getColumnIndex(PublicWorld.TABLE6_DETALE)));
            zhangMuDate.setMoney(cursor.getFloat(cursor.getColumnIndex(PublicWorld.TABLE6_MONEY)));
            arrayList.add(zhangMuDate);
        }
        return arrayList;
    }

    public static boolean changeBoolean(int i){
        if(i == 0){
            return false;
        }
        return true;
    }



}
