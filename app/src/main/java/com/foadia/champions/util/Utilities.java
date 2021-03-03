package com.foadia.champions.util;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

    static final String PREF_NAME = "myPref";
    static final String USER_NAME = "userName";
    static final String USER_Password = "Password";

    static final String USER_CountryID= "CountryID";
    static final String USER_CountryName= "CountryName";

    static final String Talent_id = "talentId";
    static final String USER_token= "token";
    static final String USER_ID = "userID";
    static final String USER_Permission = "PermissionID";
    static final String USER_Lang = "USERLang";

    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    static ProgressDialog progressDialog;

    public static void showLoadingDialog(Context context) {
        try {
            progressDialog = new ProgressDialog(context);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissLoadingDialog() {
        try {
            progressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> Object convertStringToObject(String jsonString, Class<T> castClassTo, boolean isList) {
        Gson objectMapper = new Gson();
        try {
            if (isList) {
                Object[] array = (Object[]) java.lang.reflect.Array.newInstance(castClassTo, 1);
                array = objectMapper.fromJson(jsonString, array.getClass());
                List<T> list = new ArrayList<T>();
                for (int i = 0; i < array.length; i++)
                    list.add((T) array[i]);
                return list;
            } else {
                return objectMapper.fromJson(jsonString, castClassTo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showYesNoDialog(Context context, String title,
                                       String message, String yesText, String noText,
                                       DialogInterface.OnClickListener dialogClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(yesText, dialogClickListener)
                .setNegativeButton(noText, dialogClickListener).show();

    }

    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager connectivityManager = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static void checkPreference(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREF_NAME, 0);
            editor = preferences.edit();
        }
    }

    private static void putString(Context context, String key, String value) {
        checkPreference(context);
        editor.putString(key, value);
        editor.commit();
    }

    private static void putInt(Context context, String key, int value) {
        checkPreference(context);
        editor.putInt(key, value);
        editor.commit();
    }

    private static String getString(Context context, String key) {
        checkPreference(context);
        return preferences.getString(key, null);
    }

    private static int getInt(Context context, String key) {
        checkPreference(context);
        return preferences.getInt(key,-1);
    }

    public static void putName(Context context, String name) {
        putString(context, USER_NAME, name);
    }


    public static void putPassword(Context context, String Password) {
        putString(context, USER_Password, Password);
    }

    public static String getPassword(Context context) {
        return getString(context,USER_Password);
    }

    public static void putCountryID(Context context, int countryId) {
        putInt(context, USER_CountryID, countryId);
    }

    public static int getCountryId(Context context) {
        return getInt(context,USER_CountryID);
    }

    public static void putCountryName(Context context, String countryName) {
        putString(context, USER_CountryName, countryName);
    }

    public static String getCountryName(Context context) {
        return getString(context,USER_CountryName);
    }

    public static void putIqama(Context context, String Iqama) {
        putString(context, Talent_id, Iqama);
    }

    public static int getTalent(Context context) {
        return getInt(context,Talent_id);
    }

    public static void putLang(Context context, String Lang) {
        putString(context, USER_Lang, Lang);
    }

    public static String getLang(Context context) {
        return getString(context,USER_Lang);
    }




    public static void putToken(Context context,String usertoken) {
        putString(context, USER_token, usertoken);
    }

    public static String getToken(Context context) {
        return getString(context,USER_token);
    }


    public static void putUserId(Context context, int id) {
        putInt(context, USER_ID, id);
    }
    public static int getUserId(Context context) {
        return getInt(context,USER_ID);
    }

    public static void putUSERPermission(Context context, int id) {
        putInt(context, USER_Permission, id);
    }
    public static int getUSERPermission(Context context) {
        return getInt(context,USER_Permission);
    }

    public static String getName(Context context) {
        return getString(context,USER_NAME);
    }


    public static String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(20000);
        con.setReadTimeout(20000);
        return readStream(con);
    }

    public static String sendPost(String url, String urlParameters) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(20000);
        con.setReadTimeout(20000);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        return readStream(con);
    }

    private static String readStream(HttpURLConnection con) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
