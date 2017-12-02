package com.ravipatel.childbook;

/**
 * Created by nikpatel on 28/11/17.
 */

public class book {


    String  book_id;
    String book_name;
    byte[] image;

    public book() {
    }

    public book(String book_id, String book_name, byte[] image) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.image = image;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
