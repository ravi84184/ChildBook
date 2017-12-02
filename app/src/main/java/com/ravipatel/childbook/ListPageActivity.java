package com.ravipatel.childbook;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListPageActivity extends AppCompatActivity {


    private static final String TAG = "ListPageActivity";
    private ListView listView;

    private DataBaseHelper myDB;


    List<Pages> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        myDB = new DataBaseHelper(this);

        listView = (ListView) findViewById(R.id.pageListView);

        listItems = new ArrayList<Pages>();



        Cursor res = myDB.getPageData(getIntent().getStringExtra("BOOK_ID"));

        Log.e(TAG, "onCreate: Intent book_id: " +getIntent().getStringExtra("BOOK_ID") );
//        Cursor res = myDB.getPageAllData();
        if (res.getCount() == 0){

            return;
        }

        while (res.moveToNext()){


            String page_id = res.getString(0);
            String page_title = res.getString(1);
            String page_audio = res.getString(2);
            byte[] page_image = res.getBlob(3);
            String book_id = res.getString(4);

            Log.e(TAG, "onCreate: page_id: " + page_id );
            Log.e(TAG, "onCreate: page_title: " + page_title );
            Log.e(TAG, "onCreate: page_audio: " + page_audio );
            Log.e(TAG, "onCreate: book_id: " + book_id );


            Pages pages = new Pages(book_id, page_id, page_title, page_audio, page_image);

            listItems.add(pages);

        }

        PageAdapter pageAdapter = new PageAdapter(this,listItems);
        listView.setAdapter(pageAdapter);

    }


}
