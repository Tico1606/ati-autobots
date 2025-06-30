package com.autobots.automanager.model;

import org.springframework.stereotype.Component;
import com.autobots.automanager.entities.Address;

@Component
public class AddressUpdater {
  private final NullStringChecker checker = new NullStringChecker();

  public void update(Address target, Address source) {
    if (source != null) {
      if (!checker.isNullOrEmpty(source.getState())) {
        target.setState(source.getState());
      }
      if (!checker.isNullOrEmpty(source.getCity())) {
        target.setCity(source.getCity());
      }
      if (!checker.isNullOrEmpty(source.getDistrict())) {
        target.setDistrict(source.getDistrict());
      }
      if (!checker.isNullOrEmpty(source.getStreet())) {
        target.setStreet(source.getStreet());
      }
      if (!checker.isNullOrEmpty(source.getNumber())) {
        target.setNumber(source.getNumber());
      }
      if (!checker.isNullOrEmpty(source.getAdditionalInfo())) {
        target.setAdditionalInfo(source.getAdditionalInfo());
      }
    }
  }
}
