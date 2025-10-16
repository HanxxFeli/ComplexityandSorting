package appDomain;

import java.io.*;
import java.util.*;
import utilities.*;

/**
 * Application driver for sorting geometric shapes.
 * Reads shapes from a file, sorts them using various algorithms,
 * and displays benchmark results.
 *
 * @author Xander, Hans, Yvana, Celine
 */
public class AppDriver {

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments:
     *   -f<filename> : input file path (required)
     *   -t<v|h|a> : compare type (v=volume, h=height, a=base area)
     *   -s<b|s|i|m|q|z> : sorting algorithm
     *      b=bubble(Xander), s=selection(Yvana), i=insertion(Hans),
     *      m=merge(Hans), q=quick(Yvana), z=custom(Celine)
     */
    public static void main(String[] args) {
        // Parse command line arguments
        String filename = null;
        String compareType = "h"; // default to height
        String sortType = "b"; // default to bubble

        for (String arg : args) {
            if (arg.startsWith("-f") || arg.startsWith("-F")) {
                filename = arg.substring(2);
            } else if (arg.startsWith("-t") || arg.startsWith("-T")) {
                compareType = arg.substring(2).toLowerCase();
            } else if (arg.startsWith("-s") || arg.startsWith("-S")) {
                sortType = arg.substring(2).toLowerCase();
            }
        }

        // Validate arguments
        if (filename == null || filename.isEmpty()) {
            System.out.println("Error: File name is required. Use -f<filename>");
            System.out.println("Usage: java -jar Sort.jar -f<filename> -t<v|h|a> -s<b|s|i|m|q|z>");
            return;
        }

        if (!compareType.matches("[vha]")) {
            System.out.println("Error: Invalid compare type. Use v (volume), h (height), or a (base area)");
            return;
        }

        if (!sortType.matches("[bsimqz]")) {
            System.out.println("Error: Invalid sort type. Use b, s, i, m, q, or z");
            return;
        }

        try {
            // FILE I/O
            Shape[] shapes = readShapesFromFile(filename);

            if (shapes == null || shapes.length == 0) {
                System.out.println("Error: No shapes found in file or file could not be read.");
                return;
            }

            System.out.println("Loaded " + shapes.length + " shapes from file: " + filename);

            // Determine comparator (will be used by sorting algorithms)
            Comparator<Shape> comparator = getComparator(compareType);
            String compareTypeStr = getCompareTypeString(compareType);
            String sortAlgorithm = getSortAlgorithmName(sortType);

            System.out.println("Sorting by: " + compareTypeStr);
            System.out.println("Algorithm: " + sortAlgorithm);

            // Perform sorting with benchmarking
            long startTime = System.currentTimeMillis();

            // TODO: Team members will implement their sorting algorithms here
            sortShapes(shapes, sortType, comparator);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // Display results
            System.out.println("\n" + sortAlgorithm + " sort run time was: " + duration + " milliseconds");

            // Display first and last sorted values
            System.out.println("\nFirst element is:\t" + shapes[0] + "\t" +
                    compareTypeStr + ": " + getCompareValue(shapes[0], compareType));
            System.out.println("Last element is:\t" + shapes[shapes.length - 1] + "\t" +
                    compareTypeStr + ": " + getCompareValue(shapes[shapes.length - 1], compareType));

            // Display every 1000th element
            System.out.println();
            for (int i = 999; i < shapes.length; i += 1000) {
                System.out.println((i + 1) + "-th element:\t\t" + shapes[i] + "\t" +
                        compareTypeStr + ": " + getCompareValue(shapes[i], compareType));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads shapes from the txt file.
     *
     * File format:
     * - First line: number of shapes
     * - Subsequent lines: ShapeType height radius/edgeLength
     *
     * @param filename the path to the input file
     * @return array of Shape objects
     * @throws IOException if file cannot be read
     */
    private static Shape[] readShapesFromFile(String filename) throws IOException {
        List<Shape> shapeList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Read first line to get count
            String firstLine = br.readLine();
            if (firstLine == null) {
                return new Shape[0];
            }

            int count = Integer.parseInt(firstLine.trim());

            // Read each shape line
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                // Split by whitespace
                String[] parts = line.split("\\s+");
                if (parts.length < 3) {
                    continue;
                }

                String shapeType = parts[0];
                double param1 = Double.parseDouble(parts[1]); // height
                double param2 = Double.parseDouble(parts[2]); // radius or edge length

                // Create shape based on type
                Shape shape = createShape(shapeType, param1, param2);
                if (shape != null) {
                    shapeList.add(shape);
                }
            }
        }

        return shapeList.toArray(new Shape[0]);
    }

    /**
     * FILE I/O
     * Factory method to create shape objects based on type and parameters.
     *
     * @param type the shape type (Cylinder, Cone, Pyramid, SquarePrism, etc.)
     * @param param1 first parameter (height)
     * @param param2 second parameter (radius or edge length)
     * @return Shape object or null if type is invalid
     */
    private static Shape createShape(String type, double param1, double param2) {
        switch (type.toLowerCase()) {
            case "cylinder":
                return new Cylinder(param1, param2);
            case "cone":
                return new Cone(param1, param2);
            case "pyramid":
                return new Pyramid(param1, param2);
            case "squareprism":
                return new SquarePrism(param1, param2);
            case "triangularprism":
                return new TriangularPrism(param1, param2);
            case "pentagonalprism":
                return new PentagonalPrism(param1, param2);
            case "octagonalprism":
                return new OctagonalPrism(param1, param2);
            default:
                System.out.println("Warning: Unknown shape type: " + type);
                return null;
        }
    }

    /**
     * Helper method to get the appropriate comparator based on compare type.
     *
     * @param compareType 'v' for volume, 'h' for height, 'a' for base area
     * @return appropriate Comparator, or null for height (uses natural ordering)
     */
    private static Comparator<Shape> getComparator(String compareType) {
        switch (compareType) {
            case "v":
                return new VolumeComparator();
            case "a":
                return new BaseAreaComparator();
            case "h":
            default:
                return null; // Use natural ordering (Comparable)
        }
    }

    /**
     * Gets a human-readable string for the compare type
     */
    private static String getCompareTypeString(String compareType) {
        switch (compareType) {
            case "v":
                return "Volume";
            case "a":
                return "Area";
            case "h":
            default:
                return "Height";
        }
    }

    /**
     * Gets the value being compared for display purposes.
     */
    private static String getCompareValue(Shape shape, String compareType) {
        double value;
        switch (compareType) {
            case "v":
                value = shape.getVolume();
                break;
            case "a":
                value = shape.getBaseArea();
                break;
            case "h":
            default:
                value = shape.getHeight();
                break;
        }
        return String.format("%.3f", value);
    }

    /**
     * Gets the name of the sorting algorithm.
     */
    private static String getSortAlgorithmName(String sortType) {
        switch (sortType) {
            case "b":
                return "Bubble Sort"; // Xander
            case "s":
                return "Selection Sort"; // Yvana
            case "i":
                return "Insertion Sort"; // Hans
            case "m":
                return "Merge Sort"; // Hans
            case "q":
                return "Quick Sort"; // Yvana
            case "z":
                return "Custom Sort"; // Celine
            default:
                return "Unknown Sort";
        }
    }

    /**
     * Routes to the appropriate sorting algorithm based on sortType.
     *
     * @param shapes the array to sort
     * @param sortType the sorting algorithm to use
     * @param comparator the comparator, or null for natural ordering
     */
    private static void sortShapes(Shape[] shapes, String sortType, Comparator<Shape> comparator) {
        switch (sortType) {
            case "b": // Bubble Sort - XANDER
                if (comparator == null) {
                    BubbleSort.sort(shapes);
                } else {
                    BubbleSort.sort(shapes, comparator);
                }
                break;

            case "i": // Insertion Sort - HANS
                if (comparator == null) {
                    InsertionSort.sort(shapes);
                } else {
                    InsertionSort.sort(shapes, comparator);
                }
                break;

            case "s": // Selection Sort - YVANNA
                if (comparator == null) {
                    SelectionSort.sort(shapes);
                } else {
                    SelectionSort.sort(shapes, comparator);
                }
                break;

            case "m": // Merge Sort - HANS
                if (comparator == null) {
                    MergeSort.sort(shapes);
                } else {
                    MergeSort.sort(shapes, comparator);
                }
                break;

            case "q": // Quick Sort - YVANNA
                if (comparator == null) {
                    QuickSort.sort(shapes);
                } else {
                    QuickSort.sort(shapes, comparator);
                }
                break;

            case "z": // Custom Sort - CELINE
                if (comparator == null) {
                    CustomSort.sort(shapes);
                } else {
                    CustomSort.sort(shapes, comparator);
                }
                break;

            default:
                System.out.println("Invalid sort type");
                break;
        }
    }
}