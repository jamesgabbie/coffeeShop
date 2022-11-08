package com.jamesgabbie.coffeeShop.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
//	LOGIN/REG
	private String name;
	private String email;
    private String password;
    @Transient
    private String passwordConfirmation;

// 	CONTENT
    @Column(nullable = false)
    @Size(max = 500, message="Exceeded Max Characters of 600")
    private String welcomeText = "";
    @Column(nullable = false)
    @Size(max = 500, message="Exceeded Max Characters of 600")
    private String contactText = "";
    @Column(nullable = false)
    @Size(max = 500, message="Exceeded Max Characters of 600")
    private String aboutText = "";
    @Column(nullable = true)
    @OneToMany(mappedBy="owner", fetch = FetchType.LAZY)
    private List<Item> items;
    
    @Column(nullable = true)
    @OneToMany(mappedBy="userImages", fetch = FetchType.LAZY)
    private List<Image> savedImages;
//  Records
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
	public User() {
		
	}

	
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}


	public String getWelcomeText() {
		return welcomeText;
	}


	public void setWelcomeText(String welcomeText) {
		this.welcomeText = welcomeText;
	}


	public String getContactText() {
		return contactText;
	}


	public void setContactText(String contactText) {
		this.contactText = contactText;
	}


	public String getAboutText() {
		return aboutText;
	}


	public void setAboutText(String aboutText) {
		this.aboutText = aboutText;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public List<Image> getSavedImages() {
		return savedImages;
	}


	public void setSavedImages(List<Image> savedImages) {
		this.savedImages = savedImages;
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


	public User(String name, String email, String password, String passwordConfirmation, String welcomeText,
			String contactText, String aboutText, List<Item> items, List<Image> savedImages) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.welcomeText = welcomeText;
		this.contactText = contactText;
		this.aboutText = aboutText;
		this.items = items;
		this.savedImages = savedImages;
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
