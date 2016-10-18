package lanou.carhome.recommedfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.find.dbtool.SearchCarNameBean;
import lanou.carhome.main.EncodeUtil;
import lanou.carhome.recommedfragment.search.CarSesrchBean;
import lanou.carhome.recommedfragment.search.HistoryAdapter;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/30.
 */
public class SearchKeyActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgDelete;
    private EditText ed;
    private ListView listView;
    private WebView webView;
//     DBTool dbTool = DBTool.getintance();

    private LiteOrm mLiteOrm;
    private ArrayList<SearchCarNameBean> list;
    private HistoryAdapter historyAdapter;
    private View view;
    private SearchAdapter searchAdapter;
    private ArrayList<SearchCarNameBean> newList;


    @Override
    protected int setLayout() {
        return R.layout.searchkey_acitivity;
    }

    @Override
    protected void initView() {

        ed = bindView(R.id.recommed_search_activity_ed);
        imgDelete = bindView(R.id.recommed_search_activity_img);
        imgDelete.setOnClickListener(this);
        ed.setOnClickListener(this);
        listView = bindView(R.id.searchactivity_lisrview);
        webView = bindView(R.id.search_activity_web);
        mLiteOrm = LiteOrm.newSingleInstance(this, "myDataBase.db");
        view = LayoutInflater.from(this).inflate(R.layout.topviewsaerchcar, null);

        ImageView historyChaImg = bindView(R.id.topviewsearch_img, view);
        historyChaImg.setOnClickListener(this);
        TextView tv = bindView(R.id.search_cancel_tv);
        tv.setOnClickListener(this);

    }

    @Override
    protected void initDate() {
        list = new ArrayList<>();
        initEdtext();
        ArrayList<SearchCarNameBean> query =
                mLiteOrm.query(SearchCarNameBean.class);
        for (SearchCarNameBean bean : query) {

            list.add(0, bean);

        }

        historyAdapter = new HistoryAdapter(this);
        historyAdapter.setArrayList(list);
        listView.setAdapter(historyAdapter);

        if (mLiteOrm.query(SearchCarNameBean.class).size() > 0) {
            listView.addHeaderView(view);

        }


    }


    private void initEdtext() {

        // edittext 监控方法
        ed.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                newList = new ArrayList<SearchCarNameBean>();


                if (s.length() == 0) {
                    imgDelete.setVisibility(View.GONE);
//
                    ArrayList<SearchCarNameBean> query =
                            mLiteOrm.query(SearchCarNameBean.class);
                    for (SearchCarNameBean bean : query) {

                        newList.add(0, bean);
//
                    }
                    if (newList.size() > 0) {
                        view.setVisibility(View.VISIBLE);
                    }

                    historyAdapter.setArrayList(newList);

                    listView.setAdapter(historyAdapter);
                    //  view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                    imgDelete.setVisibility(View.VISIBLE);
                    ArrayList<SearchCarNameBean> query =
                            mLiteOrm.query(SearchCarNameBean.class);
                    for (SearchCarNameBean bean : query) {

                        newList.add(0, bean);

                    }
//

                }
                innitRequestInternet(s);
                initPhoneSearch(s);
            }
        });


    }

    private void initPhoneSearch(final Editable s) {

        ed.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String str = EncodeUtil.encode(s.toString());
                String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword=" + str + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
                //  Log.d("SearchKeyActivity", searchUrl);

                ed.setText(s.toString());
                SearchCarNameBean bean = new SearchCarNameBean();
                bean.setName(s.toString());
//                dbTool.insertPerdson(bean);
                mLiteOrm.insert(bean);


                webView.setVisibility(View.VISIBLE);
                webView.loadUrl(searchUrl);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });

                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    // 当按了搜索之后关闭软键盘
                    ((InputMethodManager) ed.getContext().getSystemService(

                            Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            SearchKeyActivity.this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);


                    return true;
                }
                return false;
            }
        });

    }


    private void innitRequestInternet(Editable s) {
        if (!s.toString().equals("")) {


            String str = s.toString();
            String url = "http://mobilenc.app.autohome.com.cn/sou_v5.7.0/sou/suggestwords.ashx?pm=2&k=" + str + "&t=4";
            GsonRequest<CarSesrchBean> gsonrequest = new GsonRequest<CarSesrchBean>(url, CarSesrchBean.class, new Response.Listener<CarSesrchBean>() {


                @Override
                public void onResponse(CarSesrchBean response) {
                    searchAdapter = new SearchAdapter(SearchKeyActivity.this);
                    searchAdapter.setBean(response);
                    listView.setAdapter(searchAdapter);
                    initClicklisener(response);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            VollaySingleton.getInstance().addRequest(gsonrequest);
        }
    }

    private void initClicklisener(final CarSesrchBean response) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
                try {
                    String str = null;
                    str = EncodeUtil.encode(response.getResult().getWordlist().get(position - 1).getName());

                    String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword=" + str + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
                    //  Log.d("SearchKeyActivity", searchUrl);
                    String carName = response.getResult().getWordlist().get(position - 1).getName();
                    ed.setText(carName);
                    SearchCarNameBean bean = new SearchCarNameBean();
                    bean.setName(carName);
//                dbTool.insertPerdson(bean);
                    mLiteOrm.insert(bean);


                    webView.setVisibility(View.VISIBLE);
                    webView.loadUrl(searchUrl);
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }
                    });

                    WebSettings settings = webView.getSettings();
                    settings.setJavaScriptEnabled(true);


                } catch (IndexOutOfBoundsException e) {
                    //  e.printStackTrace();
                    try {
                        String str1 = list.get(position).getName();
                        // String str = null;
                        //  str1 = EncodeUtil.encode(response.getResult().getWordlist().get(position - 1).getName());

                        String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword=" + str1 + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
                        //  Log.d("SearchKeyActivity", searchUrl);
                        String carName = str1;
                        ed.setText(carName);
//                    SearchCarNameBean bean = new SearchCarNameBean();
//                    bean.setName(carName);
//                dbTool.insertPerdson(bean);
                        //                  mLiteOrm.insert(bean);


                        webView.setVisibility(View.VISIBLE);
                        webView.loadUrl(searchUrl);
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                view.loadUrl(url);
                                return true;
                            }
                        });

                        WebSettings settings = webView.getSettings();
                        settings.setJavaScriptEnabled(true);

                    } catch (IndexOutOfBoundsException e1) {

                    }


                }
                //  historyAdapter.i =


//                try {
//
//
//                    CarSesrchBean.ResultBean.WordlistBean wordlistBean = (CarSesrchBean.ResultBean.WordlistBean)parent.getItemAtPosition(position);
//                    Log.d("SearchKeyActivity", "wordlistBean:" + wordlistBean.getName());
//
//
//
//
//                }catch (NullPointerException e){
//
//                        SearchCarNameBean searchCarNameBean = (SearchCarNameBean)parent.getItemAtPosition(position);
//                        Log.d("SearchKeyActivity", "scascs"+searchCarNameBean.getName());
//
//
//
//
//                }catch (ClassCastException e){
//
//                    SearchCarNameBean searchCarNameBean = (SearchCarNameBean)parent.getItemAtPosition(position);
//                    Log.d("SearchKeyActivity", "scascs"+searchCarNameBean.getName());
//
//
//                }


            }
        });
    }


    @Override
    public void onClick(View v) {
        final ArrayList<SearchCarNameBean> listZuixin = new ArrayList<>();

        switch (v.getId()) {
            case R.id.recommed_search_activity_img:


                ed.setText("");

//                if (!ed.getText().equals("")) {
                listView.setVisibility(View.VISIBLE);
                historyAdapter.setArrayList(newList);
                listView.setAdapter(historyAdapter);


                if (webView.getVisibility() == View.VISIBLE) {
                    webView.setVisibility(View.GONE);
                }
                //

//                }


                break;
            case R.id.recommed_search_activity_ed:


                break;
            case R.id.topviewsearch_img:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("是否清空历史记录");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mLiteOrm.deleteAll(SearchCarNameBean.class);
                        view.setVisibility(View.GONE);
                        historyAdapter = new HistoryAdapter(SearchKeyActivity.this);

                        ArrayList<SearchCarNameBean> query =
                                mLiteOrm.query(SearchCarNameBean.class);
                        for (SearchCarNameBean bean : query) {

                            listZuixin.add(0, bean);

                        }
                        historyAdapter.setArrayList(listZuixin);
                        listView.setAdapter(historyAdapter);
                    }
                });
                builder.show();

                break;

            case R.id.search_cancel_tv:
                this.finish();
                break;
        }
    }


}
