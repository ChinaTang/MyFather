package tang.di.date;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import tang.di.database.DataBaseTools;

/**
 * Created by tangdi on 2015/11/24.
 */
public class ZhangMuDate implements Parcelable{

    private float money;

    private String date;

    private String detail;

    private int _id;

    public ZhangMuDate(Parcel parcel){
        money = parcel.readFloat();
        date = parcel.readString();
        detail = parcel.readString();
        _id = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeFloat(money);
        dest.writeString(date);
        dest.writeString(detail);
        dest.writeInt(_id);
    }

    public ZhangMuDate(){}

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<ZhangMuDate> CREATOR = new Creator<ZhangMuDate>() {

        @Override
        public ZhangMuDate[] newArray(int size) {
            return new ZhangMuDate[size];
        }

        @Override
        public ZhangMuDate createFromParcel(Parcel source) {
            return new ZhangMuDate(source);
        }

    };



    public float getMoney() {
        return money;
    }

    public Date getDate() {
        return DataBaseTools.StringToDate(date);
    }

    public String getDetail() {
        return detail;
    }

    public int get_id() {
        return _id;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setDate(Date date) {
        this.date = DataBaseTools.DateToString(date);
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


}
