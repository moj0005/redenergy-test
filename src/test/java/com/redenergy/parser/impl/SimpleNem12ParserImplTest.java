package com.redenergy.parser.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.junit.Test;

import com.redenergy.exceptions.InvalidContentException;
import com.redenergy.model.MeterRead;

public class SimpleNem12ParserImplTest {

	SimpleNem12ParserImpl test = new SimpleNem12ParserImpl();

	@Test(expected = InvalidContentException.class)
	public void testEmptyFile() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("empty.csv").toURI());

		test.parseSimpleNem12(file);

	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile1() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("just_start_flag.csv").toURI());

		test.parseSimpleNem12(file);

	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile2() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("just_end_flag.csv").toURI());

		test.parseSimpleNem12(file);

	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile3() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("start_and_end_flags.csv").toURI());

		test.parseSimpleNem12(file);

	}

	@Test
	public void testInvalidFile4() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("meter_read_block_only.csv").toURI());

		Collection<MeterRead> metersData = test.parseSimpleNem12(file);
		
		for (MeterRead meterData : metersData) {
			assertEquals("6123456789", meterData.getNmi());
			assertEquals(0, meterData.getTotalVolume().intValue());
		}
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile5() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("meter_read_block_only_with_wrong_record_type.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile6() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("meter_read_block_with_start_tag_only.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile7() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("meter_read_block_with_end_tag_only.csv").toURI());

		test.parseSimpleNem12(file);
		
	}


	@Test(expected = InvalidContentException.class)
	public void testInvalidFile8() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_nmi_1.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile9() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_nmi_2.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile10() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_nmi_3.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile11() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_nmi_4.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile12() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("duplicate_nmi.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile13() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_energy_unit.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile14() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("incomplete_volume_record.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile15() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_quality_enum.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile16() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_volume.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile17() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("empty_date.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile18() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_date.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile19() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("invalid_record_code.csv").toURI());

		test.parseSimpleNem12(file);
		
	}

	@Test
	public void testInvalidFile20() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("just_meter_read_block.csv").toURI());

		Collection<MeterRead> metersData = test.parseSimpleNem12(file);
		
		for (MeterRead meterData : metersData) {
			assertEquals(0, meterData.getTotalVolume().intValue());
		}
	}

	@Test
	public void testInvalidFile21() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("SimpleNem12_original.csv").toURI());

		Collection<MeterRead> metersData = test.parseSimpleNem12(file);
		
		for (MeterRead meterData : metersData) {
			
			if (meterData.getNmi().equals("6123456789")) {
				assertEquals(Double.valueOf(-36.84), Double.valueOf(meterData.getTotalVolume().doubleValue()));
			} else if (meterData.getNmi().equals("6987654321")) {
				assertEquals(Double.valueOf(14.33), Double.valueOf(meterData.getTotalVolume().doubleValue()));
			}
			
			
		}
	}

	@Test
	public void testInvalidFile22() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("SimpleNem12_modified.csv").toURI());

		Collection<MeterRead> metersData = test.parseSimpleNem12(file);
		
		for (MeterRead meterData : metersData) {
			
			if (meterData.getNmi().equals("6123456789")) {
				assertEquals(Double.valueOf(-26.84), Double.valueOf(meterData.getTotalVolume().doubleValue()));
			} else if (meterData.getNmi().equals("6987654321")) {
				assertEquals(Double.valueOf(4.5), Double.valueOf(meterData.getTotalVolume().doubleValue()));
			}
			
			
		}
	}

	@Test
	public void testInvalidFile23() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("SimpleNem12_double_test.csv").toURI());

		Collection<MeterRead> metersData = test.parseSimpleNem12(file);
		
		for (MeterRead meterData : metersData) {
			
			if (meterData.getNmi().equals("6123456789")) {
				assertEquals(Double.valueOf(0.0000002335), Double.valueOf(meterData.getTotalVolume().doubleValue()));
			} else if (meterData.getNmi().equals("6987654321")) {
				assertEquals(Double.valueOf(0), Double.valueOf(meterData.getTotalVolume().doubleValue()));
			}
			
			
		}
	}

	@Test(expected = InvalidContentException.class)
	public void testInvalidFile24() throws URISyntaxException, InvalidContentException, IOException {
		
		File file = new File(this.getClass().getClassLoader().getResource("SimpleNem12_duplicate_date.csv").toURI());

		test.parseSimpleNem12(file);
		
	}
}
