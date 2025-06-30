package com.autobots.automanager.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.PhoneNumber;

@Component
public class PhoneNumberUpdater {
  private final NullStringChecker checker = new NullStringChecker();

  public void update(PhoneNumber target, PhoneNumber source) {
    if (source != null) {
      if (!checker.isNullOrEmpty(source.getAreaCode())) {
        target.setAreaCode(source.getAreaCode());
      }
      if (!checker.isNullOrEmpty(source.getNumber())) {
        target.setNumber(source.getNumber());
      }
    }
  }

  public void update(List<PhoneNumber> targetList, List<PhoneNumber> sourceList) {
    for (PhoneNumber source : sourceList) {
      for (PhoneNumber target : targetList) {
        if (source.getId() != null && source.getId().equals(target.getId())) {
          update(target, source);
        }
      }
    }
  }
}
