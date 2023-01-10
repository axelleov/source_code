package com.ti5b.freeciscoccna.utility;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utilities {
    private static final String PREFERENCE_KEY = "appFreeCiscoCCNA";

    private static final String BASE_URL = "https://accommodative-certi.000webhostapp.com/freecisco/index.php/MobileControl/";

    public static Retrofit mRetrofit;

    public static Retrofit getmRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static void clearUser(Context context) {
        SharedPreferences sp = null;
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            sp = EncryptedSharedPreferences.create(
                    PREFERENCE_KEY,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e ) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("xEmail", null);
        editor.apply();
    }

    public static void setValue(Context context, String xPref, String xValue) {
        SharedPreferences sp = null;
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            sp = EncryptedSharedPreferences.create(
                    PREFERENCE_KEY,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e ) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(xPref, xValue);
        editor.commit();
    }

    public static String getValue(Context context, String xPref){
        SharedPreferences sp = null;
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            sp = EncryptedSharedPreferences.create(
                    PREFERENCE_KEY,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e ) {
            e.printStackTrace();
        }


        String xValue = sp.getString(xPref, null);
        return xValue;
    }

    public static boolean checkValue(Context context, String xPref){
        SharedPreferences sp = null;
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            sp = EncryptedSharedPreferences.create(
                    PREFERENCE_KEY,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e ) {
            e.printStackTrace();
        }


        String xValue = sp.getString(xPref, null);
        if (xValue != null) {
            return true;
        } else {
            return false;
        }
    }

}
