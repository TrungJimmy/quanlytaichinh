package com.example.trungqltc.DoanhThu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DB_Doanhthu extends SQLiteOpenHelper {
        private final String TAG_1 = "DB.DOANHTHU";
        private static final String DOANH_THU = "doanhthu_manager";
        private static final String ID = "id";
        private static final String DANH_MUC_1= "danh_muc_1";
        private static final String SO_TIEN_1 = "so_tien1";
        private static final String NGAY_1 = "ngay_1";
        private static final String GHI_CHU_1= "ghi_chu_1";
        private static int VERSION = 1;

        private Context context;

        private String SQLQuery = "CREATE TABLE " + DOANH_THU + " (" +
                ID + " INTEGER primary key, " +
                DANH_MUC_1 + " TEXT, " +
                SO_TIEN_1 + " INTEGER, " +
                NGAY_1 + " TEXT, " +
                GHI_CHU_1 + " TEXT ) "  ;


        public DB_Doanhthu(Context context) {
            super(context, DOANH_THU, null, VERSION);
            this.context = context;
        }



    @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SQLQuery);
            Log.d(TAG_1, "onCreate: ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.d(TAG_1, "onUpgrade: ");
        }

    public void addDoanhthu(ItemDoanhThu itemDoanhThu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DANH_MUC_1, itemDoanhThu.getDanhmuc_1());
        values.put(SO_TIEN_1 , itemDoanhThu.getTien_1());
        values.put(NGAY_1, itemDoanhThu.getNgay_1());
        values.put(GHI_CHU_1, itemDoanhThu.getGhichu_1());

        db.insert(DOANH_THU, null, values);
        db.close();

    }

        public List<ItemDoanhThu> getAllDoanhthu() {
            List<ItemDoanhThu> arrayListDoanhthu = new ArrayList<>();

            String selectQuery = "SELECT * FROM " + DOANH_THU;

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(selectQuery,null);
            if (cursor.moveToFirst()) {
                do {
                    ItemDoanhThu itemDoanhThu = new ItemDoanhThu();
                    itemDoanhThu.setId(cursor.getInt(0));
                    itemDoanhThu.setDanhmuc_1(cursor.getString(1));
                    itemDoanhThu.setTien_1(cursor.getInt(2));
                    itemDoanhThu.setNgay_1(cursor.getString(3));
                    itemDoanhThu.setGhichu_1(cursor.getString(4));
                    arrayListDoanhthu.add(itemDoanhThu);

                } while (cursor.moveToNext());
            }
            db.close();
            return arrayListDoanhthu;
        }
        public int updateDoanhthu(ItemDoanhThu itemDoanhThu){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DANH_MUC_1,itemDoanhThu.getDanhmuc_1());
            contentValues.put(SO_TIEN_1,itemDoanhThu.getTien_1());
            contentValues.put(NGAY_1,itemDoanhThu.getNgay_1());
            contentValues.put(GHI_CHU_1,itemDoanhThu.getGhichu_1());
            return db.update(DOANH_THU,contentValues,ID+"=?",new String[]{String.valueOf(itemDoanhThu.getId())});
        }
        public int deleteDoanhthu(int id){
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(DOANH_THU,ID+"=?",new String[] {String.valueOf(id)});
        }

}
