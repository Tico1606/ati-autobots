package com.autobots.automanager.model;

import org.springframework.stereotype.Service;
import com.autobots.automanager.entities.IdentityDocument;

import java.util.List;

@Service
public class IdentityDocumentUpdater {
  private final NullStringChecker checker = new NullStringChecker();

  public void update(IdentityDocument target, IdentityDocument source) {
    if (source != null) {
      if (!checker.isNullOrEmpty(source.getCategory())) {
        target.setCategory(source.getCategory());
      }
      if (!checker.isNullOrEmpty(source.getValue())) {
        target.setValue(source.getValue());
      }
    }
  }

  public void update(List<IdentityDocument> targetList, List<IdentityDocument> sourceList) {
    for (IdentityDocument source : sourceList) {
      for (IdentityDocument target : targetList) {
        if (source.getId() != null && source.getId().equals(target.getId())) {
          update(target, source);
        }
      }
    }
  }
}
