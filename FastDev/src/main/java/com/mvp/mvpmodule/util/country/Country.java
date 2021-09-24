package com.mvp.mvpmodule.util.country;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 国家
 *
 * @author fence
 * @date 2019-09-05
 */
public class Country implements Parcelable {

    private String mCode;//手机号编码
    private String mShortName;//缩写
    private String mFullName;//全名
    private int mFlag;//国家图标
    private int mPhoneLimeter;//手机号限制
    private String mDomain;//国家顶级域名
    public Country(String code, String shortName, String fullName, int flag,String domain) {
        mCode = code;
        mShortName = shortName;
        mFullName = fullName;
        mFlag = flag;
        mDomain=domain;
    }
    public Country(String code, String shortName, String fullName, int flag) {
        mCode = code;
        mShortName = shortName;
        mFullName = fullName;
        mFlag = flag;
    }
    public Country(String code, String shortName, String fullName, int flag, int phoneLimeter) {
        mCode = code;
        mShortName = shortName;
        mFullName = fullName;
        mFlag = flag;
        mPhoneLimeter=phoneLimeter;
    }
    public Country(String code, String shortName, String fullName, int flag, int phoneLimeter,String domain) {
        mCode = code;
        mShortName = shortName;
        mFullName = fullName;
        mFlag = flag;
        mPhoneLimeter=phoneLimeter;
        mDomain=domain;
    }

    protected Country(Parcel in) {
        mFullName = in.readString();
        mShortName = in.readString();
        mCode = in.readString();
        mFlag = in.readInt();
        mPhoneLimeter=in.readInt();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String shortName) {
        mShortName = shortName;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public int getFlag() {
        return mFlag;
    }

    public void setFlag(int flag) {
        mFlag = flag;
    }

    public int getmPhoneLimeter() {
        return mPhoneLimeter;
    }

    public void setmPhoneLimeter(int mPhoneLimeter) {
        this.mPhoneLimeter = mPhoneLimeter;
    }

    public String getmDomain() {
        return mDomain;
    }

    public void setmDomain(String mDomain) {
        this.mDomain = mDomain;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFullName);
        dest.writeString(mShortName);
        dest.writeString(mCode);
        dest.writeInt(mFlag);
        dest.writeInt(mPhoneLimeter);
        dest.writeString(mDomain);
    }
}
