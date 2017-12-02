package com.ravipatel.childbook;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button addBookBtn;

    private DataBaseHelper myDB;

    private GridView listView;
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;


    List<book>  listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(checkPermissionsArray(Permissions.PERMISSIONS)){

        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }

        myDB = new DataBaseHelper(this);

        listView = (GridView) findViewById(R.id.bookList);

        addBookBtn = (Button) findViewById(R.id.addBookBtn);

        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);


            }
        });

        listItems = new ArrayList<book>();


        Cursor res = myDB.getBookData();
        if (res.getCount() == 0){

            return;
        }

        while (res.moveToNext()){

            book book = new book(res.getString(0),res.getString(1),res.getBlob(2));
            listItems.add(book);

        }

        BookAdapter listViewAdapter = new BookAdapter(this,listItems);
        listView.setAdapter(listViewAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();

//        myDB.deletePages();
//        myDB.deleteBook();
//        Cursor res = myDB.getBookData();
//        if (res.getCount() == 0){
//
//            return;
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()){
//            buffer.append("id: " +res.getString(0)+"\n");
//            buffer.append("name: "+res.getString(1)+"\n");
//        }
//        showMes("Data",buffer.toString());

    }

    public void showMes(String title, String mes){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(mes);
        builder.show();

    }

    /**
     * verifiy all the permissions passed to the array
     * @param permissions
     */
    public void verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions: verifying permissions.");

        ActivityCompat.requestPermissions(
                MainActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }
    /**
     * Check an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionsArray(String[] permissions){
        Log.d(TAG, "checkPermissionsArray: checking permissions array.");

        for(int i = 0; i< permissions.length; i++){
            String check = permissions[i];
            if(!checkPermissions(check)){
                return false;
            }
        }
        return true;
    }
    /**
     * Check a single permission is it has been verified
     * @param permission
     * @return
     */
    public boolean checkPermissions(String permission){
        Log.d(TAG, "checkPermissions: checking permission: " + permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(MainActivity.this, permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermissions: \n Permission was not granted for: " + permission);
            return false;
        }
        else{
            Log.d(TAG, "checkPermissions: \n Permission was granted for: " + permission);
            return true;
        }
    }
}
