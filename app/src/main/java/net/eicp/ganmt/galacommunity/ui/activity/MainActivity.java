package net.eicp.ganmt.galacommunity.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;

import net.eicp.ganmt.galacommunity.R;
import net.eicp.ganmt.galacommunity.ui.fragment.BaseTabFragment;
import net.eicp.ganmt.galacommunity.ui.fragment.MainMyFragment;
import net.eicp.ganmt.galacommunity.ui.fragment.MainNeighborFragment;
import net.eicp.ganmt.galacommunity.ui.fragment.MainServiceFragment;
import net.eicp.ganmt.galacommunity.ui.fragment.MainSquareFragment;
import net.eicp.ganmt.galacommunity.ui.view.ChangeColorIconWithTextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseFragmentActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    private String[] mTitles = new String[] { "First Fragment!",
            "Second Fragment!", "Third Fragment!", "Fourth Fragment!" };

    private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOverflowShowingAlways();
        //getActionBar().setDisplayShowHomeEnabled(false);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        initDatas();

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initDatas()
    {

        MainSquareFragment mainSquareFragment = new MainSquareFragment();
        Bundle args = new Bundle();
        args.putString("title", mTitles[0]);
        mainSquareFragment.setArguments(args);
        mTabs.add(mainSquareFragment);

        MainMyFragment mainMyFragment = new MainMyFragment();
        Bundle args1 = new Bundle();
        args1.putString("title", mTitles[1]);
        mainMyFragment.setArguments(args1);
        mTabs.add(mainMyFragment);

        MainNeighborFragment mainNeighborFragment = new MainNeighborFragment();
        Bundle args2 = new Bundle();
        args2.putString("title", mTitles[2]);
        mainNeighborFragment.setArguments(args2);
        mTabs.add(mainNeighborFragment);

        MainServiceFragment mainServiceFragment = new MainServiceFragment();
        Bundle args3 = new Bundle();
        args3.putString("title", mTitles[3]);
        mainServiceFragment.setArguments(args3);
        mTabs.add(mainServiceFragment);

//        for (String title : mTitles)
//        {
//            BaseTabFragment baseTabFragment = new BaseTabFragment();
//            Bundle args = new Bundle();
//            args.putString("title", title);
//            baseTabFragment.setArguments(args);
//            mTabs.add(baseTabFragment);
//        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {

            @Override
            public int getCount()
            {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return mTabs.get(arg0);
            }
        };

        initTabIndicator();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initTabIndicator()
    {
        ChangeColorIconWithTextView one = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_one);
        ChangeColorIconWithTextView two = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_two);
        ChangeColorIconWithTextView three = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_three);
        ChangeColorIconWithTextView four = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_four);

        mTabIndicator.add(one);
        mTabIndicator.add(two);
        mTabIndicator.add(three);
        mTabIndicator.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0f);
    }
    @Override
    public void onClick(View view) {
        resetOtherTabs();

        switch (view.getId())
        {
            case R.id.id_indicator_one:
                mTabIndicator.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicator.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicator.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                mTabIndicator.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0)
        {
            ChangeColorIconWithTextView left = mTabIndicator.get(position);
            ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    /**
     * 重置其他的Tab
     */
    private void resetOtherTabs()
    {
        for (int i = 0; i < mTabIndicator.size(); i++)
        {
            mTabIndicator.get(i).setIconAlpha(0);
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu)
    {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
        {
            if (menu.getClass().getSimpleName().equals("MenuBuilder"))
            {
                try
                {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e)
                {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void setOverflowShowingAlways()
    {
        try
        {
            // true if a permanent menu key is present, false otherwise.
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
