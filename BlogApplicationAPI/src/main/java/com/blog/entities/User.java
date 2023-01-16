package com.blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usertable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@Column(name="username", nullable = false, length=20)
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="user", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id"))
	private Set<Role> roles=new HashSet<>();
	
}
