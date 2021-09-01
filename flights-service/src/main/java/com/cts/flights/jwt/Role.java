package com.cts.flights.jwt;
import javax.persistence.*;
 
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    private String name;
    
    public Role() {
		super();
	}
    
	public Role(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
        return id;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Integer id) {
		this.id = id;
	}   
}