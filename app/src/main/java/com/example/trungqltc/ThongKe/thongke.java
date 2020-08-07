package com.example.trungqltc.ThongKe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trungqltc.DoanhThu.DB_Doanhthu;
import com.example.trungqltc.DoanhThu.ItemDoanhThu;
import com.example.trungqltc.KhoanChi.DB_Khoanchi;
import com.example.trungqltc.KhoanChi.Item_Khoan_Chi;
import com.example.trungqltc.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class thongke extends Fragment {
    Button btnChart;
    DB_Doanhthu db_Doanhthu;
    DB_Khoanchi db_Khoanchi;
    TextView tongThu;
    TextView tongChi;
    TextView candoi;
    ItemDoanhThu itemDoanhThu;
    Item_Khoan_Chi item_khoan_chi;
    FloatingActionButton floatingActionButton1;
    FloatingActionButton floatingActionButton2;


    ArrayList<Integer> Sum;

    int tong_DT=0;
    int tong_KC=0;
    int CanDoi =0;



    public static thongke newInstance() {
        
        Bundle args = new Bundle();
        thongke fragment = new thongke();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        tongThu = view.findViewById(R.id.tongThu);
        tongChi = view.findViewById(R.id.tongchi);
        candoi = view.findViewById(R.id.candoi);

        floatingActionButton1 = view.findViewById(R.id.floataction1);
        floatingActionButton2 = view.findViewById(R.id.floataction2);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PieChart.class);

                intent.putExtra("thu",tong_DT);
                intent.putExtra("chi",tong_KC);

                getActivity().startActivity(intent);



            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        TongThu();
        TongChi();
        CanDoi();

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){

        TongThu();
        TongChi();
        CanDoi();

        }
        else{
            //no
        }
    }

    public void TongThu(){
        itemDoanhThu = new ItemDoanhThu();
        db_Doanhthu= new DB_Doanhthu(getContext());
        List<ItemDoanhThu> tongitemthu = new ArrayList();
        tongitemthu = db_Doanhthu.getAllDoanhthu();
        db_Doanhthu.updateDoanhthu(itemDoanhThu);
        Sum = new ArrayList<>();
        for (int x=0;x<tongitemthu.size();x++ ){
            Sum.add(tongitemthu.get(x).getTien_1());
        }
        tong_DT =0; // chạy đi
        for (int i=0;i<Sum.size();i++){
            tong_DT += Sum.get(i);
        }
        db_Doanhthu.updateDoanhthu(itemDoanhThu);
        itemDoanhThu.setTien_1(tong_DT);
        tongThu.setText(String.valueOf(tong_DT));
    }

    public void TongChi(){
        item_khoan_chi = new Item_Khoan_Chi();
        db_Khoanchi= new DB_Khoanchi(getContext());
        List<Item_Khoan_Chi> tongitemchi = new ArrayList();
        tongitemchi = db_Khoanchi.getAllKhoanchi();
        db_Khoanchi.updateKhoanchi(item_khoan_chi);
        Sum = new ArrayList<>();
        for (int x=0;x<tongitemchi.size();x++ ){
            Sum.add(tongitemchi.get(x).getTien_2());
        }
        tong_KC =0; // chạy đi
        for (int i=0;i<Sum.size();i++){
            tong_KC += Sum.get(i);
        }
        db_Khoanchi.updateKhoanchi(item_khoan_chi);
        item_khoan_chi.setTien_2(tong_KC);
        tongChi.setText(String.valueOf(tong_KC));

    }
    public void CanDoi(){
        CanDoi = tong_DT-tong_KC ;
        candoi.setText(String.valueOf(CanDoi));
    }



}
