package lanou.carhome.recommedfragment;

import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.widget.TextView;

import java.io.File;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/10/13.
 */
public class MusicActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected int setLayout() {
        return R.layout.musicactivity;
    }

    @Override
    protected void initView() {
        tv = bindView(R.id.music_activity_tv);

    }

    @Override
    protected void initDate() {
        //获取手机内存
        File dataFileDir = Environment.getDataDirectory();
        String dataMemory = this.getMemoryInfo(dataFileDir);

        //获取sd卡
        File sdcardFileDir = Environment.getExternalStorageDirectory();
        String sdcardMemory = getMemoryInfo(sdcardFileDir);

        tv.setText("手机内存:" + "\n" + dataMemory +"\n" + "SD卡:" + "\n" + sdcardMemory);


    }
    private String getMemoryInfo(File path) {
        // 获得一个磁盘状态对象
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSize();   // 获得一个扇区的大小

        long totalBlocks = stat.getBlockCount();    // 获得扇区的总数

        long availableBlocks = stat.getAvailableBlocks();   // 获得可用的扇区数量

        // 总空间
        String totalMemory =  Formatter.formatFileSize(this, totalBlocks * blockSize);
        // 可用空间
        String availableMemory = Formatter.formatFileSize(this, availableBlocks * blockSize);

        return "总空间: " + totalMemory + "\n可用空间: " + availableMemory;
    }
}

