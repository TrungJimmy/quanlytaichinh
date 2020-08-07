package com.example.trungqltc;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.trungqltc.DoanhThu.doanhthu;
import com.example.trungqltc.KhoanChi.khoanchi;
import com.example.trungqltc.ThongKe.thongke;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> titleFrm = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
    public Fragment getItem(int possition){

            Fragment frag= null;
            switch (possition){
                case 0:
                    frag= doanhthu.newInstance();
                    break;
                case 1:
                    frag= khoanchi.newInstance();
                    break;
                case 2:
                    frag= thongke.newInstance();
                    break;
                default:
                    break;
            }
            return frag;
        }

        @Override
    public int getCount(){
            return  fragmentList.size();
        }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFrm.get(position);
    }
    public void add(Fragment frm, String t ){
            fragmentList.add(frm);
            titleFrm.add(t);
    }

}
