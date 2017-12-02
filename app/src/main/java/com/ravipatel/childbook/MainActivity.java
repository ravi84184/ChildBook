package com.ravipatel.childbook;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.*;

public class MainActivity extends AppCompatActivity {


    private Button addBookBtn;

    private DataBaseHelper myDB;

    private GridView listView;

    List<book>  listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
//
//        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
//            buffer.append("id: " +res.getString(0)+"\n");
//            buffer.append("name: "+res.getString(1)+"\n");
//
            book book = new book(res.getString(0),res.getString(1),res.getBlob(2));
            listItems.add(book);
//
        }

        BookAdapter listViewAdapter = new BookAdapter(this,listItems);
        listView.setAdapter(listViewAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        
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
}
