package net.eicp.ganmt.galacommunity.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午2:03
 * Description: FragmentActivity 基类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseFragmentActivity extends FragmentActivity {
    protected  String TAG  = getClass().getSimpleName();
    //protected ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mImageLoader = ImageLoader.getInstance();
    }

    /**
     * Activity跳转
     * @param context
     * @param targetActivity
     * @param bundle
     */
    public void redictToActivity(Context context,Class<?> targetActivity,Bundle bundle){
        Intent intent = new Intent(context, targetActivity);
        if(null != bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
