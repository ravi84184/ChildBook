package com.ravipatel.childbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by nikpatel on 02/12/17.
 */

public class PageAdapter extends BaseAdapter {

    private static final String TAG = "PageAdapter";

    Context context;
    List<Pages> itemsList;


    public PageAdapter(Context context, List<Pages> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }


    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemsList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){

            convertView = inflater.inflate(R.layout.page_list_layout, null);
            holder = new ViewHolder();
            holder.page_title = convertView.findViewById(R.id.pageTitle);
            holder.page_image = convertView.findViewById(R.id.pageImage);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Pages pages = (Pages) getItem(position);

        holder.page_title.setText(pages.getPageTitle());

        final byte[] image = pages.getPageImage();
        final Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        Log.e(TAG, "onClick: pages " +pages.getPageImage().toString() );

        holder.page_image.setImageBitmap(bitmap);

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, bStream);
        final byte[] byteArray = bStream.toByteArray();

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ViewListActivity.class);
                intent.putExtra("id", pages.pageId);
//                intent.putExtra("image",byteArray);
//                intent.putExtra("audio",pages.getPageAudio());
//                intent.putExtra("text",pages.getPageTitle());
                context.startActivity(intent);

            }
        });

        return convertView;
    }


    private class ViewHolder{

        ImageView page_image;
        TextView page_title;

    }
    public static String tempFileImage(Context context, Bitmap bitmap, String name) {

        File outputDir = context.getCacheDir();
        File imageFile = new File(outputDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(context.getClass().getSimpleName(), "Error writing file", e);
        }

        return imageFile.getAbsolutePath();
    }
}
