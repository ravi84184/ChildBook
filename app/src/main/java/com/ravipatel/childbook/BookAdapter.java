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
import android.widget.Toast;

import java.util.List;

/**
 * Created by nikpatel on 28/11/17.
 */

public class BookAdapter extends BaseAdapter {

    private static final String TAG = "BookAdapter";

    Context context;
    List<book> itemsList;

    public BookAdapter(Context context, List<book> itemsList) {
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

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){

            convertView = inflater.inflate(R.layout.book_list_layout, null);

            holder = new ViewHolder();
            holder.book_title = convertView.findViewById(R.id.book_title);
            holder.book_image = convertView.findViewById(R.id.book_image);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        final book book = (book) getItem(position);

        Log.e(TAG, "getView: " + book.getBook_name() );

        holder.book_title.setText(book.getBook_name());

        final String book_id = book.getBook_id();

        byte[] image = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        holder.book_image.setImageBitmap(bitmap);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, book.getBook_id(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ListPageActivity.class);
                intent.putExtra("BOOK_ID",book_id);
                context.startActivity(intent);

            }
        });


        return convertView;
    }
    private class ViewHolder{

        ImageView book_image;
        TextView book_title;

    }
}
