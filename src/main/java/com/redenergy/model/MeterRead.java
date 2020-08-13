// Copyright Red Energy Limited 2017

package com.redenergy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.SortedMap;
import java.util.TreeMap;

import com.redenergy.exceptions.InvalidContentException;

/**
 * Represents a meter read, corresponds to RecordType 200 in SimpleNem12
 *
 * The volumes property is a map that holds the date and associated <code>MeterVolume</code> on that date, values come from RecordType 300.
 */
public class MeterRead {

	private String nmi;
	private EnergyUnit energyUnit;
	private SortedMap<LocalDate, MeterVolume> volumes = new TreeMap<>();

	public MeterRead(String nmi, EnergyUnit energyUnit) {
		this.nmi = nmi;
		this.energyUnit = energyUnit;
	}

	public String getNmi() {
		return nmi;
	}

	public void setNmi(String nmi) {
		this.nmi = nmi;
	}

	public EnergyUnit getEnergyUnit() {
		return energyUnit;
	}

	public void setEnergyUnit(EnergyUnit energyUnit) {
		this.energyUnit = energyUnit;
	}

	public SortedMap<LocalDate, MeterVolume> getVolumes() {
		return volumes;
	}

	public void setVolumes(SortedMap<LocalDate, MeterVolume> volumes) {
		this.volumes = volumes;
	}

	MeterVolume getMeterVolume(LocalDate localDate) {
		return volumes.get(localDate);
	}

	public void appendVolume(LocalDate localDate, MeterVolume meterVolume) throws InvalidContentException {
		
		if (volumes.keySet().contains(localDate)) {
			throw new InvalidContentException("Duplicate date found (" + localDate + ") for the same NMI: " + this.getNmi());
		}
		
		volumes.put(localDate, meterVolume);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nmi == null) ? 0 : nmi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeterRead other = (MeterRead) obj;
		if (nmi == null) {
			if (other.nmi != null)
				return false;
		} else if (!nmi.equals(other.nmi))
			return false;
		return true;
	}

	public BigDecimal getTotalVolume() {
		return volumes.values().stream()
				.map(mr -> mr.getVolume())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String toString() {
		return "MeterRead [nmi=" + nmi + ", energyUnit=" + energyUnit + ", volumes=" + volumes + "]";
	}


}
