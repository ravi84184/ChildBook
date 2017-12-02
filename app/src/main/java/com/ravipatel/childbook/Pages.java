package com.ravipatel.childbook;

/**
 * Created by nikpatel on 02/12/17.
 */

public class Pages {

    String bookId, pageId, pageTitle, pageAudio;
    byte[] pageImage;

    public Pages() {
    }

    public Pages(String bookId, String pageId, String pageTitle, String pageAudio, byte[] pageImage) {
        this.bookId = bookId;
        this.pageId = pageId;
        this.pageTitle = pageTitle;
        this.pageAudio = pageAudio;
        this.pageImage = pageImage;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageAudio() {
        return pageAudio;
    }

    public void setPageAudio(String pageAudio) {
        this.pageAudio = pageAudio;
    }

    public byte[] getPageImage() {
        return pageImage;
    }

    public void setPageImage(byte[] pageImage) {
        this.pageImage = pageImage;
    }
}
