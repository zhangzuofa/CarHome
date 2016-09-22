package lanou.carhome.forumfragment.hotforum;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/21.
 */
public class HotActivity  extends BaseActivity implements View.OnClickListener {

    private ImageView hotImg;
    private PullToRefreshListView listView;

    @Override
    protected int setLayout() {
        return R.layout.hotacitivty;
    }

    @Override
    protected void initView() {
        hotImg = bindView(R.id.hotActivity_img);
        hotImg.setOnClickListener(this);
        listView = bindView(R.id.Refresh_HotAcitivity);



    }

    @Override
    protected void initDate() {
        innitRequestInternet();
        ListView listViewTop = listView.getRefreshableView();
        View viewTop = LayoutInflater.from(this).inflate(R.layout.hours_viewtop,null);
        listViewTop.addHeaderView(viewTop);
        innitPullToRefresh();




    }

    private void innitPullToRefresh() {

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
        GsonRequest<HotBean> gsonRequest = new GsonRequest<HotBean>(URLValues.Hot_URL, HotBean.class, new Response.Listener<HotBean>() {
            @Override
            public void onResponse(HotBean response) {
                listView.onRefreshComplete();
                HotAdapter adapter = new HotAdapter(HotActivity.this);
                adapter.setBean(response);
                listView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hotActivity_img:
                super.onBackPressed();
                break;
        }


    }
}
