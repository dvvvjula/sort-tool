import javax.swing.*;
import java.io.*;
import java.util.*;

public class Sort {

    // Generic QuickSort implementation
    public static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Helper method for partitioning the array in QuickSort
    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Generic BubbleSort implementation
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    // Sort data loaded from a file
    public static void sortExistingSet(String filePath, String algorithm) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
            reader.close();

            if (lines.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The file is empty.");
                return;
            }

            // Automatically detect the data type
            String detectedType = detectType(lines);
            System.out.println("Detected data type: " + detectedType);

            // Parse and sort the data according to the detected type
            if (detectedType.equals("Integer")) {
                Integer[] data = new Integer[lines.size()];
                for (int i = 0; i < lines.size(); i++) {
                    data[i] = Integer.parseInt(lines.get(i));
                }
                sortArray(data, algorithm);
                promptAndSaveResult(data);
            } else if (detectedType.equals("Double")) {
                Double[] data = new Double[lines.size()];
                for (int i = 0; i < lines.size(); i++) {
                    data[i] = Double.parseDouble(lines.get(i));
                }
                sortArray(data, algorithm);
                promptAndSaveResult(data);
            } else { // String type
                String[] data = lines.toArray(new String[0]);
                sortArray(data, algorithm);
                promptAndSaveResult(data);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File reading error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error parsing number: " + e.getMessage());
        }
    }

    // Sort data generated artificially
    public static void sortGeneratedSet(String type, int size, String algorithm) {
        if (type.equalsIgnoreCase("Integer")) {
            Integer[] data = generateIntegerArray(size);
            sortArray(data, algorithm);
            promptAndSaveResult(data);
        } else if (type.equalsIgnoreCase("Double")) {
            Double[] data = generateDoubleArray(size);
            sortArray(data, algorithm);
            promptAndSaveResult(data);
        } else { // String type
            String[] data = generateStringArray(size);
            sortArray(data, algorithm);
            promptAndSaveResult(data);
        }
    }

    // Choose the sorting algorithm and sort the data
    private static <T extends Comparable<T>> void sortArray(T[] data, String algorithm) {
        long start = System.currentTimeMillis();
        if (algorithm.equalsIgnoreCase("QuickSort")) {
            quickSort(data, 0, data.length - 1);
        } else if (algorithm.equalsIgnoreCase("BubbleSort")) {
            bubbleSort(data);
        }
        long end = System.currentTimeMillis();
        System.out.println(algorithm + " completed in " + (end - start) + " ms.");
    }

    // Prompt the user if they want to save the sorted result to a file
    private static <T> void promptAndSaveResult(T[] data) {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to save the result to a file?", "Save Result", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String fileName = JOptionPane.showInputDialog(null, "Enter the file name for saving:", "sorted_output.txt");
            if (fileName != null && !fileName.trim().isEmpty()) {
                saveToFile(data, fileName);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid file name.");
            }
        }
    }

    // Save the data to a file
    private static <T> void saveToFile(T[] data, String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (T item : data) {
                pw.println(item);
            }
            JOptionPane.showMessageDialog(null, "The result was saved to the file: " + fileName);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving to file: " + ex.getMessage());
        }
    }

    // Detect the data type based on the content of the file
    private static String detectType(List<String> lines) {
        boolean isInteger = true;
        boolean isDouble = true;
        for (String line : lines) {
            try {
                Integer.parseInt(line);
            } catch (NumberFormatException e) {
                isInteger = false;
            }
            try {
                Double.parseDouble(line);
            } catch (NumberFormatException e) {
                isDouble = false;
            }
        }
        if (isInteger)
            return "Integer";
        if (isDouble)
            return "Double";
        return "String";
    }

    // Generate an array of random integers
    private static Integer[] generateIntegerArray(int size) {
        Integer[] arr = new Integer[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000); // numbers between 0 and 9999
        }
        return arr;
    }

    // Generate an array of random doubles
    private static Double[] generateDoubleArray(int size) {
        Double[] arr = new Double[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextDouble() * 10000; // numbers between 0 and 10000
        }
        return arr;
    }

    // Generate an array of random strings (e.g., random 5-letter words)
    private static String[] generateStringArray(int size) {
        String[] arr = new String[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = generateRandomWord(rand, 5);
        }
        return arr;
    }

    // Generate a random word of a given length
    private static String generateRandomWord(Random rand, int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }
}
