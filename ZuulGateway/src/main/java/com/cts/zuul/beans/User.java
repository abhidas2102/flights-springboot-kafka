package com.cts.zuul.beans;
import java.util.*;
 
import javax.persistence.*;
 
@Entity
@Table(name = "users")
public class User {
 
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String username;
    private String password;
    private long mobile;
    private String email;
    private boolean enabled;
     
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<BookingDetails> bookingDetails;

    public User() {
		super();
	}

	public User(Long id, String username, String password, long mobile, String email, boolean enabled, Set<Role> roles,
			List<BookingDetails> bookingDetails) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.enabled = enabled;
		this.roles = roles;
		this.bookingDetails = bookingDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", mobile=" + mobile
				+ ", email=" + email + ", enabled=" + enabled + ", roles=" + roles + ", bookingDetails="
				+ bookingDetails + "]";
	}
}