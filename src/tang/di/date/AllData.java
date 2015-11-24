package tang.di.date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tang.di.database.DataBaseTools;
import tang.di.database.MydatabaseHelp;
import tang.di.database.PublicWorld;

/**
 * Created by tangdi on 2015/10/15.
 */
public class AllData {



    private ArrayList<MotherPigDate> mMotherList = new ArrayList<MotherPigDate>();

    private ArrayList<LittlePigDate> mLittlePigList = new ArrayList<LittlePigDate>();


    private static AllData instance;

    private AllData(){}

    public static AllData getInstance() {
        if(instance == null){
            instance = new AllData();
            return instance;
        }
        return instance;
    }

    public ArrayList<MotherPigDate> getmMotherList() {
        return mMotherList;
    }

    public ArrayList<LittlePigDate> getmLittlePigList() {
        return mLittlePigList;
    }

    public void setmMotherList(ArrayList<MotherPigDate> mMotherList) {
        this.mMotherList = mMotherList;
    }

    public void setmLittlePigList(ArrayList<LittlePigDate> mLittlePigList) {
        this.mLittlePigList = mLittlePigList;
    }

}

