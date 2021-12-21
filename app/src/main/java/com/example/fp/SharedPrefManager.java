package com.example.fp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences("sharedpref", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveString(String value) {
        editor.putString("str",value);
        editor.commit();
    }

    public void saveUser(String value) {
        editor.putString("user", value);
        editor.apply();
        editor.commit();
    }

    public void saveEmail(String value) {
        editor.putString("pass",value);
        editor.apply();
        editor.commit();
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
    public String getString() {
        return sharedPreferences.getString("str", "");
    }

    public String getEmail() {
        return sharedPreferences.getString("mail", "");
    }

    public String getUser() {
        return sharedPreferences.getString("user", "");
    }

    public String getPass() {
        return sharedPreferences.getString("pass", "");
    }

    public void savelogin(Boolean value){
        editor.putBoolean("svlogin",value);
        editor.commit();
    }

    public boolean getlogin() {
        return sharedPreferences.getBoolean("svlogin", false);
    }
}
