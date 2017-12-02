package com.ravipatel.childbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nikpatel on 28/11/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DataBaseHelper";

    private static final String DB_NAME = "childBook";
    private static final int DB_VERSION = 1;

    private static final String TBL_BOOK = "tbl_book";
    private static final String BOOK_ID = "book_id";
    private static final String BOOK_TITLE = "book_title";
    private static final String BOOK_IMAGE = "book_image";

    private static final String TBL_PAGES = "pages";
    private static final String PAGES_ID = "pages_id";
    private static final String PAGES_TEXT = "text";
    private static final String PAGES_AUDIO = "audio";
    private static final String PAGES_IMAGE = "image";




    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlBOOK = "CREATE TABLE "+TBL_BOOK+"("+PAGES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +BOOK_TITLE+" VARCHAR,"
                +BOOK_IMAGE+" BLOG"+");";

        String sqlPAGES = "CREATE TABLE "+TBL_PAGES+"("+PAGES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +PAGES_TEXT+" VARCHAR," +PAGES_AUDIO+" VARCHAR," +PAGES_IMAGE+" BLOG,"
                +BOOK_ID+" INTEGER, FOREIGN KEY("+BOOK_ID+") REFERENCES "+TBL_BOOK+"("+BOOK_ID+"));";

        db.execSQL(sqlBOOK);
        db.execSQL(sqlPAGES);

    }


    public boolean addBook(String bookName, byte[] image){

        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BOOK_TITLE,bookName);
        contentValues.put(BOOK_IMAGE,image);

        db.insert(TBL_BOOK, null,contentValues);
        db.close();


        return true;
    }

    public Cursor getId(String bookTitle){

        SQLiteDatabase db =getWritableDatabase();

        //Cursor res = db.rawQuery("SELECT "+BOOK_ID+" FROM "+TBL_BOOK+" WHERE " +BOOK_TITLE+" = "+bookTitle,null);

        Cursor res = db.rawQuery("select * from "+TBL_BOOK+" WHERE "+BOOK_TITLE +" = '" + bookTitle+"'",null);


        return res;
    }

    public boolean addPage(String pageText, byte[] pageImage, String pageAudio, String booId){


        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PAGES_TEXT,pageText);
        contentValues.put(PAGES_IMAGE,pageImage);
        contentValues.put(PAGES_AUDIO,pageAudio);
        contentValues.put(BOOK_ID, booId);
        Log.e(TAG, "addPage: " + booId );
        db.insert(TBL_PAGES, null, contentValues);
        db.close();

        return true;

    }

    public Cursor getPageData(String bookId){

        SQLiteDatabase db =getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TBL_PAGES+" WHERE "+BOOK_ID +" = '" + bookId+"'",null);

        return res;
    }

    public Cursor getPageDataByID(String pageId){

        SQLiteDatabase db =getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TBL_PAGES+" WHERE "+PAGES_ID +" = '" + pageId+"'",null);

        return res;
    }
    public Cursor getBookData(){

        SQLiteDatabase db =getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TBL_BOOK,null);

        return res;

    }

    public Cursor getPageAllData(){

        SQLiteDatabase db =getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TBL_PAGES,null);

        return res;

    }

    public void deleteBook(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TBL_BOOK);
        db.close();
    }

    public void deletePages(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TBL_PAGES);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlBOOK = "DROP TABLE IF EXISTS "+TBL_BOOK;
        String sqlPAGES = "DROP TABLE IF EXISTS "+TBL_PAGES;

        db.execSQL(sqlBOOK);
        db.execSQL(sqlPAGES);

        onCreate(db);
    }
}
