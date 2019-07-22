package com.example.imotor.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    public static final String PREF_NAME = "IMotor";

    public static void setPhoneNumber(String phoneNumber, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PhoneUser", phoneNumber);
        editor.apply();
    }

    public static String getPhoneNumber(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("PhoneUser", null);
    }


}
