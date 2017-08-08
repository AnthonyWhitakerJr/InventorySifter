package whitaker.anthony.invsifter;

import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore("MANUAL RUN ONLY. USE TO GENERATE DATA FOR TESTING.")
public class DataGeneratorTest {

	public static final String DELIMITER = ";";
	public static final String FILENAME_DATASET_1 = "src/test/resources/dataset1.txt";
	public static final String FILENAME_DATASET_2 = "src/test/resources/dataset2.txt";
	public static final String FILENAME_DATASET_3 = "src/test/resources/dataset3.txt";

	@Test
	public void generateTestData() {
		DataGenerator generator = new DataGenerator("src/main/resources/ProductCandidates.txt", DELIMITER, null, null);

		int numberOfProductsInSet = 25;
		Collection<Product> dataset = generator.generateDataSet(numberOfProductsInSet, LocalDate.now(), LocalDate.now().plusYears(1));
		assertEquals(numberOfProductsInSet, dataset.size());
		generator.writeDataSetToFile(dataset, FILENAME_DATASET_1, DELIMITER);
		assertTrue("File not created correctly.", Files.isRegularFile(Paths.get(FILENAME_DATASET_1)));

		numberOfProductsInSet = 25;
		dataset = generator.generateDataSet(numberOfProductsInSet, LocalDate.now().plusMonths(2), LocalDate.now().plusMonths(14));
		assertEquals(numberOfProductsInSet, dataset.size());
		generator.writeDataSetToFile(dataset, FILENAME_DATASET_2, DELIMITER);
		assertTrue("File not created correctly.", Files.isRegularFile(Paths.get(FILENAME_DATASET_2)));

		numberOfProductsInSet = 25;
		dataset = generator.generateDataSet(numberOfProductsInSet, LocalDate.now().plusMonths(6), LocalDate.now().plusMonths(18));
		assertEquals(numberOfProductsInSet, dataset.size());
		generator.writeDataSetToFile(dataset, FILENAME_DATASET_3, DELIMITER);
		assertTrue("File not created correctly.", Files.isRegularFile(Paths.get(FILENAME_DATASET_3)));
	}

}