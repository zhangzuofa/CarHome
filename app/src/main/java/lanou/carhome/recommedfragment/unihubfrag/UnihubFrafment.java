package lanou.carhome.recommedfragment.unihubfrag;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/10/8.
 */
public class UnihubFrafment extends BaseFragment {

    private PullToRefreshListView listView;

    @Override
    protected int setLayout() {
        return R.layout.unihubfragment;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.pullRefresh_unihubfragment);

        


    }

    @Override
    protected void initDate() {
        initRequestInternet();
        initRefrshTo();

    }

    private void initRefrshTo() {
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
        GsonRequest<UnhubBean> gsonRequst = new GsonRequest<UnhubBean>(URLValues.URL_YC, UnhubBean.class,
                new Response.Listener<UnhubBean>() {
                    @Override
                    public void onResponse(UnhubBean response) {
                        UnihubAdapter unihubAdapter = new UnihubAdapter(getContext());
                        unihubAdapter.setBean(response);
                        listView.setAdapter(unihubAdapter);
                        listView.onRefreshComplete();




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequst);

    }


}
