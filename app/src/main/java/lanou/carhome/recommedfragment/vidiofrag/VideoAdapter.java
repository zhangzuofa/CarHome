package lanou.carhome.recommedfragment.vidiofrag;

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
 * Created by dllo on 16/10/8.
 */
public class VideoAdapter extends BaseAdapter {
    Context mContext;
    VideoBean bean;

    public void setBean(VideoBean bean) {
        this.bean = bean;
    }

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titleTv.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.timeTv.setText(bean.getResult().getList().get(position).getTime());
        viewHolder.commentTv.setText(""+ bean.getResult().getList().get(position).getReplycount() + "评论");
        viewHolder.playTv.setText("" + bean.getResult().getList().get(position).getPlaycount() + "播放");
        Picasso.with(mContext).load(bean.getResult().getList().get(position).getSmallimg()).into(viewHolder.img);
        return convertView;
    }
    class ViewHolder{
        TextView titleTv;
        TextView playTv;
        TextView commentTv;
        TextView timeTv;
        ImageView img;


        public ViewHolder(View view){
            titleTv = (TextView) view.findViewById(R.id.item_video_title_tv);
            playTv = (TextView) view.findViewById(R.id.item_video_play_tv);
            commentTv = (TextView) view.findViewById(R.id.item_video_comment_tv);
            timeTv = (TextView) view.findViewById(R.id.item_video_time_tv);
            img = (ImageView) view.findViewById(R.id.item_video_img);

        }
    }
}
