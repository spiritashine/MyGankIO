package com.hujie.mygankio.base;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.hujie.mygankio.R;
import com.hujie.mygankio.classify.ClassifyFragment;
import com.hujie.mygankio.latest.RecommendFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragmemt_container)
    LinearLayout fragmemtContainer;
    @BindView(R.id.navigation)
    BottomNavigationBar navigation;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ClassifyFragment classifyFragment;
    private FuliFragment fuliFragment;
    private RecommendFragment recommendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        //设置抽屉开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //添加菜单项
        navigation.addItem(new BottomNavigationItem(R.drawable.toolbar_defaultwindow, "最新")).
                addItem(new BottomNavigationItem(R.drawable.toolbar_edit_selectnone, "分类浏览")).
                addItem(new BottomNavigationItem(R.drawable.toolbar_edit_copy, "妹纸")).
                addItem(new BottomNavigationItem(R.drawable.toolbar_edit_openas, "闲读")).
                addItem(new BottomNavigationItem(R.drawable.toolbar_moveout, "我的收藏")).
                initialise();
        //设置切换模式
        navigation.setMode(BottomNavigationBar.MODE_SHIFTING);
        //随页面滚动自动显示隐藏
        navigation.setAutoHideEnabled(true);
        //设置颜色
        navigation.setInActiveColor("#333333").
                setActiveColor("#bbbbbb").
                setBarBackgroundColor("#bbbbbb");
        recommendFragment = new RecommendFragment();
        classifyFragment = new ClassifyFragment();
        fuliFragment = new FuliFragment();
        FragmentTransaction ft=  getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmemt_container, recommendFragment,"0");
        ft.add(R.id.fragmemt_container, classifyFragment,"1");
        ft.add(R.id.fragmemt_container, fuliFragment,"2");
        ft.commit();
        navigation.selectTab(0);
        navigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
            FragmentTransaction transaction=  getSupportFragmentManager().beginTransaction();
                switch (position){
                    case 0:
                        transaction.show(recommendFragment);
                        break;
                    case 1:
                        transaction.show(classifyFragment);
                        break;
                    case 2:
                        transaction.show(fuliFragment);
                        break;
                }
                transaction.commit();

            }

            @Override
            public void onTabUnselected(int position) {
                FragmentTransaction transaction=  getSupportFragmentManager().beginTransaction();
                switch (position){
                    case 0:
                        transaction.hide(recommendFragment);
                        break;
                    case 1:
                        transaction.hide(classifyFragment);
                        break;
                    case 2:
                        transaction.hide(fuliFragment);
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabReselected(int position) {
//                FragmentTransaction transaction=  getSupportFragmentManager().beginTransaction();
//                switch (position){
//                    case 1:
//                        transaction.show(classifyFragment);
//                        break;
//                    case 2:
//                        transaction.show(fuliFragment);
//                        break;
//                }
//                transaction.commit();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        //添加搜索监听
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO: 2017/1/14
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO: 2017/1/14
        return false;
    }
}
