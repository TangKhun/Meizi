package com.nevil.meizi.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nevil.meizi.R;
import com.nevil.meizi.adapter.MainFragmentPageAdapter;
import com.nevil.meizi.fragment.GankFragment;
import com.nevil.meizi.fragment.GankMeiziFragment;
import com.nevil.meizi.fragment.TNGouTabFragment;
import com.nevil.meizi.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    //    @BindView(R.id.main_frame)
//    FrameLayout mframeLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPage;
    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;

    private long exitTime;
    MainFragmentPageAdapter mainFragmentPageAdapter;
    FragmentManager manager;
    CompositeDisposable mCompositeDisposable;
    protected Unbinder unbinder;
    MenuItem prevMenuItem;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mViewPage.setCurrentItem(0);
                Log.e("MEIZI", "onNavigationItemSelected: navigation_home");
                return true;
            case R.id.navigation_dashboard:
                mViewPage.setCurrentItem(1);
                Log.e("MEIZI", "onNavigationItemSelected: navigation_dashboard");
                return true;
//            case R.id.navigation_notifications:
//                mViewPage.setCurrentItem(2);
//                Log.e("MEIZI", "onNavigationItemSelected: navigation_notifications");
//                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragment();
        // initFragment();
    }


    private void initFragment() {
        String[] fragmentString = {"干货", "福利", "美女"};
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new GankFragment());
        fragmentList.add(new GankMeiziFragment());
       // fragmentList.add(new TNGouTabFragment());

        //fragmentList.add(new TNGouFragment());
        mainFragmentPageAdapter = new MainFragmentPageAdapter(getSupportFragmentManager(), fragmentList, fragmentString);
        mViewPage.setAdapter(mainFragmentPageAdapter);
        mViewPage.addOnPageChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(mDrawerLayout, "再按一次退出程序", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clean) {
            FileUtil.cleanCache(this);
            return true;
        } else if (id == R.id.action_github) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TangKhun/Meizi")));
            return true;
        } else if (id == R.id.action_about) {

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
//            case R.id.nav_gank:
//                setGankFragment();
//            case R.id.nav_gank_fuli:
//                setGankFuliFragment();
//                break;
//            case R.id.nav_sexy_beauty:
//                setClassId(1);
//                break;
//            case R.id.nav_japanese_beauty:
//                setClassId(2);
//                break;
//            case R.id.nav_silk_stockings:
//                setClassId(3);
//                break;
//            case R.id.nav_beauty_photos:
//                setClassId(4);
//                break;
//            case R.id.nav_beauty_portrait:
//                setClassId(5);
//                break;
//            case R.id.nav_pure_beauty:
//                setClassId(6);
//                break;
//            case R.id.nav_sexy_models:
//                setClassId(7);
//                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void setGankFragment() {
//        GankFragment fragment = (GankFragment) manager.findFragmentByTag("gank.io");
//        if (fragment == null) {
//            fragment = new GankFragment();
//        }
//        manager.beginTransaction().replace(R.id.main_frame, fragment, "gank.io").commitAllowingStateLoss();
//    }
//
//    public void setGankFuliFragment() {
//        GankMeiziFragment fragment = (GankMeiziFragment) manager.findFragmentByTag("Gank.fuli");
//        if (fragment == null)
//            fragment = new GankMeiziFragment();
//        manager.beginTransaction().replace(R.id.main_frame, fragment, "Gank.fuli").commitAllowingStateLoss();
//
//    }
//
//    public void setClassId(int classId) {
//        try {
//            TNGouFragment fragment = (TNGouFragment) manager.findFragmentByTag("TNGou");
//            if (fragment != null) {
//                fragment.changeClassId(classId);
//            } else {
//                TNGouFragment tnGouFragment = new TNGouFragment();
//                manager.beginTransaction().replace(R.id.main_frame, tnGouFragment, "TNGou").commitAllowingStateLoss();
//                tnGouFragment.changeClassId(classId);
//            }
//        } catch (Exception e) {
//            Log.e("MEIZI", "setClassId: " + e.getMessage());
//        }
//    }


    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        if (disposable != null)
            mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
        unbinder.unbind();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            mBottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        mBottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = mBottomNavigationView.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
