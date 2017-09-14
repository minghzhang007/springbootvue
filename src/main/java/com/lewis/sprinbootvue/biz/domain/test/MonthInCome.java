package com.lewis.sprinbootvue.biz.domain.test;

public class MonthInCome {

    private int month;

    private int money;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public MonthInCome(int month, int money) {
        this.month = month;
        this.money = money;
    }

    @Override
    public String toString() {
        return "MonthInCome{" +
                "month=" + month +
                ", money=" + money +
                '}';
    }
}
