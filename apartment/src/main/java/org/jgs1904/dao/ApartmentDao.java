package org.jgs1904.dao;

import org.jgs1904.entity.Apartment;
import org.jgs1904.entity.Water_electricity_cost;
import org.jgs1904.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @ClassName ApartmentDao
 * @Description TODO
 * @Author Jsyh_Li
 * @Since 2020/1/23 20:48
 */
public class ApartmentDao {
    public ArrayList<Water_electricity_cost> selectList(Water_electricity_cost water_electricity_cost, String a, String b) throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = JDBCUtil.getConnection();

        String sql = "select * from water_electricity_cost where cost_time like '" + a + "-" + b + "%' order by (water_cost+electricity_cost) desc";

        // 预编译sql
        PreparedStatement pstmt = con.prepareStatement(sql);
        // 执行sql
        ResultSet rs = pstmt.executeQuery();
        // 封装结果集
        ArrayList<Water_electricity_cost> list = new ArrayList<>();
        while (rs.next()) {
            water_electricity_cost = new Water_electricity_cost(rs.getInt("id"),
                    rs.getDouble("water_cost"),
                    rs.getDouble("electricity_cost"),
                    rs.getString("complaint_tel"),
                    rs.getString("cost_time") == null ? null : sdf.parse(rs.getString("cost_time")),
                    rs.getInt("apart_id"));
            list.add(water_electricity_cost);
        }
        // 关闭资源
        JDBCUtil.closeAll(con, pstmt, rs);
        return list;
    }

    public ArrayList<Apartment> selectOtherList(Apartment apartment, String a) throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = JDBCUtil.getConnection();

        String sql = "select apartment.* from water_electricity_cost,apartment where apartment.id=water_electricity_cost.apart_id and water_electricity_cost.cost_time like '" + a + "%'group by apartment.id\n" +
                "order by sum(water_electricity_cost.water_cost+water_electricity_cost.electricity_cost)\n" +
                "desc";

        // 预编译sql
        PreparedStatement pstmt = con.prepareStatement(sql);
        // 执行sql
        ResultSet rs = pstmt.executeQuery();
        // 封装结果集
        ArrayList<Apartment> list = new ArrayList<>();
        while (rs.next()) {
            apartment = new Apartment(rs.getInt("id"),
                    rs.getString("type"),
                    rs.getDouble("rent"),
                    rs.getInt("limit_resident_num"),
                    rs.getDate("build_date") == null ? null : sdf.parse(rs.getString("build_date")));
            list.add(apartment);
        }
        // 关闭资源
        JDBCUtil.closeAll(con, pstmt, rs);
        return list;
    }
}

