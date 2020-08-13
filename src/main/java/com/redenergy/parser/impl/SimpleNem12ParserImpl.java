package com.redenergy.parser.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.redenergy.exceptions.InvalidContentException;
import com.redenergy.model.EnergyUnit;
import com.redenergy.model.MeterRead;
import com.redenergy.model.MeterVolume;
import com.redenergy.model.Quality;
import com.redenergy.parser.SimpleNem12Parser;

public class SimpleNem12ParserImpl implements SimpleNem12Parser {
	
	// for strict parsing, uuuu is year instead of year-of-era (which is yyyy)
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuuMMdd");

	private static final String FILE_START_FLAG = "100";
	private static final String FILE_END_FLAG = "900";
	private static final String CSV_DELIMETER = ",";
	
	//Meter reading block start flag
	private static final String MRB_START_FLAG = "200";

	//Meter reading record start flag
	private static final String MR_RECORD_START_FLAG = "300";

	@Override
	public Collection<MeterRead> parseSimpleNem12(File simpleNem12File) throws InvalidContentException, IOException {
		
		List<MeterRead> list = new ArrayList<>();

		List<String[]> dataLines = extractDataLines(simpleNem12File);
		
		int firstIndex = 0;
		int secondIndex = 1;
		int thirdIndex = 2;
		int forthIndex = 3;
		
		for (int index = 0 ; index < dataLines.size() ; index++) {
			
			String line [] = dataLines.get(index);
			
			if (line != null && line.length >= 3) {

				if (MRB_START_FLAG.equals(line[firstIndex])) {

					if (validateMeterReadBlockRecord(line)) {
						
						MeterRead meterRead = new MeterRead(line[secondIndex], EnergyUnit.valueOf(line[thirdIndex]));
						
						if (list.contains(meterRead)) {
							throw new InvalidContentException("Duplicate NMI found: " + Arrays.asList(line));
						}
						
						list.add(meterRead);
					} else {
						//should not happen
					}

				} else if (MR_RECORD_START_FLAG.equals(line[firstIndex])) {

					if (list.isEmpty()) {
						throw new InvalidContentException("Orphan volume record found without any NMI: " + Arrays.asList(line));
					} else if (validateVolumneRecord(line)) {
						list.get(list.size() - 1).appendVolume(LocalDate.parse(line[secondIndex], dateFormatter.withResolverStyle(ResolverStyle.STRICT)), new MeterVolume(new BigDecimal(line[thirdIndex]), Quality.valueOf(line[forthIndex])));
					} else {
						//should not happen
					}
				} else {
					throw new InvalidContentException("Invalid content: " + Arrays.asList(line));
				}

			} else {
				throw new InvalidContentException("Invalid content: " + Arrays.asList(line));
			}
			
		}

		return list;

	}
	
	private boolean validateMeterReadBlockRecord(String [] meterReadBlockRecord) throws InvalidContentException {

		if (!meterReadBlockRecord[1].matches("\\d{10}")) {
			throw new InvalidContentException("Not a valid meter read block record: " + Arrays.asList(meterReadBlockRecord));
		}
		
		
		if (!EnergyUnit.contains(meterReadBlockRecord[2])) {
			throw new InvalidContentException("Not a valid meter read block record: " + Arrays.asList(meterReadBlockRecord));
		}

		//meter read block record is valid
		return true;
	}

	private boolean validateVolumneRecord(String [] volumnRecord) throws InvalidContentException {
		
		if (volumnRecord.length != 4) {
			throw new InvalidContentException("Not a valid volume record: " + Arrays.asList(volumnRecord));
		}
		
		try {
			LocalDate.parse(volumnRecord[1], dateFormatter.withResolverStyle(ResolverStyle.STRICT));
		} catch (DateTimeParseException e) {
			throw new InvalidContentException("Not a valid volume record: " + Arrays.asList(volumnRecord));
		}
		
		try {
			new BigDecimal(volumnRecord[2]);
		} catch (NumberFormatException e) {
			throw new InvalidContentException("Not a valid volume record: " + Arrays.asList(volumnRecord));
		}
		
		if (!Quality.contains(volumnRecord[3])) {
			throw new InvalidContentException("Not a valid volume record: " + Arrays.asList(volumnRecord));
		}

		//volume record is valid
		return true;
	}

	private static List<String[]> extractDataLines(File simpleNem12File) throws InvalidContentException, IOException {
		
		if (Files.lines(Paths.get(simpleNem12File.getPath()))
				.filter(line -> line == null || line.trim().isEmpty()).count() > 0) {
			throw new InvalidContentException("Found empty lines in the file: " + simpleNem12File.getAbsolutePath());
		}
		
		List<String> allLines = Files.lines(Paths.get(simpleNem12File.getPath()))
				.collect(Collectors.toList());
		
		if (allLines == null 
				|| allLines.isEmpty() 
				|| !FILE_START_FLAG.equals(allLines.get(0)) 
				|| !FILE_END_FLAG.equals(allLines.get(allLines.size() - 1))) {
			throw new InvalidContentException("File contains invalid content: " + simpleNem12File.getAbsolutePath());
		}
		
		
		List<String []> dataLines = allLines.stream()
											.filter(line -> (!FILE_START_FLAG.equals(line) && !FILE_END_FLAG.equals(line)))
											.map(line -> line.split(CSV_DELIMETER))
											.collect(Collectors.toList());

		if (dataLines == null || dataLines.isEmpty()) {
			throw new InvalidContentException("File contains no content to process: " + simpleNem12File.getAbsolutePath());
		}
		
		return dataLines;
	}
}
