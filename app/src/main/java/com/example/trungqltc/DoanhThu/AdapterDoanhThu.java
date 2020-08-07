package com.example.trungqltc.DoanhThu;

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

public class AdapterDoanhThu extends ArrayAdapter<ItemDoanhThu> {
    private List<ItemDoanhThu> arrayList;
    private Context context;
    private int resoure;

    public AdapterDoanhThu(@NonNull Context context, @LayoutRes int resource, @NonNull List<ItemDoanhThu> objects) {
        super(context, resource,objects);
        this.arrayList = objects;
        this.context = context;
        this.resoure = resource;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_doanh_thu,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.danhmuc_1 = (TextView)convertView.findViewById(R.id.danhmuc_1);
            viewHolder.tien_1 = (TextView)convertView.findViewById(R.id.tien_1);
            viewHolder.ngay_1  = (TextView)convertView.findViewById(R.id.ngay_1);
            viewHolder.ghichu_1 = (TextView)convertView.findViewById(R.id.ghichu_1);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
         final ItemDoanhThu itemDoanhThu = arrayList.get(position);
        viewHolder.danhmuc_1.setText(itemDoanhThu.getDanhmuc_1());
        viewHolder.tien_1.setText(String.valueOf(itemDoanhThu.getTien_1()));
        viewHolder.ngay_1.setText(itemDoanhThu.getNgay_1());
        viewHolder.ghichu_1.setText(itemDoanhThu.getGhichu_1());


        return convertView;
    }


    public class ViewHolder {
        TextView id;
        TextView danhmuc_1;
        TextView tien_1;
        TextView ngay_1;
        TextView ghichu_1;


    }
}
