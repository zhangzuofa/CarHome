package lanou.carhome.forumfragment.threetyeight;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;


/**
 * Created by dllo on 16/9/29.
 */
public class ThirtyNineWebViewActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView img;
    private Button btn;
    private String url;

    @Override
    protected int setLayout() {
        return R.layout.thirtynine_webview_acitvity;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        webView = bindView(R.id.thirtynine_Activity_webview);
        img = bindView(R.id.thirtynine_webview_Activity_img);
        img.setOnClickListener(this);
        btn = bindView(R.id.thirtynine_Activity_webview_share_btn);
        btn.setOnClickListener(this);




    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        url = intent.getStringExtra("39贴详情");
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

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("作发带你看车!!!!");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用

        oks.setSiteUrl(url);

// 启动分享GUI
        oks.show(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.thirtynine_webview_Activity_img:
                super.onBackPressed();
                break;
            case R.id.thirtynine_Activity_webview_share_btn:
                showShare();
                break;
        }

    }
}
