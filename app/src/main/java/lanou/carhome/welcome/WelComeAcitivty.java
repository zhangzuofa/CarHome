package lanou.carhome.welcome;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.main.MainActivity;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/10/13.
 */
public class WelComeAcitivty extends BaseActivity implements View.OnClickListener {

    private ImageView img;
    private RelativeLayout rl;
  Timer timer = new Timer();
    int time = 1;
    @Override
    protected int setLayout() {
        return R.layout.welcomeactivity;
    }

    @Override
    protected void initView() {
        img = bindView(R.id.welcome_img);
        rl = bindView(R.id.welcome_relaLayout);
        rl.setOnClickListener(this);


    }

    @Override
    protected void initDate() {
        ininitRequestInternet();

        inittime();


    }

    private void inittime() {
        TimerTask task  = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        if (time < 0) {
                            timer.cancel();
                            Intent intent = new Intent(WelComeAcitivty.this, MainActivity.class);
                            startActivity(intent);
                            WelComeAcitivty.this.finish();


                        }
                    }
                });

            }
        };
        timer.schedule(task,1000,1000);
    }

    private void ininitRequestInternet() {
        GsonRequest<WelecomeBean>gsonRequest = new GsonRequest<WelecomeBean>(URLValues.URL_WELCOME, WelecomeBean.class, new Response.Listener<WelecomeBean>() {
            @Override
            public void onResponse(WelecomeBean response) {
                  if (response.getResult().getIshavead() !=0){
                      Picasso.with(WelComeAcitivty.this).load(response.getResult().getAd().getImgad().getImgurl()).into(img);
                      rl.setVisibility(View.VISIBLE);
                  }








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
            case R.id.welcome_relaLayout:
                Intent intent = new Intent(WelComeAcitivty.this, MainActivity.class);
                startActivity(intent);
                timer.cancel();
                this.finish();

                break;
        }

    }
}
