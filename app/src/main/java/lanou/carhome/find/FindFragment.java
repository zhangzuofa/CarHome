package lanou.carhome.find;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.find.everyoneadpter.BusinessAdapter;
import lanou.carhome.find.everyoneadpter.ForMeAdapter;
import lanou.carhome.find.everyoneadpter.MyLoveAdapter;
import lanou.carhome.find.everyoneadpter.ServeAdapter;
import lanou.carhome.find.everyoneadpter.TimeLimitAdapter;
import lanou.carhome.find.timebuy.LimitedBuyTextView;
import lanou.carhome.forumfragment.formed.ForumedWebVIewActivity;
import lanou.carhome.main.DividerItemDecoration;
import lanou.carhome.recommedfragment.SearchKeyActivity;
import lanou.carhome.recommedfragment.smallrecommedfrag.OnClickLisenerRecycleView;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/19.
 */
public class FindFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshListView listView;
    private View viewFindTop;
    private Banner banner;
    private View viewGoodsList;
    private RecyclerView rcForme;
    private View viewForMe;
    private ImageView img_ad;
    private RecyclerView yewuRcyclerView;
    private RecyclerView serveRecycleView;
    private ImageView imgFindTop;
    private ArrayList<String> listBanner;
    private RecyclerView timeLimitRecycle;
    private RecyclerView recycleViewMylove;
    private ImageView imgDaiKuan;
    private RecyclerView recyclerViewSports;
    private ImageView imgOneMoreSports;
    private ImageView imgTwoMoreSports;
    private ImageView imgThreeMoreSports;
    private ImageView searchImg;

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    private RelativeLayout timeBuyRl;


    @Override
    protected int setLayout() {
        return R.layout.findfragment;
    }

    @Override
    protected void initDate() {

        innitRequestInternet();
        rcForme.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rcForme.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST));
        recycleViewMylove.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        recycleViewMylove.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST));
        serveRecycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST));
        serveRecycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        timeLimitRecycle.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST));
        ListView listViewT = listView.getRefreshableView();
        //最上全部头布局
        listViewT.addHeaderView(viewFindTop);
        //为你推荐和
        listViewT.addHeaderView(viewForMe);
        //商品列表
        listViewT.addHeaderView(viewGoodsList);
        innitPullToRefresh();
        //监控滑动监听
    //    innitTouchListener();


    }

//    private void innitTouchListener() {
////        * Fragment中，注册
////                * 接收MainActivity的Touch回调的对象
////                * 重写其中的onTouchEvent函数，并进行该Fragment的逻辑处理
////                */
//        MainActivity.MyTouchListener myTouchListener = new MainActivity.MyTouchListener() {
//            @Override
//            public void onTouchEvent(MotionEvent event) {
//                // 处理手势事件
//                if(event.getAction() == MotionEvent.ACTION_DOWN) {
//                    //当手指按下的时候
//                    x1 = event.getX();
//                    y1 = event.getY();
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP) {
//                    //当手指离开的时候
//                    x2 = event.getX();
//                    y2 = event.getY();
//                    if(y1 - y2 > 50) {
//                      //  Toast.makeText(getContext(), "向上滑", Toast.LENGTH_SHORT).show();
//                    } else if(y2 - y1 > 1500) {
//                     Toast.makeText(getContext(), "向下滑", Toast.LENGTH_SHORT).show();
//
//                    } else if(x1 - x2 > 50) {
//                        Toast.makeText(getContext(), "向左滑", Toast.LENGTH_SHORT).show();
//                    } else if(x2 - x1 > 50) {
//                        Toast.makeText(getContext(), "向右滑", Toast.LENGTH_SHORT).show();
//                    }
//                }
//              //  return super.onTouchEvent(event);
//            }
//
//
//  //      }
//      };
//
//        // 将myTouchListener注册到分发列表
//        ((MainActivity)this.getActivity()).registerMyTouchListener(myTouchListener);
//    }

    private void innitPullToRefresh() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initListView();


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    private void initListView() {
        GsonRequest<FindBean> gsonRequest = new GsonRequest<FindBean>(URLValues.FIND_URL, FindBean.class, new Response.Listener<FindBean>() {
            @Override
            public void onResponse(FindBean response) {
                listView.onRefreshComplete();
                FindFragmentAdapter adapter = new FindFragmentAdapter(getContext());
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
    protected void initView() {
        listView = bindView(R.id.RefreshListview_Find);
        viewFindTop = LayoutInflater.from(getContext()).inflate(R.layout.findtop_findfragment, null);
        viewForMe = LayoutInflater.from(getContext()).inflate(R.layout.viewforme, null);
        img_ad = bindView(R.id.findtop_findfragment_img_Ad, viewFindTop);
        imgFindTop = bindView(R.id.findtop_findfragment_img_findTop, viewFindTop);
        banner = bindView(R.id.banner_findFragmentTopView, viewFindTop);
        yewuRcyclerView = bindView(R.id.findFragment_topView_rcyelerView_yewu, viewFindTop);
        serveRecycleView = bindView(R.id.findFragment_topView_recyclerView_serve, viewFindTop);
        timeLimitRecycle = bindView(R.id.findFragment_topView_rcyelerView_timeLimit, viewFindTop);
        timeBuyRl = bindView(R.id.time_buy_rl,viewFindTop);

        viewGoodsList = LayoutInflater.from(getContext()).inflate(R.layout.viewgoodslist, null);
        //    为我推荐 和喜欢还有
        rcForme = bindView(R.id.forme_RecycleV, viewForMe);
        recycleViewMylove = bindView(R.id.foryou_recycle, viewForMe);
        imgDaiKuan = bindView(R.id.forme_daikuan_img, viewForMe);

        imgOneMoreSports = bindView(R.id.forme_moreSports_img_one, viewForMe);
        imgTwoMoreSports = bindView(R.id.forme_moreSports_img_two, viewForMe);
        imgThreeMoreSports = bindView(R.id.forme_moreSports_img_three, viewForMe);
        searchImg = bindView(R.id.fin_fragment_search_img);
        searchImg.setOnClickListener(this);


    }

    private void innitRequestInternet() {
        listBanner = new ArrayList<>();
        GsonRequest<FindBean> gsonRequest = new GsonRequest<FindBean>(URLValues.FIND_URL, FindBean.class, new Response.Listener<FindBean>() {
            @Override
            public void onResponse(FindBean response) {
                initSendAdapter(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }

    private void initSendAdapter(FindBean response) {
        FindFragmentAdapter adapter = new FindFragmentAdapter(getContext());
        adapter.setBean(response);
        listView.setAdapter(adapter);
        // 为你推荐
        ForMeAdapter forMeAdapter = new ForMeAdapter(getContext());
        forMeAdapter.setBean(response);
        rcForme.setAdapter(forMeAdapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        rcForme.setLayoutManager(manager);
        // 轮播图

        for (int i = 0; i < response.getResult().getCardlist().get(0).getData().size(); i++) {
            listBanner.add(response.getResult().getCardlist().get(0).getData().get(i).getImageurl());

        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImages(listBanner);

        //广告判断 有用
        //   Picasso.with(getContext()).load(response.getResult().getCardlist().get(4).getData().get(0).getImageurl()).into(img_ad);
        // 业务

        BusinessAdapter businessAdapter = new BusinessAdapter(getContext());
        businessAdapter.setBean(response);
        yewuRcyclerView.setAdapter(businessAdapter);
        GridLayoutManager manegerBusiness = new GridLayoutManager(getContext(), 5);
        yewuRcyclerView.setLayoutManager(manegerBusiness);
        initOnClickBusiness(businessAdapter, response);

        // 发现头条
        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {
            if (response.getResult().getCardlist().get(i).getDescription().equals("头条")) ;
            Picasso.with(getContext()).load(response.getResult().getCardlist().get(i).getImageurl()).into(imgFindTop);
        }


        // 服务
        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {
            if (response.getResult().getCardlist().get(i).getDescription().equals("田字小号专区")) {
                ServeAdapter serveAdapter = new ServeAdapter(getContext());
                serveAdapter.setNum(i);
                serveAdapter.setBean(response);
                serveRecycleView.setAdapter(serveAdapter);
                GridLayoutManager serveManager = new GridLayoutManager(getContext(), 2);
                serveRecycleView.setLayoutManager(serveManager);

            }

        }

        //限时抢购
        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {
            if (response.getResult().getCardlist().get(i).getDescription().equals("限时抢购")) {


                TimeLimitAdapter timeLimitAdapter = new TimeLimitAdapter(getContext());
                timeLimitAdapter.setBean(response);
                timeLimitAdapter.setNum(i);

                timeLimitRecycle.setAdapter(timeLimitAdapter);
                LinearLayoutManager timeLimitManager = new LinearLayoutManager(getContext());
                timeLimitManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                timeLimitRecycle.setLayoutManager(timeLimitManager);
                LimitedBuyTextView limited = new LimitedBuyTextView(getContext(), response.getResult().getCardlist().get(i)
                        .getRightbtn().getData());
                //Log.d("噶", ""+response.getResult().getCardlist().get(i)
//                        .getRightbtn().getData());

              try {
                  timeBuyRl.addView(limited.initTime());
              }catch (NullPointerException e){

              }

            }

        }

        //猜我喜欢
        MyLoveAdapter myLoveAdapter = new MyLoveAdapter(getContext());
        myLoveAdapter.setBean(response);
        recycleViewMylove.setAdapter(myLoveAdapter);
        GridLayoutManager myLoveManager = new GridLayoutManager(getContext(), 2);
        recycleViewMylove.setLayoutManager(myLoveManager);
        //贷款
        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {
            if (response.getResult().getCardlist().get(i).getDescription().equals("单帧小号横栏")) {

                Picasso.with(getContext()).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(imgDaiKuan);

            }
        }


        //更多活动
        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {
            if (response.getResult().getCardlist().get(i).getDescription().equals("活动专区")) {
                Picasso.with(getContext()).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(imgOneMoreSports);
                Picasso.with(getContext()).load(response.getResult().getCardlist().get(i).getData().get(1).getImageurl()).into(imgTwoMoreSports);
                Picasso.with(getContext()).load(response.getResult().getCardlist().get(i).getData().get(2).getImageurl()).into(imgThreeMoreSports);

            }
        }


    }

    private void initOnClickBusiness(BusinessAdapter businessAdapter, FindBean response) {
        businessAdapter.setOnClickLisenerRecycleView(new OnClickLisenerRecycleView() {
            @Override
            public void onClick(int position, RecyclerView.ViewHolder holder) {
                switch (position) {
                    case 0:

                        Toast.makeText(getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intentWeb = new Intent(getActivity(), ForumedWebVIewActivity.class);
                        intentWeb.putExtra("论坛详情网址", URLValues.DISCOVER_CAR_MALL);
                        //   Toast.makeText(getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
                        startActivity(intentWeb);
                        break;
                    case 2:
                        Intent intentWebFenqi = new Intent(getActivity(), ForumedWebVIewActivity.class);
                        intentWebFenqi.putExtra("论坛详情网址", URLValues.DISCOVER_HIRE_CAR);
                        //   Toast.makeText(getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
                        startActivity(intentWebFenqi);

                        break;
                    case 3:
                        Intent intentKeepCar = new Intent(getContext(), ForumedWebVIewActivity.class);
                        intentKeepCar.putExtra("论坛详情网址", URLValues.DISCOVER_SUBSIDY_HOME);
                        startActivity(intentKeepCar);
                        break;
                    case 4:
                        Intent intentTwoCar = new Intent(getContext(), ForumedWebVIewActivity.class);
                        intentTwoCar.putExtra("论坛详情网址", URLValues.DISCOVER_FIND_CAR);
                        startActivity(intentTwoCar);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        Intent intentGroupCar = new Intent(getContext(), ForumedWebVIewActivity.class);
                        intentGroupCar.putExtra("论坛详情网址", URLValues.DISCOVER_GROUP_BUY);
                        startActivity(intentGroupCar);
                        break;
                    case 8:
                        Intent intentLoveCar = new Intent(getContext(), ForumedWebVIewActivity.class);
                        intentLoveCar.putExtra("论坛详情网址", URLValues.DISCOVER_CAR_VALUATION);
                        startActivity(intentLoveCar);
                        break;
                    case 9:
                        break;


                }

            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fin_fragment_search_img:
                Intent intent = new Intent(getContext(), SearchKeyActivity.class);
                startActivity(intent);
                break;
        }
    }


}
