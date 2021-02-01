package com.example.buoi_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv_buoi3;
    List<DanhBa> dsDanhBaList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_buoi3 = findViewById(R.id.lv_buoi3);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // xin quyen
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
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

    public void load_contact(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // xin quyen
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    999);
        } else {
            // lay danh ba
            Uri uri = Uri.parse("content://contacts/people");
            dsDanhBaList = new ArrayList<>();
            Cursor cursor = getContentResolver().query(uri, null, null,
                    null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String id = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));
                    Log.e("--------------" + id , "----------------" + name);
                    DanhBa danhBa = new DanhBa(id, name);
                    dsDanhBaList.add(danhBa);
                    cursor.moveToNext();
                }
                cursor.close();
                Toast.makeText(getApplicationContext(), dsDanhBaList.size() + " -------11111",
                        Toast.LENGTH_SHORT).show();
                LazyCursorAdapter adapter = new LazyCursorAdapter(MainActivity.this ,
                        dsDanhBaList);
                lv_buoi3.setAdapter(adapter);

                lv_buoi3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage(String.valueOf(dsDanhBaList.get(position).getName()));
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
        }
    }
    public void show(View view){
        startActivity(new Intent(this , Bai_2Activity.class));
    }
}