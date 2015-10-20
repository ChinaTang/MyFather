package tang.di.date;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by tangdi on 2015/10/15.
 */
public class LittlePigDate {

    private static final String FROM = "FROM";

    /**
     * 是否两个相加的标志位
     */
    public static Boolean ISADD = false;

    public static Boolean IsSell = false;

    private static final String SELL_FLAG = "SELL_FLAG";

    private static final String ADD_FLAG = "ADD_FLAG";

    private static final String LITTLEPIGBRITHDAY = "LITTLEPIGBRITHDAY";

    private static final String PIG = "PIG";

    private static final String LITTLEPIGMILK = "LITTLEPIGMILK";

    private static final String LITTLECUTDAY = "LITTLECUTDAY";

    private static final String SELLLITTLEPIGDAY = "SELLLITTLEPIGDAY";

    private static final String SELLLITTLEPIG = "SELLLITTLEPIG";

    private static final String SELLLITTLEPIGPRICE = "SELLLITTLEPIGPRICE";

    private Context context;

    private String mFrom;

    private Date mLittlePigBrithDay;

    private int mPigs;

    private Date mLittlePigMilk;

    private Date mLittleCutDay;

    private Date mSellLittlePigDay;

    private int mSellLittlePig;

    private Long mSellLittlePigPrice;




    public LittlePigDate(String From, Date mLittlePigBrithDay, Date mLittlePigMilk,
                         Date mLittleCutDay, Date mSellLittlePigDay, int mPigs, int msellLittlePig,
                         Long mSellLittlePigPrice){
        this.mFrom = From;
        this.mLittlePigBrithDay = mLittlePigBrithDay;
        this.mPigs = mPigs;
        this.mLittlePigMilk = mLittlePigMilk;
        this.mLittleCutDay = mLittleCutDay;
        this.mSellLittlePigDay = mSellLittlePigDay;
        this.mSellLittlePig = msellLittlePig;
        this.mSellLittlePigPrice = mSellLittlePigPrice;

    }

    public LittlePigDate(JSONObject json) throws JSONException {

        mFrom = json.getString(FROM);
        mLittlePigBrithDay = new Date(json.getLong(LITTLEPIGBRITHDAY));
        mPigs = json.getInt(PIG);
        mLittlePigMilk = new Date(json.getLong(LITTLEPIGMILK));
        mLittleCutDay = new Date(json.getLong(LITTLECUTDAY));
        mSellLittlePigDay = new Date(json.getLong(SELLLITTLEPIGDAY));
        mSellLittlePig = json.getInt(SELLLITTLEPIGDAY);
        mSellLittlePigPrice = json.getLong(SELLLITTLEPIGPRICE);
        ISADD = json.getBoolean(ADD_FLAG);
        IsSell = json.getBoolean(SELL_FLAG);
    }

    public JSONObject toJSON() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(FROM, mFrom);
        json.put(LITTLEPIGBRITHDAY, mLittlePigBrithDay.getTime());
        json.put(PIG, mPigs);
        json.put(LITTLEPIGMILK, mLittlePigMilk.getTime());
        json.put(LITTLECUTDAY, mLittleCutDay.getTime());
        json.put(SELLLITTLEPIGDAY, mSellLittlePigDay.getTime());
        json.put(SELLLITTLEPIG, mSellLittlePig);
        json.put(SELLLITTLEPIGPRICE, mSellLittlePigPrice);
        json.put(ADD_FLAG, ISADD);
        json.put(SELL_FLAG, IsSell);

        return json;
    }

    public LittlePigDate(Context context){
        this.context = context;
    }

    public String getmFrom() {
        return mFrom;
    }

    public Date getmLittlePigBrithDay() {
        return mLittlePigBrithDay;
    }

    public int getmPigs() {
        return mPigs;
    }

    public Date getmLittlePigMilk() {
        return mLittlePigMilk;
    }

    public Date getmLittleCutDay() {
        return mLittleCutDay;
    }

    public Date getmSellLittlePigDay() {
        return mSellLittlePigDay;
    }

    public int getmSellLittlePig() {
        return mSellLittlePig;
    }

    public Long getmSellLittlePigPrice() {
        return mSellLittlePigPrice;
    }

    public static String getSellFlag() {
        return SELL_FLAG;
    }

    public static String getFROM() {
        return FROM;
    }
}
