package lanou.carhome.forumfragment.formed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.carhome.R;

/**
 * Created by dllo on 16/9/21.
 */
public class FroumedAdapter extends BaseAdapter {
    Context mContext;
    ForumedBean bean;

    public void setBean(ForumedBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public FroumedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean == null ? 0: bean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.forumedfragment,null);
           viewHolder = new ViewHolder(convertView);
           convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.tvForumed.setText(bean.getResult().getList().get(position).getBbsname());
        viewHolder.tvNum.setText("" + bean.getResult().getList().get(position).getReplycounts() + "跟帖");
        Picasso.with(mContext).load(bean.getResult().getList().get(position).getSmallpic()).into(viewHolder.img);


        return convertView;
    }
   class ViewHolder{

       private TextView tvTitle;
       private TextView tvForumed;
       private TextView tvNum;
       private ImageView img;

       public ViewHolder(View view){
           tvTitle = (TextView) view.findViewById(R.id.forumedfragment_Tv_title);
           tvForumed = (TextView) view.findViewById(R.id.forumedfragment_Tv_forumed);
           tvNum = (TextView) view.findViewById(R.id.forumFragment_Tv_num);
           img = (ImageView) view.findViewById(R.id.forumedfragment_Img);



       }
   }
}
