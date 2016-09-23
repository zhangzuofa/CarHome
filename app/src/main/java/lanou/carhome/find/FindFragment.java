package lanou.carhome.find;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.find.everyoneadpter.ForMeAdapter;
import lanou.carhome.main.DividerItemDecoration;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/19.
 */
public class FindFragment extends BaseFragment {

    private PullToRefreshListView listView;
    private View viewFindTop;
    private Banner banner;
    private View viewGoodsList;
    private RecyclerView rcForme;
    private View viewForMe;
    private ImageView img_ad;


    @Override
    protected int setLayout() {
        return R.layout.findfragment;
    }

    @Override
    protected void initDate() {

        innitRequestInternet();
        rcForme.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        rcForme.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL_LIST));
        ListView listViewT = listView.getRefreshableView();
        listViewT.addHeaderView(viewFindTop);
        listViewT.addHeaderView(viewForMe);
        listViewT.addHeaderView(viewGoodsList);





    }

    @Override
    protected void initView() {
        listView = bindView(R.id.RefreshListview_Find);
        viewFindTop = LayoutInflater.from(getContext()).inflate(R.layout.findtop_findfragment,null);
        img_ad = bindView(R.id.findtop_findfragment_img_Ad,viewFindTop);
        banner = bindView(R.id.banner_findFragmentTopView,viewFindTop);

        viewGoodsList = LayoutInflater.from(getContext()).inflate(R.layout.viewgoodslist,null);
        viewForMe = LayoutInflater.from(getContext()).inflate(R.layout.viewforme,null);
        rcForme = (RecyclerView) viewForMe.findViewById(R.id.forme_RecycleV);











    }

    private void innitRequestInternet() {
        final ArrayList<String> listBanner = new ArrayList<>();
        GsonRequest<FindBean> gsonRequest = new GsonRequest<FindBean>(URLValues.FIND_URL, FindBean.class, new Response.Listener<FindBean>() {
            @Override
            public void onResponse(FindBean response) {
                FindFragmentAdapter adapter = new FindFragmentAdapter(getContext());
                adapter.setBean(response);
                listView.setAdapter(adapter);
                ForMeAdapter forMeAdapter= new ForMeAdapter(getContext());
                forMeAdapter.setBean(response);
                rcForme.setAdapter(forMeAdapter);
                GridLayoutManager manager = new GridLayoutManager(getContext(),2);
                rcForme.setLayoutManager(manager);

                for (int i = 0; i < response.getResult().getCardlist().get(0).getData().size(); i++) {
                   listBanner.add(response.getResult().getCardlist().get(0).getData().get(i).getImageurl());

            }
                banner.setImages(listBanner);
                Picasso.with(getContext()).load(response.getResult().getCardlist().get(1).getData().get(0).getImageurl()).into(img_ad);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }

}
