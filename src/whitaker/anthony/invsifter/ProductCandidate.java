package whitaker.anthony.invsifter;

/**
 * Represents a potential product. Contains category & name.
 * Number will be auto-generated and expiration date is entered at product creation.
 */
public class ProductCandidate {
	private final String category;
	private final String name;

	public ProductCandidate(String category, String name) {
		this.category = category;
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductCandidate{" +
				"category='" + category + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}
}
