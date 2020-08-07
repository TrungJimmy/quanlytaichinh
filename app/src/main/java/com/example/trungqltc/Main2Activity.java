package com.example.trungqltc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.trungqltc.DoanhThu.doanhthu;
import com.example.trungqltc.KhoanChi.khoanchi;
import com.example.trungqltc.ThongKe.thongke;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class Main2Activity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem  tabDoanhThu,tabKhoanChi,tabThongKe;
    private boolean isTransactionSafe;
    private boolean isTransactionPending;
    private int[] tabIcons = {
            R.drawable.icon_doanhthu,
            R.drawable.icon_khoanchi,
            R.drawable.icon_thongke,
            R.drawable.icon_add,
            R.drawable.icon_lich};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tabDoanhThu = findViewById(R.id.tabDoanhThu);
        final ViewPager viewPager = findViewById(R.id.view_pager);
        addTabs(viewPager);
        //chuyển sang TabLayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        setupTabIcons();
            }


    public void addTabs(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new doanhthu(),"Doanh Thu");
        adapter.add(new khoanchi(),"Khoản chi");
        adapter.add(new thongke(),"Thống kê");
        viewPager.setAdapter(adapter);

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    public void onPostResume() {
        super.onPostResume();
        isTransactionSafe = true;
    }
    public void onPause() {
        super.onPause();
        isTransactionSafe = false;
    }


    }