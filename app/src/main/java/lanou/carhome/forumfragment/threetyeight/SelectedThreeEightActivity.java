package lanou.carhome.forumfragment.threetyeight;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/22.
 */
public class SelectedThreeEightActivity  extends BaseActivity implements View.OnClickListener {

    private ArrayList<String> list;
    private int position;
    private PullToRefreshListView lv;
    private ImageView img;

    @Override
    protected int setLayout() {
        return R.layout.threeeight;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.selectRecomThreeEight_refresh);
        img = (ImageView) findViewById(R.id.selectedThEightActivity_img);
        img.setOnClickListener(this);



    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        position = intent.getIntExtra("哈哈",1000);
        Log.d("SelectedThreeEightActiv", "position:" + position);

        list = new ArrayList<>();
        //1
        list.add(URLValues.WIFE_MODEL_URL);
        list.add(URLValues.NOTORIOUS_URL);
        list.add(URLValues.HOF_URL);
        list.add(URLValues.LECTURER_URL);
        list.add(URLValues.AUSLESE_URL);
        list.add(URLValues.DISCOURSE_URL);
        list.add(URLValues.HIGH_POINT_URL);
        list.add(URLValues.ELECTRIC_VEHICLE_URL);
        list.add(URLValues.BUY_CAR_URL);
        list.add(URLValues.DRIVING_CRITIQUE_URL);
        //11
        list.add(URLValues.DRIVING_MEMBER_URL);
        list.add(URLValues.OVERSEAS_BUYERSL_URL);
        list.add(URLValues.CLASSIC_CAR_URL);
        list.add(URLValues.SISTER_CAR_URL);
        list.add(URLValues.PREFERENTIAL_CAR_URL);
        list.add(URLValues.ORIGINAL_LARGE_URL);
        list.add(URLValues.TOP_ELEGANT_URL);
        list.add(URLValues.MODIFIED_RATIONAL_URL);
        list.add(URLValues.WAY_MOTORING_URL);
        list.add(URLValues.FIRST_CAMP_URL );
        //21
        list.add(URLValues.NEW_LIVE_URL);
        list.add(URLValues.HISTORICAL_TOPIC_URL);
        list.add(URLValues.FRIEND_HEAVEN_EARTH_URL);
        list.add(URLValues.HONEYMOON_URL);
        list.add(URLValues.SWEET_WEDDING_URL);
        list.add(URLValues.PHOTOGRAPHY_CLASS_URL);
        list.add(URLValues.CAR_PARTY_URL );
        list.add(URLValues.BIKE_TRIBE_URL);
        list.add(URLValues.GOSSIP_CLUB_URL );
        list.add(URLValues. NORCO_TRAVELS_URL);
        //31
        list.add(URLValues.SOUTHWEST_TRAVELS_URL);
        list.add(URLValues.NORTHEAST_TRAVELS_URL);
        list.add(URLValues.NORTHWEST_TRAVELS_URL);
        list.add(URLValues.SKY_TRAVELS_URL );
        list.add(URLValues.SOUTH_TRAVELS_URL );
        list.add(URLValues.HYZ_TRAVELS_URL );
        list.add(URLValues.MACAO_TRAVELS_URL );
        list.add(URLValues.OVERSEAS_TRAVELS_URL );
        list.add(URLValues.SEA_PEARL_URL );

        innitRequestInternet();

    }

    private void innitRequestInternet() {
        GsonRequest<ThreeEightBean> gsonRequesty = new GsonRequest<ThreeEightBean>(list.get(position), ThreeEightBean.class,
                new Response.Listener<ThreeEightBean>() {
                    @Override
                    public void onResponse(ThreeEightBean response) {
                        ThreeAdapter adapter = new ThreeAdapter(SelectedThreeEightActivity.this);
                        adapter.setBean(response);
                        lv.setAdapter(adapter);
                        initOnClickLissenner(response);




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequesty);
    }

    private void initOnClickLissenner(final ThreeEightBean response) {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectedThreeEightActivity.this,ThirtyNineWebViewActivity.class);
                String str1 ="http://forum.app.autohome.com.cn/forum_v7.0.0/forum/club/topiccontent-a2-pm2-v7.1.0-t";
                String str2 = "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw360.json";
                int num = response.getResult().getList().get(position).getTopicid();
                String url  = str1 + num +str2;
                intent.putExtra("39贴详情",url);

                startActivity(intent);


            }
        });



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selectedThEightActivity_img:
               super.onBackPressed();
                break;
        }
    }
}
