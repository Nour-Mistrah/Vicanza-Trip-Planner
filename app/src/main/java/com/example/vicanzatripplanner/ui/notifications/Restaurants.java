package com.example.vicanzatripplanner.ui.notifications;

public class Restaurants {
    private int Rid;
    private String Rname;
    private String Rlocation;
    private double Rprice;
    private String RimageUrl;

    public Restaurants(int Rid, String Rname, String Rlocation, double Rprice, String RimageUrl) {
        this.Rid = Rid;
        this.Rname = Rname;
        this.Rlocation = Rlocation;
        this.Rprice = Rprice;
        this.RimageUrl = RimageUrl;
    }

    public int getRId() { return Rid; }
    public String getRName() { return Rname; }
    public String getRLocation() { return Rlocation; }
    public double getRPrice() { return Rprice; }
    public String getRImageUrl() { return RimageUrl; }
}
