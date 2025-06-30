package com.autobots.automanager.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = true)
  private String state;

  @Column(nullable = false)
  private String city;

  @Column(nullable = true)
  private String district;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String number;

  @Column(nullable = true)
  private String postalCode;

  @Column(nullable = true)
  private String additionalInfo;

  @OneToOne
  @JoinColumn(name = "cliente_id")
  @JsonBackReference
  private Customer owner;
}
