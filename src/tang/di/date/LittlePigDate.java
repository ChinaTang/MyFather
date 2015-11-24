package tang.di.date;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import tang.di.database.DataBaseTools;

/**
 * Created by tangdi on 2015/10/15.
 */
public class LittlePigDate implements Parcelable{


    /**
     * 是否两个相加的标志位
     */
    private int ISADD = 0;

    private int IsSell = 0;

    /**
     * 数据库主键，将和UUID共同确定一个
     */
    private int _id;

    private String QUUID;

    private String mLittlePigBrithDay;

    private int mPigs;

    private String mLittlePigMilk;

    private String mLittleCutDay;

    private String mSellLittlePigDay;

    private int mSellLittlePig;

    private float mSellLittlePigPrice;

    private int mIsNoMilk;


    private String mAdress;


    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(ISADD);
        dest.writeInt(IsSell);
        dest.writeInt(_id);
        dest.writeString(QUUID);
        dest.writeString(mLittlePigBrithDay);
        dest.writeInt(mPigs);
        dest.writeString(mLittlePigMilk);
        dest.writeString(mLittleCutDay);
        dest.writeString(mSellLittlePigDay);
        dest.writeInt(mSellLittlePig);
        dest.writeFloat(mSellLittlePigPrice);
        dest.writeInt(mIsNoMilk);
        dest.writeString(mAdress);
    }

    public LittlePigDate(Parcel parcel){
        ISADD = parcel.readInt();
        IsSell = parcel.readInt();
        _id = parcel.readInt();
        QUUID = parcel.readString();
        mLittlePigBrithDay = parcel.readString();
        mPigs = parcel.readInt();
        mLittlePigMilk = parcel.readString();
        mLittleCutDay = parcel.readString();
        mSellLittlePigDay = parcel.readString();
        mSellLittlePig = parcel.readInt();
        mSellLittlePigPrice = parcel.readFloat();
        mIsNoMilk = parcel.readInt();
        mAdress = parcel.readString();
    }

    public LittlePigDate(){
        mLittlePigBrithDay = DataBaseTools.DateToString(new Date(System.currentTimeMillis()));
        mPigs = 0;
        mLittlePigMilk = DataBaseTools.DateToString(new Date(System.currentTimeMillis()));
        mLittleCutDay = DataBaseTools.DateToString(new Date(System.currentTimeMillis()));
        mSellLittlePigDay = DataBaseTools.DateToString(new Date(System.currentTimeMillis()));
        mSellLittlePig = 0;
        mSellLittlePigPrice = 0;
        mIsNoMilk = 0;
        mAdress = "";
    }

    public Date getmLittlePigBrithDay() {
        return DataBaseTools.StringToDate(mLittlePigBrithDay);
    }

    public int getmPigs() {
        return mPigs;
    }

    public Date getmLittlePigMilk() {
        return DataBaseTools.StringToDate(mLittlePigMilk);
    }

    public Date getmLittleCutDay() {
        return DataBaseTools.StringToDate(mLittleCutDay);
    }

    public Date getmSellLittlePigDay() {
        return DataBaseTools.StringToDate(mSellLittlePigDay);
    }

    public int getmSellLittlePig() {
        return mSellLittlePig;
    }

    public float getmSellLittlePigPrice() {
        return mSellLittlePigPrice;
    }



    public void setISADD(Boolean ISADD) {
        if(ISADD){
            this.ISADD = 1;
        }else{
            this.ISADD = 0;
        }

    }

    public void setIsSell(Boolean isSell) {
        if(isSell) {
            this.IsSell = 1;
        }else{
            this.IsSell = 0;
        }
    }


    public void setmLittlePigBrithDay(Date mLittlePigBrithDay) {
        this.mLittlePigBrithDay = DataBaseTools.DateToString(mLittlePigBrithDay);
    }

    public void setmPigs(int mPigs) {
        this.mPigs = mPigs;
    }

    public void setmLittlePigMilk(Date mLittlePigMilk) {
        this.mLittlePigMilk = DataBaseTools.DateToString(mLittlePigMilk);
    }

    public void setmLittleCutDay(Date mLittleCutDay) {
        this.mLittleCutDay = DataBaseTools.DateToString(mLittleCutDay);
    }

    public void setmSellLittlePigDay(Date mSellLittlePigDay) {
        this.mSellLittlePigDay = DataBaseTools.DateToString(mSellLittlePigDay);
    }

    public void setmSellLittlePig(int mSellLittlePig) {
        this.mSellLittlePig = mSellLittlePig;
    }

    public void setmSellLittlePigPrice(float mSellLittlePigPrice) {
        this.mSellLittlePigPrice = mSellLittlePigPrice;
    }

    public String getQUUID() {
        return QUUID;
    }

    public void setQUUID(String QUUID) {
        this.QUUID = QUUID;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public boolean ismIsNoMilk() {
        if(mIsNoMilk == 0){
            return false;
        }
        return true;
    }

    public void setmIsNoMilk(boolean mIsNoMilk) {
        if(mIsNoMilk){
            this.mIsNoMilk = 1;
        }else{
            this.mIsNoMilk = 0;
        }
    }

    public String getmAdress() {
        return mAdress;
    }

    public void setmAdress(String mAdress) {
        this.mAdress = mAdress;
    }

    public Boolean getIsSell() {
        if(IsSell == 0){
            return false;
        }
        return true;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("BRIday")
                //.append(mLittlePigBrithDay.toString())
                //.append("number")
                //.append(mPigs)
                //.append("mLittlePigMilk")
                //.append(mLittlePigMilk.toString())
                .append("mIsNoMilk====>")
                .append(mIsNoMilk)
                //.append("mAdress")
                //.append(mAdress)
                .append("UUID")
                .append(QUUID);
        return sb.toString();

    }

    public Boolean getISADD() {
       if(ISADD == 0){
          return false;
       }
        return true;
    }

    public static final Parcelable.Creator<LittlePigDate> CREATOR = new Creator<LittlePigDate>() {

        @Override
        public LittlePigDate[] newArray(int size) {
            return new LittlePigDate[size];
        }

        @Override
        public LittlePigDate createFromParcel(Parcel source) {
            return new LittlePigDate(source);
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }


}
