package com.jacksonleonardo.unpaper.model.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchDTO {
    private String wallet;
    private String startDate;
    private String endDate;


    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "wallet='" + wallet + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public static String formateddate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dtf);
    }
}
