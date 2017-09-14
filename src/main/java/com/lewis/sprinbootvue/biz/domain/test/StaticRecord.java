package com.lewis.sprinbootvue.biz.domain.test;

import com.lewis.sprinbootvue.biz.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class StaticRecord {

    private String year;

    private String platform;

    private String timeType;

    private int januaryMoney;

    private int februaryMoney;

    private int marchMoney;

    private int aprilMoney;

    private int mayMoney;

    private int juneMoney;

    private int julyMoney;

    private int augustMoney;

    private int septemberMoney;

    private int octoberMoney;

    private int novemberMoney;

    private int decemberMoney;

    private int accumulateMoney;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public int getJanuaryMoney() {
        return januaryMoney;
    }

    public void setJanuaryMoney(int januaryMoney) {
        this.januaryMoney = januaryMoney;
    }

    public int getFebruaryMoney() {
        return februaryMoney;
    }

    public void setFebruaryMoney(int februaryMoney) {
        this.februaryMoney = februaryMoney;
    }

    public int getMarchMoney() {
        return marchMoney;
    }

    public void setMarchMoney(int marchMoney) {
        this.marchMoney = marchMoney;
    }

    public int getAprilMoney() {
        return aprilMoney;
    }

    public void setAprilMoney(int aprilMoney) {
        this.aprilMoney = aprilMoney;
    }

    public int getMayMoney() {
        return mayMoney;
    }

    public void setMayMoney(int mayMoney) {
        this.mayMoney = mayMoney;
    }

    public int getJuneMoney() {
        return juneMoney;
    }

    public void setJuneMoney(int juneMoney) {
        this.juneMoney = juneMoney;
    }

    public int getJulyMoney() {
        return julyMoney;
    }

    public void setJulyMoney(int julyMoney) {
        this.julyMoney = julyMoney;
    }

    public int getAugustMoney() {
        return augustMoney;
    }

    public void setAugustMoney(int augustMoney) {
        this.augustMoney = augustMoney;
    }

    public int getSeptemberMoney() {
        return septemberMoney;
    }

    public void setSeptemberMoney(int septemberMoney) {
        this.septemberMoney = septemberMoney;
    }

    public int getOctoberMoney() {
        return octoberMoney;
    }

    public void setOctoberMoney(int octoberMoney) {
        this.octoberMoney = octoberMoney;
    }

    public int getNovemberMoney() {
        return novemberMoney;
    }

    public void setNovemberMoney(int novemberMoney) {
        this.novemberMoney = novemberMoney;
    }

    public int getDecemberMoney() {
        return decemberMoney;
    }

    public void setDecemberMoney(int decemberMoney) {
        this.decemberMoney = decemberMoney;
    }

    public int getAccumulateMoney() {
        return accumulateMoney;
    }

    public void setAccumulateMoney(int accumulateMoney) {
        this.accumulateMoney = accumulateMoney;
    }



    public static void main(String[] args) {
        List<StaticRecord> staticRecords = getStaticRecords();
        System.out.println(JsonUtil.toString(staticRecords));
    }

    public static List<StaticRecord> getStaticRecords() {
        List<StaticRecord> staticRecords = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            StaticRecord record = new StaticRecord();
            record.setYear("2017年");
            setPlatformAndTimeType(i, record);
            record.setJanuaryMoney(100);
            record.setFebruaryMoney(200);
            record.setMarchMoney(300);
            record.setAprilMoney(400);
            record.setMayMoney(500);
            record.setJuneMoney(600);
            record.setJulyMoney(700);
            record.setAugustMoney(800);
            record.setSeptemberMoney(900);
            record.setOctoberMoney(1000);
            record.setNovemberMoney(1100);
            record.setDecemberMoney(1200);
            record.setAccumulateMoney(7800);
            staticRecords.add(record);
        }
        return staticRecords;
    }

    private static void setPlatformAndTimeType(int i, StaticRecord record) {
        if (i <= 5) {
            record.setPlatform("IOS");
            if (i == 1){
                record.setTimeType("6元/7天");
            }else if(i ==2){
                record.setTimeType("12元/14天");
            }else if(i ==3){
               record.setTimeType("25元/30天");
            }else if(i==4){
                record.setTimeType("298元/365天");
            }else{
                record.setTimeType("IAP分成扣除");
            }
        }else if(i<= 9){
            record.setPlatform("Android");
            if(i == 6){
                record.setTimeType("1元/1天");
            }else if(i ==7){
                record.setTimeType("6元/7天");
            }else if(i ==8){
                record.setTimeType("25元/30天");
            }else{
                record.setTimeType("298元/365天");
            }
        }else if (i <=11){
            record.setPlatform("双平台");
            if(i == 10){
                record.setTimeType("单月累计收入");
            }else{
                record.setTimeType("单月结算收入");
            }
        }
    }
}
