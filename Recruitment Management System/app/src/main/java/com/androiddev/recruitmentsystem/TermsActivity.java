package com.androiddev.recruitmentsystem;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class TermsActivity extends AppCompatActivity {
    WebView webView;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        webView = findViewById(R.id.web);
        back = findViewById(R.id.back_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // showing url of terms and conditions in webview
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.privacypolicygenerator.info/live.php?token=FlMiHxRafWHZUvQmiUhBuJVua3K4yp45");

// you need to setWebViewClient for forcefully open in your webview
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}