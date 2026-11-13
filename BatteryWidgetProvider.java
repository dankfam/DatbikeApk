package com.quantum.s3simulator;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.widget.RemoteViews;
import android.content.SharedPreferences;

public class BatteryWidgetProvider extends AppWidgetProvider {
    @Override public void onUpdate(Context c, AppWidgetManager m, int[] ids) { for(int id:ids) update(c,m,id); }
    static void updateAll(Context c){ AppWidgetManager m=AppWidgetManager.getInstance(c); int[] ids=m.getAppWidgetIds(new android.content.ComponentName(c,BatteryWidgetProvider.class)); for(int id:ids) update(c,m,id); }
    private static void update(Context c, AppWidgetManager m, int id){
        SharedPreferences p=c.getSharedPreferences("dashboard",Context.MODE_PRIVATE); RemoteViews v=new RemoteViews(c.getPackageName(),R.layout.battery_widget);
        v.setTextViewText(R.id.widget_soc,p.getString("soc","Open app to sync"));
        v.setTextViewText(R.id.widget_kwh,p.getString("kwh","— kWh"));
        v.setTextViewText(R.id.widget_range,p.getString("range","— km"));
        v.setTextViewText(R.id.widget_status,p.getString("status","Quantum S3 dashboard"));
        Intent i=c.getPackageManager().getLaunchIntentForPackage(c.getPackageName()); PendingIntent pi=PendingIntent.getActivity(c,0,i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE); v.setOnClickPendingIntent(R.id.widget_root,pi); m.updateAppWidget(id,v);
    }
}
