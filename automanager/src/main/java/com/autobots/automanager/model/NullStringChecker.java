package com.autobots.automanager.model;

public class NullStringChecker {

  public boolean isNullOrEmpty(String value) {
    return value == null || value.isBlank();
  }
}
