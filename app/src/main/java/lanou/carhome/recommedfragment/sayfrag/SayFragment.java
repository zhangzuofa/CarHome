package lanou.carhome.recommedfragment.sayfrag;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.recommedfragment.market.FuYongActivity;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/10/8.
 */
public class SayFragment extends BaseFragment {

    private PullToRefreshListView listView;

    @Override
    protected int setLayout() {
        return R.layout.sayfragment;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.pullRefresh_sayfragment);


    }

    @Override
    protected void initDate() {
        innitRequestInternet();
        initRefrshTo();


    }
    private void initRefrshTo() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                innitRequestInternet();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    private void innitRequestInternet() {
        GsonRequest<SayBean> gsonRequest = new GsonRequest<SayBean>(URLValues.URL_SAY, SayBean.class,
                new Response.Listener<SayBean>() {
                    @Override
                    public void onResponse(SayBean response) {
                        SayAdapter sayAdpter = new SayAdapter(getContext());
                        sayAdpter.setBean(response);
                        listView.setAdapter(sayAdpter);
                        listView.onRefreshComplete();
                        onClickListener(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }

    private void onClickListener(final SayBean response) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n";
                String str1 ="-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
                int str2 = response.getResult().getList().get(position).getId();
                String url = str+ str2 + str1;
                Intent inent = new Intent(getContext(),FuYongActivity.class);
                inent.putExtra("复用网址",url);
                startActivity(inent);
            }
        });


    }
}
