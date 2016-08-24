package com.corebaseit.androidjavascriptwebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class StartNewIntentWebView extends AppCompatActivity {

    private WebView browser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_intent_web_view);

        browser = (WebView) findViewById(R.id.webkit);
        //Enable Javascript
        browser.getSettings().setJavaScriptEnabled(true);
        // Force links and redirects to open in the WebView instead of in a browser
        browser.setWebViewClient(new WebViewClient());
        //load the webview with this http address:
        browser.loadUrl(getString(R.string.newWebViewUrl));
        //set onKeyListener to navegate:
        browser.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (browser.isFocused() && browser.canGoBack()) {
            browser.goBack();
        } else {
            super.onBackPressed();
            finish();
        }
    }
}