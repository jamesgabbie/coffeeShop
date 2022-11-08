package com.jamesgabbie.coffeeShop.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="images")
public class Image {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String placement;
	private String fileName;
	private byte[] imgData;
	private Long imgSize;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userImages;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
	public Image() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPlacement() {
		return placement;
	}


	public void setPlacement(String placement) {
		this.placement = placement;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public byte[] getImgData() {
		return imgData;
	}


	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}


	public Long getImgSize() {
		return imgSize;
	}


	public void setImgSize(Long imgSize) {
		this.imgSize = imgSize;
	}


	public User getUserImages() {
		return userImages;
	}


	public void setUserImages(User userImages) {
		this.userImages = userImages;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Image(String placement, String fileName, byte[] imgData, Long imgSize, User userImages) {
		this.placement = placement;
		this.fileName = fileName;
		this.imgData = imgData;
		this.imgSize = imgSize;
		this.userImages = userImages;
	}


	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
