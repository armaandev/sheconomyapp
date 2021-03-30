package com.sheconomy.sheeconomy.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionForCountry {

        private SharedPreferences prefs;

        public SessionForCountry(Context cntx) {
            // TODO Auto-generated constructor stub
            prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
        }

        public void setusename(String usename) {
            prefs.edit().putString("usename", usename).commit();
        }

        public String getusename() {
            String usename = prefs.getString("usename","Arman");
            return usename;
        }
    }


