package com.ravipatel.childbook;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class ViewListActivity extends AppCompatActivity {

    private static final String TAG = "ViewListActivity";
    private TextView pageTitle;
    private ImageView pageImage;
    private Button btnPlay;
    DataBaseHelper db;
    byte[] page_image;
    Bitmap bmp;

    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    String name,audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);


        db = new DataBaseHelper(this);

        pageImage = (ImageView) findViewById(R.id.pageImage);
        pageTitle = (TextView) findViewById(R.id.pageTitle);
        btnPlay = (Button) findViewById(R.id.btnPlay);


//        byte[] byteArray = getIntent().getByteArrayExtra("image");
//        String title = getIntent().getStringExtra("text");
//        audio = getIntent().getStringExtra("audio");
//
//        pageTitle.setText(title);
//
//        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//        pageImage.setImageBitmap(bmp);


        Cursor res = db.getPageDataByID(getIntent().getStringExtra("id"));
        if (res.getCount() == 0){

            return;
        }
//
//        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
//            buffer.append("id: " +res.getString(0)+"\n");
//            buffer.append("name: "+res.getString(1)+"\n");
            name = res.getString(1);
            page_image = res.getBlob(3);
            audio = res.getString(2);

            Log.e(TAG, "onCreate: ----------- name --------" +name );

        }

        pageTitle.setText(name);

        bmp = BitmapFactory.decodeByteArray(page_image, 0, page_image.length);
        pageImage.setImageBitmap(bmp);


        File yourAppDir = new File(Environment.getExternalStorageDirectory()+File.separator+"ChilBook");

        if(!yourAppDir.exists() && !yourAppDir.isDirectory())
        {
            // create empty directory
            if (yourAppDir.mkdirs())
            {
                Log.i("CreateDir","App dir created");
            }
            else
            {
                Log.w("CreateDir","Unable to create app dir!");
            }
        }
        else

        {
            Log.i("CreateDir","App dir already exists");
        }
        outputFile = yourAppDir + "/"+audio+".3gp";;

        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);


        playAudio();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

    }

    private void playAudio() {




        MediaPlayer m = new MediaPlayer();
        try {
            m.setDataSource(outputFile);
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            m.prepare();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        m.start();

    }
}
