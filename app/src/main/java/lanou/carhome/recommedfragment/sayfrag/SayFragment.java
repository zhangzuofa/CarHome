package lanou.carhome.recommedfragment.sayfrag;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
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


    }

    private void innitRequestInternet() {
        GsonRequest<SayBean> gsonRequest = new GsonRequest<SayBean>(URLValues.URL_SAY, SayBean.class,
                new Response.Listener<SayBean>() {
                    @Override
                    public void onResponse(SayBean response) {
                        SayAdapter sayAdpter = new SayAdapter(getContext());
                        sayAdpter.setBean(response);
                        listView.setAdapter(sayAdpter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }
}
