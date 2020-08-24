package com.example.trungqltc.KhoanChi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB_Khoanchi extends SQLiteOpenHelper {
    private final String TAG_2 = "DB.KHOANCHI";
    private static final String KHOAN_CHI = "khoanchi_manager";
    private static final String ID_2 = "id_2";
    private static final String DANH_MUC_2= "danh_muc_2";
    private static final String SO_TIEN_2 = "so_tien2";
    private static final String NGAY_2 = "ngay_2";
    private static final String GHI_CHU_2 = "ghi_chu_2";
    private static int VERSION = 1;

    private Context context;

    private String SQLQuery = "CREATE TABLE " + KHOAN_CHI + " (" +
            ID_2 + " integer primary key, " +
            DANH_MUC_2 + " STRING, " +
            SO_TIEN_2 + " INTEGER, " +
            NGAY_2 + " STRING, " +
            GHI_CHU_2 + " STRING ) "  ;


    public DB_Khoanchi(@Nullable Context context) {
        super(context, KHOAN_CHI, null, VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d(TAG_2, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG_2, "onUpgrade: ");

    }
    public void addKhoanchi(Item_Khoan_Chi itemKhoanChi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DANH_MUC_2, itemKhoanChi.getDanhmuc_2());
        values.put(SO_TIEN_2, itemKhoanChi.getTien_2());
        values.put(NGAY_2, itemKhoanChi.getNgay_2());
        values.put(GHI_CHU_2, itemKhoanChi.getGhichu_2());

        db.insert(KHOAN_CHI, null, values);
        db.close();
    }
    public List<Item_Khoan_Chi> getAllKhoanchi() {
        List<Item_Khoan_Chi> arrayListKhoanchi = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + KHOAN_CHI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Item_Khoan_Chi itemKhoanChi = new Item_Khoan_Chi();
                itemKhoanChi.setId_2(cursor.getInt(0));
                itemKhoanChi.setDanhmuc_2(cursor.getString(1));
                itemKhoanChi.setTien_2(cursor.getInt(2));
                itemKhoanChi.setNgay_2(cursor.getString(3));
                itemKhoanChi.setGhichu_2(cursor.getString(4));
                arrayListKhoanchi.add(itemKhoanChi);

            } while (cursor.moveToNext());
        }
        db.close();
        return arrayListKhoanchi;
    }

    public int updateKhoanchi(Item_Khoan_Chi itemKhoanChi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DANH_MUC_2,itemKhoanChi.getDanhmuc_2());
        contentValues.put(SO_TIEN_2,itemKhoanChi.getTien_2());
        contentValues.put(NGAY_2,itemKhoanChi.getNgay_2());
        contentValues.put(GHI_CHU_2,itemKhoanChi.getGhichu_2());
        return db.update(KHOAN_CHI,contentValues,ID_2+"=?",new String[]{String.valueOf(itemKhoanChi.getId_2())});
    }
    public int deleteKhoanchi(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(KHOAN_CHI,ID_2+"=?",new String[] {String.valueOf(id)});
    }


}
