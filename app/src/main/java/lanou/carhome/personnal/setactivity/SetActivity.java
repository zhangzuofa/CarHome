package lanou.carhome.personnal.setactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.main.DataCleanManager;
import lanou.carhome.personnal.setactivity.setuppush.SetUpPushActivity;

/**
 * Created by dllo on 16/10/12.
 */
public class SetActivity  extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll;
    private TextView numTv;

    @Override
    protected int setLayout() {
        return R.layout.setactivity;
    }

    @Override
    protected void initView() {
        ll = bindView(R.id.setactivity_delete_ll);
        ll.setOnClickListener(this);
        numTv = bindView(R.id.setactivity_tv_num);
        LinearLayout setUpLl  = bindView(R.id.ll_set_up_push);
        setUpLl.setOnClickListener(this);
        ImageView returnImg = bindView(R.id.setActivity_img);
        returnImg.setOnClickListener(this);



    }

    @Override
    protected void initDate() {
        try {
         String file = DataCleanManager.getTotalCacheSize(this);
         numTv.setText(file);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setactivity_delete_ll:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确定删除缓存");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //清除
                        DataCleanManager.clearAllCache(SetActivity.this);
                        try {
                            //获取大小
                            String file = DataCleanManager.getTotalCacheSize(SetActivity.this);
                            numTv.setText(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;

            case R.id.ll_set_up_push:
                Intent setUpPushIntent = new Intent(this,SetUpPushActivity.class);
                startActivity(setUpPushIntent);

                break;
            case R.id.setActivity_img:
                this.finish();
                break;

        }

    }
}
