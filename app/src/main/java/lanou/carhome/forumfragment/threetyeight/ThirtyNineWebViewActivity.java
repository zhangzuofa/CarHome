package lanou.carhome.forumfragment.threetyeight;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;


/**
 * Created by dllo on 16/9/29.
 */
public class ThirtyNineWebViewActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView img;

    @Override
    protected int setLayout() {
        return R.layout.thirtynine_webview_acitvity;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.thirtynine_Activity_webview);
        img = bindView(R.id.thirtynine_webview_Activity_img);
        img.setOnClickListener(this);


    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
       String url =  intent.getStringExtra("39贴详情");
        webView.loadUrl(url);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.thirtynine_webview_Activity_img:
                super.onBackPressed();
                break;
        }

    }
}
