package lanou.carhome.forumfragment.hotforum;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;


/**
 * Created by dllo on 16/9/27.
 */
public class HotForumDetailActivity  extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView img;

    @Override
    protected int setLayout() {
        return R.layout.hotforum_dedail_activity;
    }

    @Override
    protected void initView() {

        img = bindView(R.id.hotForumdeTail_Activity_img);
        img.setOnClickListener(this);
        webView = bindView(R.id.hotForumdetail_Activity_Webview);



    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();

        String str =  intent.getStringExtra("热帖详情");

        webView.loadUrl(str);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hotForumdeTail_Activity_img:
                super.onBackPressed();
                break;
        }

    }
}
