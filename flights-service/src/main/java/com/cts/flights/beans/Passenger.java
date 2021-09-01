package com.cts.flights.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passenger {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	
	public Passenger() {
		super();
	}

	public Passenger(Long id, String firstName, String lastName, String gender, int age) {
		super();
		this.id = id;
		this.firstName = firstName.toUpperCase();
		this.lastName = lastName.toUpperCase();
		this.gender = gender.toUpperCase();
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.toUpperCase();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.toUpperCase();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender.toUpperCase();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", age=" + age + "]";
	}
}
