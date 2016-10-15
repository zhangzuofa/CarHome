package lanou.carhome.findcarfragment.oldcarfragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.volley.URLValues;

/**
 * Created by dllo on 16/9/27.
 */
public class OldCarFragment extends BaseFragment {

    private WebView webView;

   private ProgressBar mPbLoading;
    private MyHandler mHandler;
    private static class MyHandler extends Handler {

        private WeakReference<OldCarFragment> mWeakActivity;

        public MyHandler(OldCarFragment activity) {
            mWeakActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {

            OldCarFragment oldCarFragment = mWeakActivity.get();
            if (oldCarFragment != null) {
                // 更新进度条


                oldCarFragment.mPbLoading.setProgress(msg.arg1);

                // 更新数值显示
//                activity.mTvProgress.setText(msg.arg1 + "%");
            }
            if (msg.arg1 == 100) {
                oldCarFragment.mPbLoading.setVisibility(View.GONE);
            }

        }
    }

    @Override
    protected int setLayout() {
        return R.layout.oldcar_fragment;

    }

    @Override
    protected void initView() {
        webView = bindView(R.id.oldcar_webview);
        mHandler = new MyHandler(this);
        mPbLoading = bindView(R.id.pb_loading);


    }

    @Override
    protected void initDate() {




        webView.loadUrl(URLValues.OLDCAR_DL_URL);
        webView.setWebViewClient(new WebViewClient(){
          @Override
          public boolean shouldOverrideUrlLoading(WebView view, String url) {
              view.loadUrl(url);
              return true;
          }
      });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
      //  settings.setDomStorageEnabled(true);
//        webView.getSettings().setBuiltInZoomControls(false);
//        webView.getSettings().setJavaScriptEnabled(true);
//      //  webView.getSettings().setRenderPriority(RenderPriority.HIGH);
//        webView.getSettings().setBlockNetworkImage(true);


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
       initDate();
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void onStart() {
        super.onStart();

        // 启动线程模拟加载
        new Thread() {
            @Override
            public void run() {
//                while () {
                try {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);
                        if (i ==80){
                            Thread.sleep(500);
                        }
                        Message message = mHandler.obtainMessage();
                        message.arg1 = i;
                        mHandler.sendMessage(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                }
            }
        }.start();

    }


}
