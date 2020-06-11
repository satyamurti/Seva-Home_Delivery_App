package comm.mrspdd.lockdownsevaapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import com.ssduo.lockdownsevaapp.R;
///////////////////////////////////////////////////////////////////////////
// Made with ❤  by Satyamurti
///////////////////////////////////////////////////////////////////////////
public class customLoadingBar {
    Activity activity;
    AlertDialog dialog;

    public customLoadingBar(Activity myActivity){

        activity = myActivity;

    }
   public void startLoader(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);


        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading_bar,null));

        dialog= builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }
    public void dismissLoader(){
        dialog.dismiss();
    }
}
