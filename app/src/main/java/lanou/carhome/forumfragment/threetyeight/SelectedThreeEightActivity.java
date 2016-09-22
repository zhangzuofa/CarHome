package lanou.carhome.forumfragment.threetyeight;

import android.content.Intent;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/22.
 */
public class SelectedThreeEightActivity  extends BaseActivity{

    private ArrayList<String> list;
    private int position;
    private ListView lv;

    @Override
    protected int setLayout() {
        return R.layout.threeeight;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.three_Lv);



    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        position = intent.getIntExtra("哈哈",1000);
        Log.d("SelectedThreeEightActiv", "position:" + position);
        list = new ArrayList<>();
        list.add(URLValues.WIFE_MODEL_URL);
        list.add(URLValues.NOTORIOUS_URL);
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



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequesty);
    }
}
