package lanou.carhome.baseclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/9/19.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(setLayout());
        initView();
        initDate();
    }

    protected abstract int setLayout();
    protected abstract void initView();
    protected abstract void initDate();

    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }
    protected <T extends View> T bindView(int id,View v){
        return (T)v.findViewById(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
