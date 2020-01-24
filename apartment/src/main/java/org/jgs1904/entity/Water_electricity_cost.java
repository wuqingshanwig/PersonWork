package org.jgs1904.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Water_electricity_cost {
	private Integer id;
	private Double water_cost;
	private Double electricity_cost;
	private String complaint_tel;
	private Date cost_time;
	private Integer apart_id;

	public Water_electricity_cost () {

	}

	public Water_electricity_cost (Integer id, Double water_cost, Double electricity_cost, String complaint_tel, Date cost_time, Integer apart_id) {
		this.id = id;
		this.water_cost = water_cost;
		this.electricity_cost = electricity_cost;
		this.complaint_tel = complaint_tel;
		this.cost_time = cost_time;
		this.apart_id = apart_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getWater_cost() {
		return water_cost;
	}

	public void setWater_cost(Double water_cost) {
		this.water_cost = water_cost;
	}

	public Double getElectricity_cost() {
		return electricity_cost;
	}

	public void setElectricity_cost(Double electricity_cost) {
		this.electricity_cost = electricity_cost;
	}

	public String getComplaint_tel() {
		return complaint_tel;
	}

	public void setComplaint_tel(String complaint_tel) {
		this.complaint_tel = complaint_tel;
	}

	public Date getCost_time() {
		return cost_time;
	}

	public void setCost_time(Date cost_time) {
		this.cost_time = cost_time;
	}

	public Integer getApart_id() {
		return apart_id;
	}

	public void setApart_id(Integer apart_id) {
		this.apart_id = apart_id;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(cost_time);
		return  "水电单id=" + id +
				", 水费=" + water_cost +
				", 电费=" + electricity_cost +
				", 举报电话='" + complaint_tel + '\'' +
				", 开单日期=" + format +
				", 公寓id=" + apart_id;
	}
}