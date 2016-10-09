package lanou.carhome.recommedfragment.smallrecommedfrag;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/10/9.
 */
public class RecommedDetailActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.recommeddetailactivity;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.recommeddetail_Activity_webview);


    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        String str = intent.getStringExtra("推荐");
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
