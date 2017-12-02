package com.ravipatel.childbook;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewListActivity extends AppCompatActivity {

    private static final String TAG = "ViewListActivity";
    private TextView pageTitle;
    private ImageView pageImage;
    DataBaseHelper db;
    byte[] page_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        Toast.makeText(this, getIntent().toString(), Toast.LENGTH_SHORT).show();

        db = new DataBaseHelper(this);

        pageImage = (ImageView) findViewById(R.id.pageImage);
        pageTitle = (TextView) findViewById(R.id.pageTitle);

        String page_id = getIntent().getStringExtra("PAGE_ID");

        Cursor res = db.getPageDataByID(page_id);
        if (res.getCount() == 0){

            return;
        }
        if (res.moveToFirst()){

            do {
                String page_title = res.getString(1);
                String page_audio = res.getString(2);
                byte[] page_image = res.getBlob(3);
                String book_id = res.getString(4);

                Bitmap bitmap = BitmapFactory.decodeByteArray(page_image,0,page_image.length);
                Log.e(TAG, "onCreate: BITMAP------------" +bitmap );
                pageImage.setImageBitmap(bitmap);
                Log.e(TAG, "onCreate: page_id: " + page_id );
                Log.e(TAG, "onCreate: page_title: " + page_title );
                Log.e(TAG, "onCreate: page_audio: " + page_audio );
                Log.e(TAG, "onCreate: book_id: " + book_id );

                Log.e(TAG, "onCreate: Image: " +page_image );
            }while (res.moveToNext());
        }
//        while (res.moveToFirst()) {
//
//            String page_title = res.getString(1);
//            String page_audio = res.getString(2);
//            byte[] page_image = res.getBlob(3);
//            String book_id = res.getString(4);
//
//            Log.e(TAG, "onCreate: page_id: " + page_id );
//            Log.e(TAG, "onCreate: page_title: " + page_title );
//            Log.e(TAG, "onCreate: page_audio: " + page_audio );
//            Log.e(TAG, "onCreate: book_id: " + book_id );
//
//            Log.e(TAG, "onCreate: Image: " +page_image );
////            Bitmap bitmap = BitmapFactory.decodeByteArray(page_image, 0, page_image.length);
////
////            pageImage.setImageBitmap(bitmap);
//        }


    }
}
