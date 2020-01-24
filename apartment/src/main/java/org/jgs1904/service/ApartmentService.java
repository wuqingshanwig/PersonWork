package org.jgs1904.service;

import org.jgs1904.dao.ApartmentDao;
import org.jgs1904.entity.Apartment;
import org.jgs1904.entity.Water_electricity_cost;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @ClassName ApartmentService
 * @Description TODO
 * @Author Jsyh_Li
 * @Since 2020/1/23 20:48
 */
public class ApartmentService {

    public ArrayList<Water_electricity_cost> showCostOfOneMonth(String a, String b) throws SQLException, ParseException {
        ArrayList<Water_electricity_cost> list = new ArrayList<>();
        ApartmentDao apartmentDao = new ApartmentDao();
        list = apartmentDao.selectList(new Water_electricity_cost(), a, b);
        return list;
    }

    public ArrayList<Apartment> showCostOfYearByEveryapartment(String a) throws SQLException, ParseException {
        ArrayList<Apartment> list = new ArrayList<>();
        ApartmentDao apartmentDao = new ApartmentDao();
        list = apartmentDao.selectOtherList(new Apartment(), a);
        return list;
    }
}


