// Copyright Red Energy Limited 2017

package com.redenergy.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the KWH energy unit in SimpleNem12
 */
public enum EnergyUnit {

  KWH;
  
  private final static Set<String> values = new HashSet<String>(EnergyUnit.values().length);

  static {
      for(EnergyUnit energyUnit: EnergyUnit.values())
          values.add(energyUnit.name());
  }

  public static boolean contains( String value ){
      return values.contains(value);
  }

}
