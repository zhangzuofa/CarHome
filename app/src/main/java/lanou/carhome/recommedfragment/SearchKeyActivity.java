package lanou.carhome.recommedfragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/9/30.
 */
public class SearchKeyActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img;
    private EditText ed;

    @Override
    protected int setLayout() {
        return R.layout.searchkey_acitivity;
    }

    @Override
    protected void initView() {
        ed = bindView(R.id.recommed_search_activity_ed);
        img = bindView(R.id.recommed_search_activity_img);
        img.setOnClickListener(this);
       ed.setOnClickListener(this);

    }

    @Override
    protected void initDate() {



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recommed_search_activity_img:
                if (ed.getText()!= null){
                    ed.setText("");
                    img.setVisibility(ImageView.INVISIBLE);
                }

                break;
            case R.id.recommed_search_activity_ed:

                break;
        }
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (ed.getText()==null){
//            img.setVisibility(ImageView.INVISIBLE);
//        } else {
//            img.setVisibility(ImageView.VISIBLE);
//        }
//
//    }
}
