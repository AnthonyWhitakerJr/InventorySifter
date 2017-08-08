package whitaker.anthony.invsifter;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static whitaker.anthony.invsifter.DataGenerator.DEFAULT_EXPIRATION_DATE_FORMAT;
import static whitaker.anthony.invsifter.DataGenerator.DEFAULT_EXPIRATION_DATE_LOCALE;
import static whitaker.anthony.invsifter.DataGeneratorTest.*;

public class InventorySifterTest {

	/**
	 * Parse stream containing product candidates into an ArrayList.
	 * Stream should contain lines with a product category & name separated by a delimiter.
	 *
	 * @param stream    Stream to parse.
	 * @param delimiter Delimiter between category & name in stream.
	 * @return Arraylist of product candidates based on file contents.
	 * @throws IllegalArgumentException If unable to parse file properly with given delimiter.
	 */
	public static List<Product> parseProducts(Stream<String> stream, String delimiter, String expirationDateFormat, Locale expirationDateLocale) {
		return stream.map(s -> Product.parseProduct(s, delimiter, expirationDateFormat, expirationDateLocale))
				.collect(Collectors.toList());
	}

	/**
	 * Parse file containing product candidates into an ArrayList.
	 * File should contain lines with a product category & name separated by a delimiter.
	 *
	 * @param filename  Name of file to parse
	 * @param delimiter Delimiter between category & name in file.
	 * @return Arraylist of product candidates based on file contents.
	 * @throws IllegalArgumentException If unable to parse file properly with given delimiter.
	 */
	public static List<Product> parseProductsFromFile(String filename, String delimiter, String expirationDateFormat, Locale expirationDateLocale) {
		try(Stream<String> stream = Files.lines(Paths.get(filename))) {
			return parseProducts(stream, delimiter, expirationDateFormat, expirationDateLocale);
		} catch(IOException | IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to parse \"" + filename + "\" with delimiter \"" + delimiter + "\"", e);
		}
	}

	@Test
	public void testInventorySifter() {
		List<Product> products = new ArrayList<>();

		// Add first data set.
		products.addAll(parseProductsFromFile(FILENAME_DATASET_1, DELIMITER, DEFAULT_EXPIRATION_DATE_FORMAT, DEFAULT_EXPIRATION_DATE_LOCALE));
		assertEquals(25, products.size());

		// Return products sorted by name.
		System.out.println("--------------------------------PRODUCTS BY NAME--------------------------------");
		products.stream().sorted(Product.BY_NAME).forEach(System.out::println);

		// Add the next data set.
		products.addAll(parseProductsFromFile(FILENAME_DATASET_2, DELIMITER, DEFAULT_EXPIRATION_DATE_FORMAT, DEFAULT_EXPIRATION_DATE_LOCALE));
		assertEquals(50, products.size());

		// Return products sorted by category.
		System.out.println("\n\n--------------------------------PRODUCTS BY CATEGORY--------------------------------");
		products.stream().sorted(Product.BY_CATEGORY).forEach(System.out::println);

		// Add the next data set.
		products.addAll(parseProductsFromFile(FILENAME_DATASET_3, DELIMITER, DEFAULT_EXPIRATION_DATE_FORMAT, DEFAULT_EXPIRATION_DATE_LOCALE));
		assertEquals(75, products.size());

		// Return products sorted by expiration date.
		System.out.println("\n\n--------------------------------PRODUCTS BY EXPIRATION DATE--------------------------------");
		products.stream().sorted(Product.BY_EXPIRATION_DATE).forEach(System.out::println);

		// Delete all products of a specific category.
		products = products.stream().filter(product -> !"Canned & Packaged Foods".equals(product.getCategory())).collect(Collectors.toList());
		assertNotEquals(75, products.size());
		assertTrue(75 > products.size());
		assertTrue(products.stream().filter(product -> "Canned & Packaged Foods".equals(product.getCategory())).collect(Collectors.toList()).isEmpty());

		// Return products sorted by product number.
		System.out.println("\n\n--------------------------------PRODUCTS BY PRODUCT NUMBER--------------------------------");
		products.stream().sorted(Product.BY_NUMBER).forEach(System.out::println);
	}

}
