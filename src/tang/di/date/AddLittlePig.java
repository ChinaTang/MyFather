package tang.di.date;

import android.content.Context;

import java.util.Date;

/**
 * Created by tangdi on 2015/10/15.
 */
public class AddLittlePig {

    /**
     * 需要一个新的界面来绘制这个类
     */

    private int mAddPig;

    private String Adress;


    private Long mAddPigSell;

    private Date mSellDate;

    public static boolean IsSell;

    private Context context;

    public AddLittlePig(LittlePigDate item1, LittlePigDate item2, String Adress){
        this.mAddPig = item1.getmPigs() + item2.getmPigs();

        this.Adress = Adress;

    }

    public Long getmAddPigSell() {
        return mAddPigSell;
    }

    public Date getmSellDate() {
        return mSellDate;
    }

    private AddLittlePig(Context context){
        this.context = context;
    }

    public LittlePigDate AddLittle(LittlePigDate item1, LittlePigDate item2){

        LittlePigDate mAfterAdd = new LittlePigDate(context);

        return mAfterAdd;
    }

}
