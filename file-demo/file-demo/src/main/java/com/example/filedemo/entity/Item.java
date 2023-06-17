package com.example.filedemo.entity;

public class Item {
	private Long id;
	private String name;
	private Category category;
	private String status;
	private int quantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(Long id, String name, Category category, String status, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.status = status;
		this.quantity = quantity;
	}
	
}
