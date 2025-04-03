package com.pFI.pFI_api.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Utility class providing common methods for testing purposes.
 * This class includes utilities for:
 * - Creating test data
 * - Comparing objects
 * - File handling for tests
 * - Generating mock data
 */
public class TestUtils {

    private static final Random random = new Random();

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private TestUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    // ---------- Test Data Creation Utilities ----------

    /**
     * Creates a random string of specified length.
     *
     * @param length The length of the random string to generate
     * @return A random alphanumeric string
     */
    public static String createRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    /**
     * Generates a random integer within the specified range.
     *
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return A random integer within the specified range
     */
    public static int createRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Creates a list of random integers of the specified size.
     *
     * @param size The number of integers to generate
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return A list containing random integers
     */
    public static List<Integer> createRandomIntList(int size, int min, int max) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(createRandomInt(min, max));
        }
        return list;
    }

    /**
     * Generates a unique identifier for test objects.
     *
     * @return A unique string ID
     */
    public static String createUniqueId() {
        return UUID.randomUUID().toString();
    }

    // ---------- Object Comparison Utilities ----------

    /**
     * Compares two lists for equality irrespective of element order.
     *
     * @param <T> Type of list elements
     * @param list1 First list to compare
     * @param list2 Second list to compare
     * @return true if both lists contain the same elements (ignoring order)
     */
    public static <T> boolean areListsEqualIgnoringOrder(List<T> list1, List<T> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }
        return list1.containsAll(list2) && list2.containsAll(list1);
    }

    /**
     * Compares two objects for deep equality by comparing all their fields.
     * This is a simplified implementation and might need to be expanded based on specific needs.
     *
     * @param obj1 First object to compare
     * @param obj2 Second object to compare
     * @param excludeFields Field names to exclude from comparison
     * @return true if objects are deeply equal
     */
    public static boolean areObjectsEqual(Object obj1, Object obj2, String... excludeFields) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null || obj1.getClass() != obj2.getClass()) {
            return false;
        }

        // In a real implementation, this would use reflection to compare all fields
        // except those in excludeFields. For brevity, we're just returning true.
        // In practice, you might want to use libraries like Apache Commons Lang's
        // EqualsBuilder or similar for this functionality.

        return obj1.equals(obj2);
    }

    /**
     * Determines if two double values are equal within a small delta.
     *
     * @param value1 First double value
     * @param value2 Second double value
     * @param delta Maximum allowed difference
     * @return true if the values are equal within the specified delta
     */
    public static boolean areDoublesEqual(double value1, double value2, double delta) {
        return Math.abs(value1 - value2) <= delta;
    }

    // ---------- File Handling Utilities ----------

    /**
     * Creates a temporary directory for test files.
     *
     * @param prefix Directory name prefix
     * @return Path to the created temporary directory
     * @throws IOException If directory creation fails
     */
    public static Path createTempDirectory(String prefix) throws IOException {
        return Files.createTempDirectory(prefix);
    }

    /**
     * Creates a temporary file with the specified content.
     *
     * @param directory Directory to create the file in
     * @param fileName Name of the file
     * @param content Content to write to the file
     * @return The created file
     * @throws IOException If file operations fail
     */
    public static File createTempFile(Path directory, String fileName, String content) throws IOException {
        Path filePath = Paths.get(directory.toString(), fileName);
        Files.write(filePath, content.getBytes());
        return filePath.toFile();
    }

    /**
     * Reads the content of a file as a string.
     *
     * @param filePath Path to the file
     * @return The file content as a string
     * @throws IOException If reading fails
     */
    public static String readFileContent(Path filePath) throws IOException {
        return new String(Files.readAllBytes(filePath));
    }

    /**
     * Deletes a directory and all its contents recursively.
     *
     * @param directory The directory to delete
     * @throws IOException If deletion fails
     */
    public static void deleteDirectory(Path directory) throws IOException {
        if (Files.exists(directory)) {
            Files.walk(directory)
                    .sorted((a, b) -> -a.compareTo(b)) // Reverse order to delete files first, then directories
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to delete: " + path, e);
                        }
                    });
        }
    }

    // ---------- Mock Data Generation Helpers ----------

    /**
     * Generates a random email address for testing.
     *
     * @return A random email address
     */
    public static String createRandomEmail() {
        return createRandomString(8) + "@" + createRandomString(5) + ".com";
    }

    /**
     * Generates a random phone number for testing.
     *
     * @return A random phone number string
     */
    public static String createRandomPhoneNumber() {
        StringBuilder phone = new StringBuilder("(");
        phone.append(random.nextInt(900) + 100) // Area code
                .append(") ")
                .append(random.nextInt(900) + 100) // Prefix
                .append("-")
                .append(String.format("%04d", random.nextInt(10000))); // Line number
        return phone.toString();
    }

    /**
     * Creates a random date string in the format YYYY-MM-DD.
     *
     * @param startYear Minimum year
     * @param endYear Maximum year
     * @return A random date string
     */
    public static String createRandomDate(int startYear, int endYear) {
        int year = createRandomInt(startYear, endYear);
        int month = createRandomInt(1, 12);

        // Determine max days based on month and year
        int maxDays = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        } else if (month == 2) {
            // Simplified leap year check
            maxDays = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        }

        int day = createRandomInt(1, maxDays);

        return String.format("%04d-%02d-%02d", year, month, day);
    }

    /**
     * Generates a list of random words for testing.
     *
     * @param count Number of words to generate
     * @param minLength Minimum word length
     * @param maxLength Maximum word length
     * @return A list of random words
     */
    public static List<String> createRandomWords(int count, int minLength, int maxLength) {
        List<String> words = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            int length = createRandomInt(minLength, maxLength);
            words.add(createRandomString(length));
        }
        return words;
    }
}

