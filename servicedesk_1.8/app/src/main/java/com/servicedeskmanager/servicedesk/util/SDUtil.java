package com.servicedeskmanager.servicedesk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.servicedeskmanager.servicedesk.R;

import java.util.HashMap;
import java.util.Map;

public class SDUtil {
static String sessionGroup="SDEverest";
    private static Map<String, Integer> priorityColorMap = new HashMap<String, Integer>();
    private static Map<String, Integer> stateColorMap =  new HashMap<String, Integer>();;

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

    public static Integer getPriorityColor(String priority){
        if(priorityColorMap.isEmpty()) {
            priorityColorMap.put("1 - Critical", R.color.Critical);
            priorityColorMap.put("2 - High", R.color.High);
            priorityColorMap.put("3 - Medium", R.color.Medium);
            priorityColorMap.put("4 - Low", R.color.Low);
            priorityColorMap.put("5 - Very Low", R.color.Very_low);
        }
        return priorityColorMap.get(priority);
    }

    public static Integer getStateColor(String state){
        if(stateColorMap.isEmpty()) {
            stateColorMap.put("Open", R.color.Open);
            stateColorMap.put("In Progress", R.color.In_Progress);
            stateColorMap.put("On Hold", R.color.On_Hold);
            stateColorMap.put("Resolved", R.color.Resolved);
            stateColorMap.put("Closed", R.color.Closed);
            stateColorMap.put("Reopen", R.color.Reopen);
        }
        return stateColorMap.get(state);
    }

    public static Integer getCriticalityColor(String criticality){
        if(priorityColorMap.isEmpty()) {
            priorityColorMap.put("1 - Critical", R.color.Critical);
            priorityColorMap.put("2 - High", R.color.High);
            priorityColorMap.put("3 - Medium", R.color.Medium);
            priorityColorMap.put("4 - Low", R.color.Low);
            priorityColorMap.put("5 - Very Low", R.color.Very_low);
        }
        return priorityColorMap.get(criticality);
    }
}
