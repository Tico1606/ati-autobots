package com.autobots.automanager.model;

import org.springframework.stereotype.Component;
import com.autobots.automanager.entities.Customer;

@Component
public class CustomerUpdater {
  private final NullStringChecker checker = new NullStringChecker();
  private final AddressUpdater addressUpdater = new AddressUpdater();
  private final IdentityDocumentUpdater documentUpdater = new IdentityDocumentUpdater();
  private final PhoneNumberUpdater phoneUpdater = new PhoneNumberUpdater();

  private void updateBasicInfo(Customer target, Customer source) {
    if (!checker.isNullOrEmpty(source.getFullName())) {
      target.setFullName(source.getFullName());
    }
    if (!checker.isNullOrEmpty(source.getSocialName())) {
      target.setSocialName(source.getSocialName());
    }
    if (source.getBirthDate() != null) {
      target.setBirthDate(source.getBirthDate());
    }
    if (source.getCreatedAt() != null) {
      target.setCreatedAt(source.getCreatedAt());
    }
  }

  public void update(Customer target, Customer source) {
    updateBasicInfo(target, source);
    if (target.getAddress() != null && source.getAddress() != null) {
      addressUpdater.update(target.getAddress(), source.getAddress());
    }
    documentUpdater.update(target.getDocuments(), source.getDocuments());
    phoneUpdater.update(target.getPhones(), source.getPhones());
  }
}
