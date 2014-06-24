package application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min=3, max=30)
	private String first_name;
	
	@Column
	@Size(min=3, max=30)
	private String last_name;
	
	@Column
	@NotNull
	@Email
	private String email;
	
	@Column
	@NotNull
	@Size(min = 5, message="Must be atleast 5 characters long")
	private String password;
	
	@Transient
	private String confirm_password;
	

	@AssertTrue(message = "Passwords do not match!")
	private boolean isPasswordValid() {
		return this.password.equals(this.confirm_password);
	}
	
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")	
	private Timestamp created_at;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Timestamp updated_at;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
}
