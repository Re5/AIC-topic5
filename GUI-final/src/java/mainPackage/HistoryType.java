package mainPackage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dominik
 */
public class HistoryType {
    private int id;
    private String[] searched;
    private byte[] pdf;
    private String time;

    public HistoryType(int id, String[] searched, String time, byte[] pdf) {
        this.searched = searched;
        this.time = time;
        this.pdf = pdf;
        this.id = id;
    }       

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getSearched() {
        return searched;
    }

    public void setSearched(String[] searched) {
        this.searched = searched;
    }        

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    

}
