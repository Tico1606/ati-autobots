package com.autobots.automanager.entities;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String fullName;

  @Column
  private String socialName;

  @Column
  private Date birthDate;

  @Column
  private Date createdAt;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<IdentityDocument> documents = new ArrayList<>();

  @OneToOne(mappedBy = "owner", cascade = CascadeType.PERSIST, orphanRemoval = true)
  @JsonManagedReference
  private Address address;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<PhoneNumber> phones = new ArrayList<>();
}
