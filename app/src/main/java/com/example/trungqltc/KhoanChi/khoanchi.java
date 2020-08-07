package com.example.trungqltc.KhoanChi;

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

public class khoanchi extends Fragment {
    CircleButton btnaddkhoanchi;
    private DB_Khoanchi db_khoanchi;
    ListView rvKhoanchi;
    List<Item_Khoan_Chi> list_khoanchi;
    AdapterKhoanChi adapterKhoanChi;

    public AlertDialog alertDialog;

    public static khoanchi newInstance() {

        Bundle args = new Bundle();

        khoanchi fragment = new khoanchi();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khoanchi, container, false);
        btnaddkhoanchi = view.findViewById(R.id.btnaddKhoanchi);
        rvKhoanchi = view.findViewById(R.id.rvKhoanchi);
        db_khoanchi = new DB_Khoanchi(getContext());
        list_khoanchi = db_khoanchi.getAllKhoanchi();

        setAdapter();






        btnaddkhoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View alert = LayoutInflater.from(getContext()).inflate(R.layout.add_khoan_chi,null);
                builder.setView(alert);
                final EditText edittextDanhmuc_2,edittextSotien_2,edittextNgay_2,edittextGhichu_2;
                final Button buttonHuy_2,buttonLuu_2;

                Log.d("log", "onStart: ");
                edittextDanhmuc_2 = alert.findViewById(R.id.edittextDanhmuc_2);
                edittextSotien_2 = alert.findViewById(R.id.edittextSotien_2);
                edittextNgay_2 = alert.findViewById(R.id.edittextNgay_2);
                edittextGhichu_2 = alert.findViewById(R.id.edittextGhichu_2);

                buttonHuy_2 = alert.findViewById(R.id.buttonHuy_2);
                buttonLuu_2 = alert.findViewById(R.id.buttonLuu_2);

                final DB_Khoanchi db_khoanchi = new DB_Khoanchi(getContext());

                //Chọn ngày
                edittextNgay_2.setOnClickListener(new View.OnClickListener() {
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
                                edittextNgay_2.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();
                    }
                });

                //Hủy
                buttonHuy_2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        alertDialog.cancel();
                        Toast.makeText(getContext(),"Hủy",Toast.LENGTH_LONG).show();
                    }
                });
                //Lưu
                buttonLuu_2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String danhmuc_2 = edittextDanhmuc_2.getText().toString();
                       Integer sotien_2 = Integer.valueOf(edittextSotien_2.getText().toString());
                        String ngay_2 = edittextNgay_2.getText().toString();
                        String ghichu_2 = edittextGhichu_2.getText().toString();

                        Item_Khoan_Chi item_khoan_chi = new Item_Khoan_Chi(danhmuc_2, sotien_2, ngay_2, ghichu_2);
                        db_khoanchi.addKhoanchi(item_khoan_chi);

                        updateListKhoanchi();
                        setAdapter();
                        alertDialog.cancel();
                    }

                });

                builder.create();
                alertDialog = builder.show();
            }
        });

        rvKhoanchi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View alert = LayoutInflater.from(getContext()).inflate(R.layout.suakhoanchi,null);
                builder.setView(alert);

                final EditText edittextDanhmuc_22,editSotien_22,editSuangay_22,editGhichu_22;
                final Button editXoa_22,editUpdate_22,editResert_22,btncancel_22;


                edittextDanhmuc_22 = alert.findViewById(R.id.editDanhmuc_22);
                editSotien_22 = alert.findViewById(R.id.editSotien_22);
                editSuangay_22 = alert.findViewById(R.id.editSuangay_22);
                editGhichu_22 = alert.findViewById(R.id.editGhichu_22);

                editXoa_22 = alert.findViewById(R.id.editXoa_22);
                editUpdate_22 = alert.findViewById(R.id.editUpdate_22);
                editResert_22 = alert.findViewById(R.id.editResert_22);
                btncancel_22 = alert.findViewById(R.id.btncancel_22);

                final Item_Khoan_Chi item_khoan_chi = list_khoanchi.get(position);

                edittextDanhmuc_22.setText(item_khoan_chi.getDanhmuc_2());
                editSotien_22.setText(String.valueOf(item_khoan_chi.getTien_2()));
                editSuangay_22.setText(item_khoan_chi.getNgay_2());
                editGhichu_22.setText(item_khoan_chi.getGhichu_2());


                //sửa ngày
                editSuangay_22.setOnClickListener(new View.OnClickListener() {
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
                                editSuangay_22.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();
                    }
                });

                editUpdate_22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        item_khoan_chi.setDanhmuc_2(edittextDanhmuc_22.getText().toString());
                        item_khoan_chi.setTien_2(Integer.parseInt(editSotien_22.getText().toString()));
                        item_khoan_chi.setNgay_2(editSuangay_22.getText().toString());
                        item_khoan_chi.setGhichu_2(editGhichu_22.getText().toString());
                        db_khoanchi.updateKhoanchi(item_khoan_chi);
                        int result = db_khoanchi.updateKhoanchi(item_khoan_chi);
                        if(result>0){
                            updateListKhoanchi();
                        }
                        Toast.makeText(getContext(),"update successfuly",Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });

                editResert_22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editGhichu_22.setText("");
                        editSotien_22.setText(null);
                        editSuangay_22.setText("");
                        edittextDanhmuc_22.setText("");
                    }
                });

                editXoa_22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Item_Khoan_Chi muc = list_khoanchi.get(position);
                        int result = db_khoanchi.deleteKhoanchi(muc.getId_2());
                        if(result>0){
                            Toast.makeText(getContext(), "Delete successfuly", Toast.LENGTH_SHORT).show();
                            updateListKhoanchi();
                        }else{
                            Toast.makeText(getContext(), "Delete fail", Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.cancel();
                    }
                });

                btncancel_22.setOnClickListener(new View.OnClickListener() {
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



    public void updateListKhoanchi(){
        list_khoanchi.clear();
        list_khoanchi.addAll(db_khoanchi.getAllKhoanchi());
        if(adapterKhoanChi!= null){
            adapterKhoanChi.notifyDataSetChanged();
        }
    }

    private void setAdapter() {
        if (adapterKhoanChi == null) {
            adapterKhoanChi = new AdapterKhoanChi(getContext(),R.layout.item_khoan_chi,list_khoanchi);
            rvKhoanchi.setAdapter(adapterKhoanChi);
        }else{
            adapterKhoanChi.notifyDataSetChanged();
            rvKhoanchi.setSelection(adapterKhoanChi.getCount()-1);
        }
    }
}
