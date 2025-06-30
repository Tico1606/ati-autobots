package com.autobots.automanager.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PhoneNumber {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String areaCode;

  @Column
  private String number;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  @JsonBackReference
  private Customer owner;
}
