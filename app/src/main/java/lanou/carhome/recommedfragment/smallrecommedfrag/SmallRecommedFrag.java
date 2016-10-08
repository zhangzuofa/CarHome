package lanou.carhome.recommedfragment.smallrecommedfrag;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.main.DividerItemDecoration;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/30.
 */
public class SmallRecommedFrag extends BaseFragment {

    private PullToRefreshRecyclerView recyclerView;
    private Banner banner;
    private ArrayList<String> list;
    private View view;

    @Override
    protected int setLayout() {
        return R.layout.smallrecommed_frag;

    }

    @Override
    protected void initView() {

        recyclerView = bindView(R.id.small_recommed_frag_recycler);
        view = LayoutInflater.from(getContext()).inflate(R.layout.smallrecommed_topview,null);
        banner = bindView(R.id.small_recommed_topview_banner, view);
        

    }

    @Override
    protected void initDate() {




         innitRequestInternet();
        recyclerView.getRecyclerView().addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setSwipeEnable(true);
        initRefresh();

    }

    private void initRefresh() {
        recyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list = new ArrayList<>();
                GsonRequest<ReconmmedBean> gsonRequest = new GsonRequest<ReconmmedBean>(URLValues.URL_NEW,
                        ReconmmedBean.class, new Response.Listener<ReconmmedBean>() {
                    @Override
                    public void onResponse(ReconmmedBean response) {
                        SmallRecommedAdapter smallRecommedAdapter  = new SmallRecommedAdapter(getContext());
                        smallRecommedAdapter.setBean(response);
                        recyclerView.setAdapter(smallRecommedAdapter);
                        LinearLayoutManager manager  = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(manager);
                        for (int i = 0; i < response.getResult().getFocusimg().size(); i++) {
                            list.add(response.getResult().getFocusimg().get(i).getImgurl());

                        }
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.setIndicatorGravity(BannerConfig.RIGHT);
                        banner.setImages(list);
                        recyclerView.setOnRefreshComplete();
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VollaySingleton.getInstance().addRequest(gsonRequest);



            }
        });
    }

    private void innitRequestInternet() {
        list = new ArrayList<>();
        GsonRequest<ReconmmedBean> gsonRequest = new GsonRequest<ReconmmedBean>(URLValues.URL_NEW,
                ReconmmedBean.class, new Response.Listener<ReconmmedBean>() {
            @Override
            public void onResponse(ReconmmedBean response) {
                SmallRecommedAdapter smallRecommedAdapter  = new SmallRecommedAdapter(getContext());
                smallRecommedAdapter.setBean(response);
                recyclerView.setAdapter(smallRecommedAdapter);
                LinearLayoutManager manager  = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                for (int i = 0; i < response.getResult().getFocusimg().size(); i++) {
                 list.add(response.getResult().getFocusimg().get(i).getImgurl());
                    
                }
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                banner.setIndicatorGravity(BannerConfig.RIGHT);
                banner.setImages(list);
                recyclerView.addHeaderView(view);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }
}
