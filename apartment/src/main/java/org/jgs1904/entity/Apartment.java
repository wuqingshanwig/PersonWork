package org.jgs1904.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Apartment {
    private Integer id;
    private String type;
    private Double rent;
    private Integer limit_resident_num;
    private Date build_date;

    public Apartment() {

    }

    public Apartment(Integer id, String type, Double rent, Integer limit_resident_num, Date build_date) {
        this.id = id;
        this.type = type;
        this.rent = rent;
        this.limit_resident_num = limit_resident_num;
        this.build_date = build_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getLimit_resident_num() {
        return limit_resident_num;
    }

    public void setLimit_resident_num(Integer limit_resident_num) {
        this.limit_resident_num = limit_resident_num;
    }

    public Date getBuild_date() {
        return build_date;
    }

    public void setBuild_date(Date build_date) {
        this.build_date = build_date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(build_date);
        return  "公寓id=" + id +
                ", 型号='" + type + '\'' +
                ", 房租=" + rent +
                ", 限住人数=" + limit_resident_num +
                ", 建造日期=" + format;
    }
}