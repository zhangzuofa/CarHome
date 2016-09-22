package lanou.carhome.forumfragment.formed;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
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


    @Override
    protected int setLayout() {
        return R.layout.forumedfrag;
    }

    @Override
    protected void initDate() {
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
                Toast.makeText(getContext(), "刷新了30条", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    private void innitSetBtnBackground() {
        ArrayList<Button>buttons = new ArrayList<>();
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





    }
    public void innitInternet(){
        GsonRequest<ForumedBean> gsonRequest  = new GsonRequest<ForumedBean>(URLValues.FORUMED_URL, ForumedBean.class,
                new Response.Listener<ForumedBean>() {
                    @Override
                    public void onResponse(ForumedBean response) {
                        forumListV.onRefreshComplete();
                        FroumedAdapter adapter = new FroumedAdapter(getContext());
                        adapter.setBean(response);
                        forumListV.setAdapter(adapter);




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
            case R.id.forumed_TopView_image_24Hour:


                Intent intent = new Intent(getActivity(), HotActivity.class);
                startActivity(intent);
                break;
            case R.id.Forumed_topView_img_jingxuan:
              Intent intent1 = new Intent(getActivity(),SelectedRecommedAcitivity.class);
                startActivity(intent1);


                break;


        }

    }
}
