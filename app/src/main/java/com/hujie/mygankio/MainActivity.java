package com.hujie.mygankio;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.hujie.mygankio.adapter.MyPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_a);
        BottomNavigationBar navigationBar = (BottomNavigationBar) findViewById(R.id.navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置抽屉开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //设置TabLayout
        String[] titles={"ALL","休息视频","ANDROID","IOS","拓展资源","前端","瞎推荐"};
        ArrayList<Fragment> fragments=new ArrayList<>();

        for (int i=0;i<7;i++){
            fragments.add(ContentFragment.getInsatance(i));
        }

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setTabTextColors(Color.GRAY,Color.BLACK);

        tabLayout.setSelectedTabIndicatorColor(Color.CYAN);

        viewPager.setOffscreenPageLimit(fragments.size());

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),this,fragments,titles));

        tabLayout.setupWithViewPager(viewPager);
        //添加菜单项
        navigationBar.addItem(new BottomNavigationItem(R.drawable.toolbar_defaultwindow,"最新")).
                addItem(new BottomNavigationItem(R.drawable.toolbar_edit_selectnone,"分类浏览")).
                addItem(new BottomNavigationItem(R.drawable.toolbar_edit_copy,"妹纸")).
                addItem(new BottomNavigationItem(R.drawable.toolbar_edit_openas,"闲读")).
                addItem(new BottomNavigationItem(R.drawable.toolbar_moveout,"我的收藏")).
                initialise();
        //设置切换模式
        navigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        //随页面滚动自动显示隐藏
        navigationBar.setAutoHideEnabled(true);
        //设置颜色
        navigationBar.setInActiveColor("#333333").
                        setActiveColor("#bbbbbb").
                        setBarBackgroundColor("#bbbbbb");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
