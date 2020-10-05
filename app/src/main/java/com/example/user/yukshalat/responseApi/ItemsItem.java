package com.example.user.yukshalat.responseApi;

import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("asr")
	private String asr;

	@SerializedName("isha")
	private String isha;

	@SerializedName("shurooq")
	private String shurooq;

	@SerializedName("date_for")
	private String dateFor;

	@SerializedName("dhuhr")
	private String dhuhr;

	@SerializedName("fajr")
	private String fajr;

	@SerializedName("maghrib")
	private String maghrib;

	public String getAsr(){
		return asr;
	}

	public String getIsha(){
		return isha;
	}

	public String getShurooq(){
		return shurooq;
	}

	public String getDhuhr(){
		return dhuhr;
	}

	public String getFajr(){
		return fajr;
	}

	public String getMaghrib(){
		return maghrib;
	}
}