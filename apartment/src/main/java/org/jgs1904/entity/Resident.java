package org.jgs1904.entity;

import java.util.Date;

public class Resident {
	private Integer id;
	private String real_name;
	private String user_name;
	private String password;
	private Integer tel_num;
	private Date move_into_date;
	private Double money;
	private Integer apart_id;

	public Resident () {

	}

	public Resident (Integer id, String real_name, String user_name, String password, Integer tel_num, Date move_into_date, Double money, Integer apart_id) {
		this.id = id;
		this.real_name = real_name;
		this.user_name = user_name;
		this.password = password;
		this.tel_num = tel_num;
		this.move_into_date = move_into_date;
		this.money = money;
		this.apart_id = apart_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTel_num() {
		return tel_num;
	}

	public void setTel_num(Integer tel_num) {
		this.tel_num = tel_num;
	}

	public Date getMove_into_date() {
		return move_into_date;
	}

	public void setMove_into_date(Date move_into_date) {
		this.move_into_date = move_into_date;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getApart_id() {
		return apart_id;
	}

	public void setApart_id(Integer apart_id) {
		this.apart_id = apart_id;
	}
}