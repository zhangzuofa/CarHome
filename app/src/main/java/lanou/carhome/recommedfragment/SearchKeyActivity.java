package lanou.carhome.recommedfragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.find.dbtool.SearchCarNameBean;
import lanou.carhome.main.EncodeUtil;
import lanou.carhome.recommedfragment.search.CarSesrchBean;
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

    @Override
    protected int setLayout() {
        return R.layout.searchkey_acitivity;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        ed = bindView(R.id.recommed_search_activity_ed);
        imgDelete = bindView(R.id.recommed_search_activity_img);
        imgDelete.setOnClickListener(this);
       ed.setOnClickListener(this);
        listView = bindView(R.id.searchactivity_lisrview);
        webView = bindView(R.id.search_activity_web);
        mLiteOrm = LiteOrm.newSingleInstance(this,"myDataBase.db");

    }

    @Override
    protected void initDate() {
        initEdtext();



        }

    private void initEdtext() {
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 0) {
                    imgDelete.setVisibility(View.GONE);
                } else {
                    imgDelete.setVisibility(View.VISIBLE);
                }
             innitRequestInternet(s);

            }
        });




    }

    private void innitRequestInternet(Editable s) {
        if (!s.toString().equals("")){


        String str =s.toString();
        String url = "http://mobilenc.app.autohome.com.cn/sou_v5.7.0/sou/suggestwords.ashx?pm=2&k=" + str+ "&t=4";
        GsonRequest<CarSesrchBean>gsonrequest = new GsonRequest<CarSesrchBean>(url, CarSesrchBean.class, new Response.Listener<CarSesrchBean>() {
            @Override
            public void onResponse(CarSesrchBean response) {
                SearchAdapter searchAdapter = new SearchAdapter(SearchKeyActivity.this);
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
                String str= EncodeUtil.encode(response.getResult().getWordlist().get(position).getName());
                String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword="+ str + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
              //  Log.d("SearchKeyActivity", searchUrl);
                String carName =response.getResult().getWordlist().get(position).getName();
                ed.setText(carName);
                SearchCarNameBean bean = new SearchCarNameBean();
                bean.setName(carName);
//                dbTool.insertPerdson(bean);
                mLiteOrm.insert(bean);


                webView.setVisibility(View.VISIBLE);
                webView.loadUrl(searchUrl);
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
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recommed_search_activity_img:

                ed.setText("");

             //  ArrayList<SearchCarNameBean> query =  dbTool.getAllPerson();
                ArrayList<SearchCarNameBean> query=
                        mLiteOrm.query(SearchCarNameBean.class);
                for (SearchCarNameBean bean:query){
                    list.add(bean);
                }


            //    ArrayAdapter adapter = new ArrayAdapter(SearchKeyActivity.this,R.l,list);
            //    listView.setAdapter(adapter);








                break;
            case R.id.recommed_search_activity_ed:
                if (!ed.getText().equals("")){
                    listView.setVisibility(View.VISIBLE);
                    webView.setVisibility(View.GONE);


                }


                break;
        }
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (ed.getText()==null){
//            img.setVisibility(ImageView.INVISIBLE);
//        } else {
//            img.setVisibility(ImageView.VISIBLE);
//        }
//
//    }
}
