package com.example.vicanzatripplanner.ui.dashboard;

public class Places {

    private int Pid;
    private String Pname;
    private String Plocation;
    private double Pprice;
    private String PimageUrl;

    public Places(int Pid, String Pname, String Plocation, double Pprice, String PimageUrl) {
        this.Pid = Pid;
        this.Pname = Pname;
        this.Plocation = Plocation;
        this.Pprice = Pprice;
        this.PimageUrl = PimageUrl;
    }

    public int getPid() { return Pid; }
    public String getPname() { return Pname; }
    public String getPlocation() { return Plocation; }
    public double getPprice() { return Pprice; }
    public String getPimageUrl() { return PimageUrl; }
}