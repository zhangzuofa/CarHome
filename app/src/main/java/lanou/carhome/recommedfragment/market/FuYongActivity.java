package lanou.carhome.recommedfragment.market;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/10/9.
 */
public class FuYongActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.fuyongactivity;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.fuyong_webview_Activity_webview);


    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        String str = intent.getStringExtra("复用网址");
        webView.loadUrl(str);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);




}
}
