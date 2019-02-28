package com.ehsanmashhadi.library.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    private String mName;

    @SerializedName("code")
    private String mCode;

    @SerializedName("phone_code")
    private String mDialCode;

    @SerializedName("flag")
    private String mFlag;

    public Country(String name, String code, String dialCode, String flag) {

        mName = name;
        mCode = code;
        mDialCode = dialCode;
        mFlag = flag;
    }

    public String getName() {

        return mName;
    }

    public String getCode() {

        return mCode;
    }

    public String getDialCode() {

        return mDialCode;
    }

    public String getFlagName() {

        return mFlag;
    }
}
