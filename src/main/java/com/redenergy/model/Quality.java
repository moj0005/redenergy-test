// Copyright Red Energy Limited 2017

package com.redenergy.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents meter read quality in SimpleNem12
 */
public enum Quality {

	A,
	E;

	private final static Set<String> values = new HashSet<String>(Quality.values().length);

	static {
		for(Quality quality: Quality.values())
			values.add(quality.name());
	}

	public static boolean contains( String value ){
		return values.contains(value);
	}


}
