package comm.mrspdd.lockdownsevaapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.ssduo.lockdownsevaapp.BuildConfig;
import com.ssduo.lockdownsevaapp.R;

import comm.mrspdd.lockdownsevaapp.Ui.Activities.OrdersActivity;
import comm.mrspdd.lockdownsevaapp.Util.COnsty;

///////////////////////////////////////////////////////////////////////////
// Created with ❤  by Satyamurti only for Udacity
/////////////////////////////////////////////////////////////////////////
public class WidgetForInDevice extends AppWidgetProvider {
    @Override
    public void onEnabled(Context context) {
    }
    ///////////////////////////////////////////////////////////////////////////
// Created with ❤  by Satyamurti only for Udacity
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onDisabled(Context context) {
    }
    public static void updatemywidget(Context c, AppWidgetManager huappp90,
                                      int appWidgetId) {
        RemoteViews vf = new RemoteViews(c.getPackageName(), R.layout.widget_layout);
        SharedPreferences s = c.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        vf.setTextViewText(R.id.tvMyname, s.getString(COnsty.P_TITLE, ""));
        vf.setTextViewText(R.id.tv3, s.getString(COnsty.CONTENT_2703, ""));

        Intent intent = new Intent(c, OrdersActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);
        vf.setOnClickPendingIntent(R.id.tvMyname, pendingIntent);
        vf.setOnClickPendingIntent(R.id.tv3, pendingIntent);
        huappp90.updateAppWidget(appWidgetId, vf);
    }
    ///////////////////////////////////////////////////////////////////////////
// Created with ❤  by Satyamurti only for Udacity
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] ifds) {
        for (int ids : ifds) {
            updatemywidget(context, appWidgetManager, ids);
        }
    }


}
///////////////////////////////////////////////////////////////////////////
// Created with ❤  by Satyamurti only for Udacity
/////////////////////////////////////////////////////////////////////////
