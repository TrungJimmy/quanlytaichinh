package com.example.trungqltc.DoanhThu;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.trungqltc.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import at.markushi.ui.CircleButton;

public class doanhthu extends Fragment {

    CircleButton btnadddoanhthu;
    private DB_Doanhthu db_Doanhthu;
    ListView rvDoanhthu;
    List<ItemDoanhThu> list_doanhthu;
    AdapterDoanhThu adapterDoanhThu;
    ItemDoanhThu itemDoanhThu;
    doanhthu doanhthu;
    EditText editDanhmuc_11;
//    ImageButton btnMenuAddDoanhThu;
//    ImageButton btnMenuSuaDoanhThu;
DecimalFormat integerFormatter = new DecimalFormat("#,###.###", new DecimalFormatSymbols(Locale.US));


    public AlertDialog alertDialog;

    public static doanhthu newInstance() {

        Bundle args = new Bundle();

        doanhthu fragment = new doanhthu();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_doanhthu, container, false);

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
                final ImageButton btnMenuAddDoanhThu, btnMenuSuaDoanhThu;

                Log.d("log", "onStart: ");
                edittextDanhmuc_1 = alert.findViewById(R.id.edittextDanhmuc_1);
                edittextSotien_1 = alert.findViewById(R.id.edittextSotien_1);
                edittextNgay_1 = alert.findViewById(R.id.edittextNgay_1);
                edittextGhichu_1 = alert.findViewById(R.id.edittextGhichu_1);
                btnMenuAddDoanhThu = alert.findViewById(R.id.btnMenuAddDoanhThu);
                btnMenuSuaDoanhThu = alert.findViewById(R.id.btnMenuSuaDoanhThu);

                buttonHuy = alert.findViewById(R.id.buttonHuy);
                buttonLuu = alert.findViewById(R.id.buttonLuu);

                final DB_Doanhthu db_Doanhthu = new DB_Doanhthu(getContext());

                btnMenuAddDoanhThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"Adddd",Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                        View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                        final Spinner mSpinner = mView.findViewById(R.id.menuSpinner);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                                getResources().getStringArray(R.array.Income));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner.setAdapter(adapter);
                        Toast.makeText(getContext(),"Adddd_22222",Toast.LENGTH_SHORT).show();
                        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if(!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Chọn mục thu")){
                                    Toast.makeText(getContext(),mSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                                    edittextDanhmuc_1.setText(mSpinner.getSelectedItem().toString());
                                    dialog.dismiss();
                                }
                            }
                        });
                        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        mBuilder.setView(mView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                    }
                });

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
                        alertDialog.dismiss();
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
                        alertDialog.dismiss();
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
                final ImageButton btnMenuSuaDoanhThu;
                editSotien_11 = alert.findViewById(R.id.editSotien_11);
                editSuangay_11 = alert.findViewById(R.id.editSuangay_11);
                editGhichu_11 = alert.findViewById(R.id.editGhichu_11);
                editXoa_11 = alert.findViewById(R.id.editXoa_11);
                editUpdate_11 = alert.findViewById(R.id.editUpdate_11);
                editResert_11 = alert.findViewById(R.id.editResert_11);
                btncancel_11 = alert.findViewById(R.id.btncancel_11);
                btnMenuSuaDoanhThu = alert.findViewById(R.id.btnMenuSuaDoanhThu);
                editDanhmuc_11 = alert.findViewById(R.id.editDanhmuc_11);

                final ItemDoanhThu itemDoanhThu = list_doanhthu.get(position);
                //editSotien_11.setText(String.valueOf(integerFormatter.format(editSotien_11)));

                editDanhmuc_11.setText(itemDoanhThu.getDanhmuc_1());
                editSotien_11.setText((String.valueOf(itemDoanhThu.getTien_1())));
                editSuangay_11.setText(itemDoanhThu.getNgay_1());
                editGhichu_11.setText(itemDoanhThu.getGhichu_1());


                btnMenuSuaDoanhThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                        View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                        final Spinner mSpinner = mView.findViewById(R.id.menuSpinner);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                                getResources().getStringArray(R.array.Income));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner.setAdapter(adapter);
                        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if(!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Chọn mục thu")){
                                    Toast.makeText(getContext(),mSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                                    Log.d("log",editDanhmuc_11.getText().toString());
                                    editDanhmuc_11.getText().clear();
                                    editDanhmuc_11.setText(mSpinner.getSelectedItem().toString());

                                    dialog.dismiss();
                                }
                            }
                        });
                        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        mBuilder.setView(mView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                    }

                });

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

                        itemDoanhThu.setDanhmuc_1(editDanhmuc_11.getText().toString());
                        itemDoanhThu.setTien_1(Float.parseFloat(editSotien_11.getText().toString()));
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
                        editDanhmuc_11.setText("");
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
