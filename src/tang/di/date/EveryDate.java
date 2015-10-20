package tang.di.date;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by tangdi on 2015/10/15.
 */
public class EveryDate {

    private Context context;

    private static ArrayList<MotherPigDate> mMotherList = new ArrayList<MotherPigDate>();

    private static ArrayList<AddLittlePig> mAddLittlePig = new ArrayList<AddLittlePig>();


    private static EveryDate instance;

    private EveryDate(Context context){
        this.context = context;
    }

    public static EveryDate getInstance(Context context) {
        if(instance == null){
            instance = new EveryDate(context);
            return instance;
        }
        return instance;
    }

    public static void SaveJSONDate(){
        for(MotherPigDate item : mMotherList){



        }
    }

}

