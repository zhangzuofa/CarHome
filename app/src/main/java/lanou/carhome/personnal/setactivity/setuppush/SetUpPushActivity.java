package lanou.carhome.personnal.setactivity.setuppush;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;

import cn.jpush.android.api.JPushInterface;
import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/10/14.
 */
public class SetUpPushActivity extends BaseActivity implements View.OnClickListener {

    private Switch systemSwitch;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    @Override
    protected int setLayout() {
        return R.layout.setuppushactivity;
    }

    @Override
    protected void initView() {
        ImageView returnImg = bindView(R.id.setup_puch_img);
        returnImg.setOnClickListener(this);
        systemSwitch = bindView(R.id.setup_push_swich);



    }

    @Override
    protected void initDate() {
        initSystemSwitch();

    }

    private void initSystemSwitch() {


        sp = getSharedPreferences("setPush", MODE_PRIVATE);
        ed = sp.edit();
        ed.commit();

        systemSwitch.setChecked(sp.getBoolean("systemSwitch", true));
//        ed.putBoolean("systemSwitch",true);
//        sp.getBoolean("systemSwitch",false);
        systemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    ed.putBoolean("systemSwitch", isChecked);
                    ed.commit();
                if (isChecked){
                    JPushInterface.onResume(getApplicationContext());
                }else {
                    JPushInterface.stopPush(getApplicationContext());
                }







            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setup_puch_img:
                this.finish();
                break;
            case R.id.setup_push_gettime_ll:
                PopupWindow
                break;
        }


    }
}
