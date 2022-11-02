package com.jamesgabbie.coffeeShop.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="contents")
public class Content {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User owner;
	private String welcomeMessage;
	private String ownerMessage;
	private String contactMessage;
	
	@OneToMany(mappedBy="forContent", fetch = FetchType.LAZY)
    private List<Item> items;
	
	@OneToMany(mappedBy="forContent", fetch = FetchType.LAZY)
    private List<Image> images;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
	public Content() {
		
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	public String getWelcomeMessage() {
		return welcomeMessage;
	}


	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}


	public String getOwnerMessage() {
		return ownerMessage;
	}


	public void setOwnerMessage(String ownerMessage) {
		this.ownerMessage = ownerMessage;
	}


	public String getContactMessage() {
		return contactMessage;
	}


	public void setContactMessage(String contactMessage) {
		this.contactMessage = contactMessage;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public List<Image> getImages() {
		return images;
	}


	public void setImages(List<Image> images) {
		this.images = images;
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


	public Content(User owner, String welcomeMessage, String ownerMessage, String contactMessage, List<Item> items,
			List<Image> images) {
		this.owner = owner;
		this.welcomeMessage = welcomeMessage;
		this.ownerMessage = ownerMessage;
		this.contactMessage = contactMessage;
		this.items = items;
		this.images = images;
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
