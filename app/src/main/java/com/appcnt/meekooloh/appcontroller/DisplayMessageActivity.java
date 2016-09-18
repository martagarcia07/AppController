package com.appcnt.meekooloh.appcontroller;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marta on 14/09/16.
 */
public class DisplayMessageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }
    /** Called when the user clicks the Send button */
    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
    public void info(View view){
        String finalInfo = "$$";

        EditText mEdit = (EditText) findViewById(R.id.find_info);
        String app2find = mEdit.getText().toString();
        //mEdit.getText().clear();
        final PackageManager pm = getPackageManager();
        //get a list of installed apps.

        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            if ( app2find.trim().length() > 0 && (packageInfo.packageName).toLowerCase().contains(app2find )){
                //Log.d(TAG, "Installed package :" + packageInfo.packageName);
                //Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
                //Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
                finalInfo= finalInfo.concat(  "Installed package :" + packageInfo.packageName+"\n\n"+
                        "Source dir : " + packageInfo.sourceDir  +"\n\n"+ "Launch Activity :" +
                        pm.getLaunchIntentForPackage(packageInfo.packageName) +"\n\n\n\n"  );
                //Log.d(TAG, finalInfo );


            }

        }


        // the getLaunchIntentForPackage returns an intent that you can use with startActivity()
        TextView t = (TextView) findViewById(R.id.tv_info);
        if (finalInfo != "$$"){

            t.setText(finalInfo);
        }else{

            t.setText("No app found");
        }
        t.setMovementMethod(new ScrollingMovementMethod());
    }
    public void startApp(View view){

        EditText mEdit = (EditText) findViewById(R.id.find_info);
        String app2find = mEdit.getText().toString();
        final PackageManager pm = getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            if ( app2find.trim().length() > 0 && (packageInfo.packageName).toLowerCase().contains(app2find )){
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageInfo.packageName);
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }



            }

        }

    }

}
