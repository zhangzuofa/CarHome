package lanou.carhome.forumfragment.formed;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.forumfragment.hotforum.HotActivity;
import lanou.carhome.forumfragment.slectedrecommedacitivity.SelectedRecommedAcitivity;
import lanou.carhome.forumfragment.threetyeight.SelectedThreeEightActivity;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;


/**
 * Created by dllo on 16/9/19.
 */
public class ForumedFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshListView forumListV;
    private View viewTop;
    private Button btnWife;
    private Button btnBesuty;
    private Button btnTop;
    private Button btnMo;
    private Button btnChose;
    private Button btnSister;
    private Button btnRed;
    private Button btnChage;
    private ImageView imgViewTop;
    private ImageView imgViewTopJXImg;
    private int num;
    private FroumedAdapter adapter;


    @Override
    protected int setLayout() {
        return R.layout.forumedfrag;
    }

    @Override
    protected void initDate() {
        num = 0;
        innitInternet();

        ListView listViewTop = forumListV.getRefreshableView();
        listViewTop.addHeaderView(viewTop);
        innitRefresh();




    }

    private void innitRefresh() {

        forumListV.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {


                innitInternet();
                innitSetBtnBackground();


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                num = num + 1;
                String url ="http://223.99.255.20/clubnc.app.autohome.com.cn/club_v7.0.5/club/jingxuantopic.ashx?platud=2&categoryid=0&pageindex="+ num+"&pagesize=30&devicetype=android.1501_M02&deviceid=860954030358581&userid=0&operation=1&netstate=0&gps=38.889726%2C121.550943";
               // Log.d("试试", url);
             //   forumListV.onRefreshComplete();

                GsonRequest<ForumedBean> gsonRequest  = new GsonRequest<ForumedBean>(url, ForumedBean.class,
                        new Response.Listener<ForumedBean>() {
                            @Override
                            public void onResponse(ForumedBean response) {


                                adapter.setBean1(response);

                                forumListV.onRefreshComplete();



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

    private void innitSetBtnBackground() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(btnWife);
        buttons.add(btnBesuty);
        buttons.add(btnTop);
        buttons.add(btnMo);
        buttons.add(btnChose);
        buttons.add(btnSister);
        buttons.add(btnRed);
        buttons.add(btnChage);
        ArrayList<Integer>backGrounds =new ArrayList<>();
        backGrounds.add(R.drawable.btn_blu);
        backGrounds.add(R.drawable.btn_orange);
        ArrayList<Integer>intColor =new ArrayList<>();
        intColor.add(getResources().getColor(R.color.colorBlue));
        intColor.add(getResources().getColor(R.color.colorOrange));
        for (int i = 0; i < buttons.size(); i++) {
            int color =(int) ( Math.random()*2);
            buttons.get(i).setBackgroundResource(backGrounds.get(color));
            buttons.get(i).setTextColor(intColor.get(color));


        }




    }


    @Override
    protected void initView() {
        viewTop = LayoutInflater.from(getContext()).inflate(R.layout.forumed_viewtop,null);
        forumListV = bindView(R.id.Refresh_forumed_pullto);
        btnWife = bindView(R.id.btn_Wife,viewTop);
        btnBesuty = bindView(R.id.btn_Beauty,viewTop);
        btnTop = bindView(R.id.btn_Top,viewTop);
        btnMo = bindView(R.id.btn_MO,viewTop);
        btnChose = bindView(R.id.btn_chose,viewTop);
        btnSister = bindView(R.id.btn_SisterCar,viewTop);
        btnRed = bindView(R.id.btn_red,viewTop);
        btnChage = bindView(R.id.btn_chageCar,viewTop);
        imgViewTop = bindView(R.id.forumed_TopView_image_24Hour,viewTop);
        imgViewTop.setOnClickListener(this);
        imgViewTopJXImg = bindView(R.id.Forumed_topView_img_jingxuan,viewTop);
        imgViewTopJXImg.setOnClickListener(this);
        btnWife.setOnClickListener(this);
        btnBesuty.setOnClickListener(this);
        btnTop.setOnClickListener(this);
        btnMo.setOnClickListener(this);
        btnChose.setOnClickListener(this);
        btnSister.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnChage.setOnClickListener(this);
        forumListV.setMode(PullToRefreshBase.Mode.BOTH);








    }
    public void innitInternet(){
        GsonRequest<ForumedBean> gsonRequest  = new GsonRequest<ForumedBean>(URLValues.FORUMED_URL, ForumedBean.class,
                new Response.Listener<ForumedBean>() {
                    @Override
                    public void onResponse(ForumedBean response) {
                        forumListV.onRefreshComplete();
                        adapter = new FroumedAdapter(getContext());
                        adapter.setBean(response);
                        forumListV.setAdapter(adapter);
                        inintOnClickLisener(response);
                        Toast.makeText(getContext(), "刷新了30条", Toast.LENGTH_SHORT).show();





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);

    }

    private void inintOnClickLisener(final ForumedBean response) {

        forumListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentWeb = new Intent(getActivity(),ForumedWebVIewActivity.class);
                String str1 = "http://forum.app.autohome.com.cn/forum_v7.0.0/forum/club/topiccontent-a2-pm2-v7.1.0-t";
                String str2 = "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw360.json " ;
                String str = str1 + response.getResult().getList().get(position - 2).getTopicid() + str2;
                intentWeb.putExtra("论坛详情网址",str);

                startActivity(intentWeb);


            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forumed_TopView_image_24Hour:


                Intent intent = new Intent(getActivity(), HotActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
            case R.id.Forumed_topView_img_jingxuan:
              Intent intent1 = new Intent(getActivity(),SelectedRecommedAcitivity.class);
                startActivity(intent1);
                getActivity().overridePendingTransition(R.anim.anim_main_to_up,R.anim.anim_main_toleft);

                break;
            case R.id.btn_Wife:
                Intent intentWife = new Intent(getContext(),SelectedThreeEightActivity.class);

                intentWife.putExtra("哈哈",0);
                startActivity(intentWife);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
            case R.id.btn_Beauty:
                Intent intentBeauty = new Intent(getContext(),SelectedThreeEightActivity.class);

                intentBeauty.putExtra("哈哈",1);
                startActivity(intentBeauty);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
            case R.id.btn_Top:
                Intent intentTop  = new Intent(getContext(),SelectedThreeEightActivity.class);
                intentTop.putExtra("哈哈",6);
                startActivity(intentTop);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
            case R.id.btn_MO:
                Intent intentMo  = new Intent(getContext(),SelectedThreeEightActivity.class);
                intentMo.putExtra("哈哈",22);
                startActivity(intentMo);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
            case R.id.btn_chose:
                Intent intentChose  = new Intent(getContext(),SelectedThreeEightActivity.class);
                intentChose.putExtra("哈哈",4);
                startActivity(intentChose);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
            case R.id.btn_SisterCar:
                Intent intentSisterCar  = new Intent(getContext(),SelectedThreeEightActivity.class);
                intentSisterCar.putExtra("哈哈",13);
                startActivity(intentSisterCar);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
            case R.id.btn_red:
                Intent intentRed  = new Intent(getContext(),SelectedThreeEightActivity.class);
                intentRed.putExtra("哈哈",2);
                startActivity(intentRed);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);

                break;
            case R.id.btn_chageCar:
                Intent intentChageCar  = new Intent(getContext(),SelectedThreeEightActivity.class);
                intentChageCar.putExtra("哈哈",17);
                startActivity(intentChageCar);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;



        }

    }
}
