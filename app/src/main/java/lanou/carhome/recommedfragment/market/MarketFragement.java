package lanou.carhome.recommedfragment.market;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/10/8.
 */
public class MarketFragement extends BaseFragment {

    private PullToRefreshListView listView;
    private String url;

    public static MarketFragement newInstance(String url) {
        
        Bundle args = new Bundle();
        args.putString("url",url);
        MarketFragement fragment = new MarketFragement();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.marketfragment;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.pullRefresh_marketfragment);


    }

    @Override
    protected void initDate() {
        Bundle b = getArguments();
        url = b.getString("url");

        initRequestInternet();
        initPullToRefrsh();

    }

    private void initPullToRefrsh() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                initRequestInternet();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

    }


    private void initRequestInternet() {
        final GsonRequest<MarketBean> gsonRequest = new GsonRequest<MarketBean>(url, MarketBean.class,
                new Response.Listener<MarketBean>() {
                    @Override
                    public void onResponse(MarketBean response) {
                        listView.onRefreshComplete();
                  MarketAdapter maeketAdapter = new MarketAdapter(getContext());
                        maeketAdapter.setBean(response);
                        listView.setAdapter(maeketAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VollaySingleton.getInstance().addRequest(gsonRequest);
}
}