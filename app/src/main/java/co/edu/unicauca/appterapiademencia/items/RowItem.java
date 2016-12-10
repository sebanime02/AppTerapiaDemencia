package co.edu.unicauca.appterapiademencia.items;

/**
 * Created by ENF on 27/10/2016.
 */


public class RowItem {
    private int imageId;
    private String title;


    public RowItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;

    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }
}
