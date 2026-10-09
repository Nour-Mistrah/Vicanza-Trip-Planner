package com.example.vicanzatripplanner;


public class PackageItem {
    private int Packageid;
    private String Packagename;
    private String Packagelocation;
    private double Packageprice;
    private String Packageimage_url;

    public PackageItem(int Packageid, String Packagename, String Packagelocation, double Packageprice, String Packageimage_url) {
        this.Packageid = Packageid;
        this.Packagename = Packagename;
        this.Packagelocation = Packagelocation;
        this.Packageprice = Packageprice;
        this.Packageimage_url = Packageimage_url;
    }

    public int getId() { return Packageid; }
    public String getPackagename() { return Packagename; }
    public String getPackagelocation() { return Packagelocation; }
    public double getPackageprice() { return Packageprice; }
    public String getPackageimage_url() { return Packageimage_url; }
}