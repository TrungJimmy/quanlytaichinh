package com.example.trungqltc.KhoanChi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trungqltc.R;

import java.util.List;

public class AdapterKhoanChi extends ArrayAdapter<Item_Khoan_Chi> {
    private List<Item_Khoan_Chi> arrayList2;
    private Context context;
    private int resource;


    public AdapterKhoanChi(@NonNull Context context, @LayoutRes int resource, @NonNull List<Item_Khoan_Chi> objects) {
        super(context, resource,objects);
        this.arrayList2 = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_khoan_chi,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.danhmuc_2 = (TextView)convertView.findViewById(R.id.Danhmuc_2);
            viewHolder.tien_2 = (TextView)convertView.findViewById(R.id.tien_2);
            viewHolder.ngay_2  = (TextView)convertView.findViewById(R.id.ngay_2);
            viewHolder.ghichu_2 = (TextView)convertView.findViewById(R.id.ghichu_2);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Item_Khoan_Chi item_khoan_chi = arrayList2.get(position);
        viewHolder.danhmuc_2.setText(item_khoan_chi.getDanhmuc_2());
        viewHolder.tien_2.setText(String.valueOf(item_khoan_chi.getTien_2()));
        viewHolder.ngay_2.setText(item_khoan_chi.getNgay_2());
        viewHolder.ghichu_2.setText(item_khoan_chi.getGhichu_2());

        return convertView;
    }

    public class ViewHolder  {

            TextView id_2;
            TextView danhmuc_2;
            TextView tien_2;
            TextView ngay_2;
            TextView ghichu_2;


        }
    }



