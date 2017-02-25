package com.servicedeskmanager.servicedesk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SDUtil {
static String sessionGroup="SDEverest";
    public static void setSession(Context context, String key, String value) {
        try {
            SharedPreferences pref = context.getSharedPreferences(sessionGroup, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(key, value); // Storing string
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSession(Context context, String key) {
        SharedPreferences prefs = null;
        try {

            prefs = context.getSharedPreferences(sessionGroup, Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefs.getString(key, null);
    }

    public static void callIntent(Context context, Class t, Activity t2, String backpossible) {
        try {
            Intent myIntent = new Intent(context, t);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (backpossible.equalsIgnoreCase("no")) {
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
            t2.startActivity(myIntent);
            //t2.overridePendingTransition(R.anim.exit_to_left, R.anim.enter_from_left);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
