package com.example.buoi_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Bai_2Activity extends AppCompatActivity {

    ListView lv_b2;
    ImageView imv_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_2);


        lv_b2 = findViewById(R.id.lv_bai2);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // xin quyen
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    999);
        }
        List<String> ds = new ArrayList<>();
        String[] projection = {
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATA,
        };
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
//                Log.e("--------------" + id , "----------------" + name);
//                for (int i = 0; i < cursor.getColumnCount(); i++) {
//                    ds.add(String.valueOf(cursor.getString(i)));
//                }
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                ds.add(data);
                cursor.moveToNext();
            }
            cursor.close();

            Show_img_Adapter adapter = new Show_img_Adapter(Bai_2Activity.this , ds);
            lv_b2.setAdapter(adapter);
            lv_b2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.show_img , null);

                    ImageView img = layout.findViewById(R.id.imv_them);
                    img.setImageBitmap(BitmapFactory.decodeFile(ds.get(position)));

                    AlertDialog.Builder builder = new AlertDialog.Builder(Bai_2Activity.this);
                    builder.setTitle("Show image on AlertDialog ");

                    builder.setView(layout);
                    builder.setPositiveButton("Hide",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        setTitle("Show Image with Size = " + ds.size());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // xin quyen
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    999);
            Toast.makeText(getApplicationContext(), "ban chua cap quyen",
                    Toast.LENGTH_SHORT).show();
        } else {
            // goi lai ham lay danh ba
            // moi nhan lan nua
            // lay danh ba

            Toast.makeText(getApplicationContext(), "ok hazz",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void back_show(View view){
        finish();
    }
}