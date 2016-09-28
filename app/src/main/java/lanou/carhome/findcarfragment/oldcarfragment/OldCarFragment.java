package lanou.carhome.findcarfragment.oldcarfragment;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.volley.URLValues;

/**
 * Created by dllo on 16/9/27.
 */
public class OldCarFragment extends BaseFragment {

    private WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.oldcar_fragment;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.oldcar_webview);


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


    }


}
