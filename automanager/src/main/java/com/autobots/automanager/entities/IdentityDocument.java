package com.autobots.automanager.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class IdentityDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String category;

  @Column(unique = true)
  private String value;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  @JsonBackReference
  private Customer owner;
}
