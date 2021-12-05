package com.example.interviewtask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonA, buttonB, buttonReload;
    WebView myWebView;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonA = findViewById(R.id.button_first);
        buttonB = findViewById(R.id.button_second);
        buttonReload = findViewById(R.id.button_reload);

        myWebView = findViewById(R.id.my_web_view);
        myWebView.getSettings().setJavaScriptEnabled(true);
//        myWebView.addJavascriptInterface(new JavaScriptInterface() {
//            @Override
//            public void showToast() {
//                Toast.makeText(MainActivity.this, "Action", Toast.LENGTH_SHORT).show();
//            }
//        }, "Android");

        myWebView.setWebViewClient(new MyWebViewClient());

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.loadUrl("https://www.tutorialspoint.com/android/index.htm");
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.loadUrl("https://en.wikipedia.org/wiki/Android_(operating_system)");
            }
        });

        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.reload();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack() && myWebView.isFocused()) {
            myWebView.goBack();
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Toast.makeText(MainActivity.this, "OnPageStarted!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Toast.makeText(MainActivity.this, "OnPageFinished!", Toast.LENGTH_SHORT).show();
        }
    }

}