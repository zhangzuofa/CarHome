package lanou.carhome.recommedfragment;

import android.view.View;
import android.widget.Button;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/10/9.
 */
public class MoreActivity extends BaseActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected int setLayout() {
        return R.layout.moreactvity;
    }

    @Override
    protected void initView() {
        button = bindView(R.id.shi_btn);
        button.setOnClickListener(this);

    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shi_btn:
                this.finish();
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction transaction =manager.beginTransaction();
//                transaction.replace(R.id.main_framelayout,new RecommedFragment());
//                transaction.commit();

                break;
        }

    }
}
