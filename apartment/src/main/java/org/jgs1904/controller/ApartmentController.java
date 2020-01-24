package org.jgs1904.controller;

import org.jgs1904.entity.Apartment;
import org.jgs1904.entity.Water_electricity_cost;
import org.jgs1904.service.ApartmentService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName ApartmentController
 * @Description TODO
 * @Author Jsyh_Li
 * @Since 2020/1/23 20:47
 */
public class ApartmentController {
    public void showCostOfOneMonth(){
        ArrayList<Water_electricity_cost> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎使用查看某月各账单消费排名系统");
        System.out.println("*******************************");
        System.out.println("请数入想要查看的年份：");
        String a = sc.next();
        System.out.println("请输入想要查看的月份：");
        String b = sc.next();
        sc.close();
        ApartmentService apartmentService = new ApartmentService();
        try {
            list = apartmentService.showCostOfOneMonth(a, b);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (list.size() >= 1) {
            System.out.println(a + "年" + b + "月各账单消费排名（降序）如下：");
            for (int i = 0; i < list.size(); i++) {
                int j = i + 1;
                System.out.println(j + ":" + list.get(i).toString());
            }
        }else {
            System.out.println(a + "年" + b + "月暂无消费排名");
        }
    }

    public void showCostOfYearByEveryapartment(){
        ArrayList<Apartment> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎使用查看某年各公寓消费排名系统");
        System.out.println("*******************************");
        System.out.println("请数入想要查看的年份：");
        String a = sc.next();
        sc.close();
        ApartmentService apartmentService = new ApartmentService();
        try {
            list = apartmentService.showCostOfYearByEveryapartment(a);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (list.size() >= 1) {
            System.out.println(a + "年各公寓消费排名（降序）如下：");
            for (int i = 0; i < list.size(); i++) {
                int j = i + 1;
                System.out.println(j + ":" + list.get(i).toString());
            }
        }else {
            System.out.println(a + "年暂无公寓消费排名");
        }
    }
}
