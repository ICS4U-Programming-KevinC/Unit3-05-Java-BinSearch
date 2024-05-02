import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Does a binary search.
 *
 * @author Kevin Csiffary
 * @version 1.0
 * @since 2024-05-02
 */

// BinSearch class
public final class BinSearch {

  /** Private constructor to prevent instantiation. */
  private BinSearch() {
    throw new UnsupportedOperationException("Cannot instantiate");
  }

  /**
   * This is the main method.
   *
   * @param args Unused
   */
  public static void main(final String[] args) {
    try {
      // Setup scanner on file.
      File file = new File("input.txt");
      Scanner sc = new Scanner(file);
      // Setup writer for output file.
      FileWriter writer = new FileWriter("output.txt");
      BufferedWriter bufferedWriter = new BufferedWriter(writer);

      while (sc.hasNextLine()) {
        /// Check if line is valid input.
        try {
          // Read the line from file.
          String line = sc.nextLine();
          // Parse to an array.
          String[] lineArr = line.split(" ");
          // Cast to an int array.
          int[] intArr =
              Arrays.stream(lineArr)
                  .mapToInt(
                      // Make google happy.
                      Integer::parseInt)
                  .toArray();
          // Read the search number on the next line
          line = sc.nextLine();
          // Sort the array.
          Arrays.sort(intArr);
          // Cast the search number to an int.
          int searchNum = Integer.parseInt(line);
          // Make lines shorter.
          int search = binSearch(intArr, searchNum, 0, lineArr.length - 1);
          // Call method and write to file.
          bufferedWriter.write(Integer.toString(search));
        } catch (Exception e) {
          // Catch a non number error.
          bufferedWriter.write("Please input a number!");
        }
        // Adds a new line.
        bufferedWriter.newLine();
      }

      // Close all writers and scanner.
      bufferedWriter.close();
      writer.close();
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Does a binary search.
   *
   * @param arr
   * @param num
   * @param low
   * @param high
   * @return Returns index of the position.
   */
  public static Integer binSearch(
      // Inputs.
      final int[] arr,
      final Integer num,
      final Integer low,
      final Integer high) {
    // Check if high is less than low.
    if (high < low) {
      return -1;
    } else {
      // Calculate mid.
      int mid = (int) ((low + high) / 2);
      // Check if the array at mid is the search number.
      if (arr[mid] == num) {
        // Return that index.
        return mid;
      } else if (arr[mid] > num) {
        // If array at mid is greater than the search number, decrease high.
        return binSearch(arr, num, low, mid - 1);
      } else {
        // If array at mid is greater than the search number, increases low.
        return binSearch(arr, num, mid + 1, high);
      }
    }
  }
}
