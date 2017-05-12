package com.nevil.meizi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.nevil.meizi.fragment.GankMeiziFragment;
import com.nevil.meizi.fragment.TNGouFragment;
import com.nevil.meizi.util.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_frame)
    FrameLayout mframeLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private long exitTime;

    FragmentManager manager;
    CompositeDisposable mCompositeDisposable;
    protected Unbinder unbinder;

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
        initFragment();
    }

    private void initFragment() {
        manager = getSupportFragmentManager();
        manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_UNSET).add(R.id.main_frame, new GankMeiziFragment(), "Gank").commit();
        //manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_UNSET).add(R.id.main_frame, new TNGouFragment(), "TNGou").commit();
        manager.executePendingTransactions();
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
            cleanCache();
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
            case R.id.nav_gank_fuli:
                setGankFragment();
                break;
            case R.id.nav_sexy_beauty:
                setClassId(1);
                break;
            case R.id.nav_japanese_beauty:
                setClassId(2);
                break;
            case R.id.nav_silk_stockings:
                setClassId(3);
                break;
            case R.id.nav_beauty_photos:
                setClassId(4);
                break;
            case R.id.nav_beauty_portrait:
                setClassId(5);
                break;
            case R.id.nav_pure_beauty:
                setClassId(6);
                break;
            case R.id.nav_sexy_models:
                setClassId(7);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setGankFragment() {
        GankMeiziFragment fragment = (GankMeiziFragment) manager.findFragmentByTag("Gank");
        if (fragment == null)
            fragment = new GankMeiziFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment, "Gank").commitAllowingStateLoss();

    }

    public void setClassId(int classId) {
        try {
            TNGouFragment fragment = (TNGouFragment) manager.findFragmentByTag("TNGou");
            if (fragment != null) {
                fragment.changeClassId(classId);
            } else {
                TNGouFragment tnGouFragment = new TNGouFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, tnGouFragment, "TNGou").commitAllowingStateLoss();
                tnGouFragment.changeClassId(classId);
            }
        } catch (Exception e) {
            Log.e("MEIZI", "setClassId: " + e.getMessage());
        }
    }

    public void cleanCache() {
        Log.e("MEZI", "cleanCache: ");
        Observable.create((ObservableOnSubscribe<Boolean>) emitter -> {
            try {
                Glide.get(MainActivity.this).clearDiskCache();
                Log.e("MEZI", "clearDiskCache: ");
                emitter.onNext(true);
            } catch (Exception e) {
                emitter.onError(e);
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.e("MEZI", "onNext: " + aBoolean);
                if (aBoolean)
                    T.showShortToast(MainActivity.this, "清理完成");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("MEZI", "onError: " + throwable.getMessage());
                T.showShortToast(MainActivity.this, "清理失败");
            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        if (disposable != null)
            mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("MEIZI", "onActivityResult: " + requestCode + resultCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
        unbinder.unbind();
    }


}
