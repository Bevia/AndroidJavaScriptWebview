package com.corebaseit.androidjavascriptwebview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private WebView browser;
    private AlertDialog.Builder alertDialog;
    final int version = Build.VERSION.SDK_INT;
    RelativeLayout statusbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        browser = (WebView) findViewById(R.id.webkit);
        browser.getSettings().setJavaScriptEnabled(true);

        /**
         * To bind a new interface between your JavaScript and Android code,
         * call addJavascriptInterface(), passing it a class instance
         * to bind to your JavaScript and an interface name that your
         * JavaScript can call to access the class. So Inject WebAppInterface
         * methods into Web page by binding to Interface 'Android'
         */
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");
        //load the webview with this http address:
        browser.loadUrl(getString(R.string.UrlToLoad));
    }

    //Class to be injected into Web page


    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Caution: If you've set your targetSdkVersion to 17 or higher,
         * you must add the @JavascriptInterface annotation to any method
         * that you want available to your JavaScript (the method must also be public).
         * If you do not provide the annotation, the method is not accessible
         * by your web page when running on Android 4.2 or higher.
         */
        @JavascriptInterface   // must be added for API 17 or higher
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        /**
         * Show Dialog
         *
         * @param dialogMsg
         */
        @JavascriptInterface   // must be added for API 17 or higher
        public void showDialog(String dialogMsg) {
            //Instantiate an AlertDialog.Builder with its constructor
            if (version >= 23) {
                alertDialog = new AlertDialog.Builder(mContext,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
            } else {
                alertDialog = new AlertDialog.Builder(mContext, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            }
            //Chain together various setter methods to set the dialog characteristics
            alertDialog.setMessage(R.string.dialogTxt)
                    .setTitle(R.string.showDialogTitle);
            //Get the AlertDialog from create()
            AlertDialog dialog = alertDialog.create();
            // Setting Dialog Message
            dialog.setMessage(dialogMsg);
            // Add the buttons
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            // Showing Alert Message
            alertDialog.show();
        }

        /**
         * Intent - Move to next activity
         */
        @JavascriptInterface   // must be added for API 17 or higher
        public void moveToNextScreen() {
            if (version >= 23) {
                alertDialog = new AlertDialog.Builder(mContext,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
            } else {
                alertDialog = new AlertDialog.Builder(mContext, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            }
            // Setting Dialog Title
            alertDialog.setTitle("Alert!");
            // Setting Dialog Message
            alertDialog.setMessage(R.string.nextscreenmessage);
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Move ONTO Next screen
                            Intent chnIntent = new Intent(MainActivity.this,
                                    StartNewIntentWebView.class);
                            startActivity(chnIntent);
                        }
                    });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            // Showing Alert Message
            alertDialog.show();
        }
    }
}
