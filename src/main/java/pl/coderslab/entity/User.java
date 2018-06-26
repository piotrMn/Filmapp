package pl.coderslab.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import pl.coderslab.validator.LoginGroupVal;
import pl.coderslab.validator.NoSuchUser;
import pl.coderslab.validator.NoUserNameDuplicate;
import pl.coderslab.validator.Password;
import pl.coderslab.validator.RegisterGroupVal;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Pole nie może być puste!", groups = {RegisterGroupVal.class, LoginGroupVal.class})
	@Size(min = 5, max = 30, groups = {RegisterGroupVal.class})
	@NoUserNameDuplicate(groups = {RegisterGroupVal.class})
	@NoSuchUser(groups = {LoginGroupVal.class})
	private String username;

	@NotBlank(message = "Pole nie może być puste!", groups = {RegisterGroupVal.class})
	@Email(message = "Nieprawidłowy format adresu!", groups = {RegisterGroupVal.class})
	private String email;

	@Password(groups = {RegisterGroupVal.class})
	private String password;

	@CreationTimestamp
	private Timestamp created;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<URSlink> urs = new HashSet<URSlink>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	public Timestamp getCreated() {
		return created;
	}

	public void setCreated() {
		this.created = Timestamp.valueOf(LocalDateTime.now());
	}

	public Set<URSlink> getURS() {
		return urs;
	}

	public void setURS(Set<URSlink> urs) {
		this.urs = urs;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

}
