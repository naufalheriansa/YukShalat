package com.example.user.yukshalat.responseApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseApi{

	@SerializedName("country")
	private String country;

	@SerializedName("city")
	private String city;

	@SerializedName("title")
	private String title;

	@SerializedName("state")
	private String state;

	@SerializedName("items")
	private List<ItemsItem> items;

	public String getCountry(){

		return country;
	}

	public String getCity(){

		return city;
	}

	public String getTitle(){

		return title;
	}

	public String getState(){

		return state;
	}


	public List<ItemsItem> getItems(){

		return items;
	}
}