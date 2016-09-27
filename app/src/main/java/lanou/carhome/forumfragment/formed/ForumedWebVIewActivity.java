package lanou.carhome.forumfragment.formed;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/9/27.
 */
public class ForumedWebVIewActivity  extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView webViewImg;

    @Override
    protected int setLayout() {
        return R.layout.forumed_webview_activity;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.forumed_webview_Activity_webview);
        webViewImg = bindView(R.id.forumed_webview_Activity_img);
        webViewImg.setOnClickListener(this);



    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
       String str = intent.getStringExtra("论坛详情网址");

        webView.loadUrl(str);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forumed_webview_Activity_img:
                super.onBackPressed();
                break;
        }

    }
}
