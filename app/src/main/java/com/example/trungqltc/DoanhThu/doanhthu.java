package com.example.trungqltc.DoanhThu;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.trungqltc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import at.markushi.ui.CircleButton;

public class doanhthu extends Fragment {

    CircleButton btnadddoanhthu;
    private DB_Doanhthu db_Doanhthu;
    ListView rvDoanhthu;
    List<ItemDoanhThu> list_doanhthu;
    AdapterDoanhThu adapterDoanhThu;
    ItemDoanhThu itemDoanhThu;
    doanhthu doanhthu;



    public AlertDialog alertDialog;

    public static doanhthu newInstance() {

        Bundle args = new Bundle();

        doanhthu fragment = new doanhthu();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doanhthu, container, false);


        btnadddoanhthu = view.findViewById(R.id.btnadddoanhthu);
        rvDoanhthu = view.findViewById(R.id.rvDoanhthu);
        db_Doanhthu = new DB_Doanhthu(getContext());
        list_doanhthu = db_Doanhthu.getAllDoanhthu();
        setAdapter();




        btnadddoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View alert = LayoutInflater.from(getContext()).inflate(R.layout.adddoanhthu,null);
                builder.setView(alert);
                final EditText edittextDanhmuc_1,edittextSotien_1,edittextNgay_1,edittextGhichu_1;
                final Button buttonHuy,buttonLuu;

                Log.d("log", "onStart: ");
                edittextDanhmuc_1 = alert.findViewById(R.id.edittextDanhmuc_1);
                edittextSotien_1 = alert.findViewById(R.id.edittextSotien_1);
                edittextNgay_1 = alert.findViewById(R.id.edittextNgay_1);
                edittextGhichu_1 = alert.findViewById(R.id.edittextGhichu_1);

                buttonHuy = alert.findViewById(R.id.buttonHuy);
                buttonLuu = alert.findViewById(R.id.buttonLuu);

                final DB_Doanhthu db_Doanhthu = new DB_Doanhthu(getContext());
                //Chọn ngày

                edittextNgay_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view_2) {
                        ChonNgay();
                    }

                    private void ChonNgay() {
                        final Calendar calendar = Calendar.getInstance();
                        int ngay = calendar.get(Calendar.DATE);
                        int thang = calendar.get(Calendar.MONTH);
                        int nam = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                // i:năm, i1:tháng, i2:ngày
                                calendar.set(i,i1,i2);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                edittextNgay_1.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();
                    }
                });
                //Hủy
                buttonHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                        Toast.makeText(getContext(),"Hủy",Toast.LENGTH_LONG).show();
                    }
                });
                //Lưu
                buttonLuu.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String danhmuc = edittextDanhmuc_1.getText().toString();
                        Integer sotien = Integer.valueOf(edittextSotien_1.getText().toString());
                        String ngay = edittextNgay_1.getText().toString();
                        String ghichu = edittextGhichu_1.getText().toString();
                        ItemDoanhThu itemDoanhThu = new ItemDoanhThu(danhmuc, sotien, ngay, ghichu);
                        db_Doanhthu.addDoanhthu(itemDoanhThu);
                        updateListDoanhthu();
                        setAdapter();
                        alertDialog.cancel();
                    }
                });
                builder.create();
                alertDialog = builder.show();
            }
        });




        rvDoanhthu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View alert = LayoutInflater.from(getContext()).inflate(R.layout.suadoanhthu,null);
                builder.setView(alert);
                final EditText edittextDanhmuc_11,editSotien_11,editSuangay_11,editGhichu_11;
                final Button editXoa_11,editUpdate_11,editResert_11,btncancel_11;
                edittextDanhmuc_11 = alert.findViewById(R.id.editDanhmuc_11);
                editSotien_11 = alert.findViewById(R.id.editSotien_11);
                editSuangay_11 = alert.findViewById(R.id.editSuangay_11);
                editGhichu_11 = alert.findViewById(R.id.editGhichu_11);

                editXoa_11 = alert.findViewById(R.id.editXoa_11);
                editUpdate_11 = alert.findViewById(R.id.editUpdate_11);
                editResert_11 = alert.findViewById(R.id.editResert_11);
                btncancel_11 = alert.findViewById(R.id.btncancel_11);


                final ItemDoanhThu itemDoanhThu = list_doanhthu.get(position);

                edittextDanhmuc_11.setText(itemDoanhThu.getDanhmuc_1());
                editSotien_11.setText((String.valueOf(itemDoanhThu.getTien_1())));
                editSuangay_11.setText(itemDoanhThu.getNgay_1());
                editGhichu_11.setText(itemDoanhThu.getGhichu_1());

                //sửa ngày
                //Chọn ngày
                editSuangay_11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view_2) {
                        ChonNgay();
                    }

                    private void ChonNgay() {
                        final Calendar calendar = Calendar.getInstance();
                        int ngay = calendar.get(Calendar.DATE);
                        int thang = calendar.get(Calendar.MONTH);
                        int nam = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                // i:năm, i1:tháng, i2:ngày
                                calendar.set(i,i1,i2);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                editSuangay_11.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();
                    }
                });
                editUpdate_11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        itemDoanhThu.setDanhmuc_1(edittextDanhmuc_11.getText().toString());
                        itemDoanhThu.setTien_1(Integer.parseInt(editSotien_11.getText().toString()));
                        itemDoanhThu.setNgay_1(editSuangay_11.getText().toString());
                        itemDoanhThu.setGhichu_1(editGhichu_11.getText().toString());
                        db_Doanhthu.updateDoanhthu(itemDoanhThu);
                        int result = db_Doanhthu.updateDoanhthu(itemDoanhThu);
                        if(result>0){
                            updateListDoanhthu();
                        }
                        Toast.makeText(getContext(),"update successfuly",Toast.LENGTH_LONG).show();
                         alertDialog.cancel();
                    }
                });

                editResert_11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editGhichu_11.setText("");
                        editSotien_11.setText(null);
                        editSuangay_11.setText("");
                        edittextDanhmuc_11.setText("");
                    }
                });

                editXoa_11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ItemDoanhThu muc = list_doanhthu.get(position);
                        int result = db_Doanhthu.deleteDoanhthu(muc.getId());
                        if(result>0){
                            Toast.makeText(getContext(), "Delete successfuly", Toast.LENGTH_SHORT).show();
                            updateListDoanhthu();
                        }else{
                            Toast.makeText(getContext(), "Delete fail", Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.cancel();
                    }
                });
                btncancel_11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"Cancel",Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });
                builder.create();
                alertDialog = builder.show();
            }
        });




        return view;
    }






    public void updateListDoanhthu(){
        list_doanhthu.clear();
        list_doanhthu.addAll(db_Doanhthu.getAllDoanhthu());
        if(adapterDoanhThu!= null){
            adapterDoanhThu.notifyDataSetChanged();
        }
    }

    private void setAdapter() {
        if (adapterDoanhThu == null) {
            adapterDoanhThu = new AdapterDoanhThu(getContext(),R.layout.item_doanh_thu,list_doanhthu);
            rvDoanhthu.setAdapter(adapterDoanhThu);
        }else{
            adapterDoanhThu.notifyDataSetChanged();
            rvDoanhthu.setSelection(adapterDoanhThu.getCount()-1);
        }
    }






}
