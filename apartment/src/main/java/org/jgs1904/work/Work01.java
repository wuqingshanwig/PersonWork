package org.jgs1904.work;

import org.jgs1904.controller.ApartmentController;
import org.jgs1904.util.EntityGenerator;
import org.junit.Test;

/**
 * @ClassName Work01
 * @Description TODO
 * @Author Jsyh_Li
 * @Since 2020/1/23 20:09
 */
public class Work01 {
    @Test
    public void test01(){
        EntityGenerator entityGenerator = new EntityGenerator();
       // entityGenerator.createEntity("apartment");
       // entityGenerator.createEntity("resident");
        entityGenerator.createEntity("water_electricity_cost");

    }

    public static void main(String[] args) {
        ApartmentController apartmentController = new ApartmentController();
        apartmentController.showCostOfOneMonth();
    }
}
