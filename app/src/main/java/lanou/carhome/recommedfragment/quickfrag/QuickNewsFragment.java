package lanou.carhome.recommedfragment.quickfrag;

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
public class QuickNewsFragment extends BaseFragment {

    private PullToRefreshListView listView;

    @Override
    protected int setLayout() {
        return R.layout.quicknewsfragment;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.pullRefresh_quicknews);




    }

    @Override
    protected void initDate() {
        initRequestInternet();

    }

    private void initRequestInternet() {
        GsonRequest<QuickNewsBean> gsonRequest = new GsonRequest<QuickNewsBean>(URLValues.URL_QUICK, QuickNewsBean.class,
                new Response.Listener<QuickNewsBean>() {
                    @Override
                    public void onResponse(QuickNewsBean response) {
                        QuickNewsAdpater quickNewsAdapter = new QuickNewsAdpater(getContext());
                        quickNewsAdapter.setBean(response);
                        listView.setAdapter(quickNewsAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }
}
