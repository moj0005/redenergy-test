package com.redenergy;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import com.redenergy.exceptions.InvalidContentException;
import com.redenergy.model.MeterRead;
import com.redenergy.parser.impl.SimpleNem12ParserImpl;

public class Driver {

	public static void main(String argv[]) throws InvalidContentException, IOException, URISyntaxException {
		
		Driver driver = new Driver();
		
//		File file = new File("absolute path in case if someone wants to test using this class");

		File file = new File(driver.getClass().getResource("/SimpleNem12.csv").toURI());

		SimpleNem12ParserImpl parser = new SimpleNem12ParserImpl();
		
		Collection<MeterRead> test = parser.parseSimpleNem12(file);

		test.stream().forEach(meterRead -> System.out.println(meterRead.getNmi() + " - " + meterRead.getTotalVolume()));
	}
}
