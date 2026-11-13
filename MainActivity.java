package com.quantum.s3simulator;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONObject;

public class MainActivity extends Activity {
    private WebView webView;
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this); setContentView(webView);
        WebSettings s = webView.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setAllowFileAccess(true); s.setAllowContentAccess(true);
        webView.setWebViewClient(new WebViewClient()); webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new DashboardBridge(this), "AndroidDashboard");
        webView.loadUrl("file:///android_asset/index.html");
    }
    @Override public void onBackPressed() { if (webView != null && webView.canGoBack()) webView.goBack(); else super.onBackPressed(); }
    public static class DashboardBridge {
        private final Context context; DashboardBridge(Context c){context=c.getApplicationContext();}
        @JavascriptInterface public void updateDashboard(String json) {
            try { JSONObject o=new JSONObject(json); SharedPreferences p=context.getSharedPreferences("dashboard", Context.MODE_PRIVATE);
                p.edit().putString("soc", o.isNull("soc")?"—":String.format(java.util.Locale.US,"%.0f%%",o.optDouble("soc")))
                .putString("kwh", o.isNull("kwh")?"—":String.format(java.util.Locale.US,"%.2f kWh",o.optDouble("kwh")))
                .putString("range", o.isNull("range")?"—":String.format(java.util.Locale.US,"%.1f km",o.optDouble("range")))
                .putString("finish",o.optString("finish","—")).putString("status",o.optString("status","Simulator snapshot")).putLong("updated",System.currentTimeMillis()).apply();
                BatteryWidgetProvider.updateAll(context);
            } catch(Exception ignored){}
        }
    }
}
