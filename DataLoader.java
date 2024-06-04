mport java.io.*;
import java.util.*;

public class DataLoader {
    public static List<double[]> features = new ArrayList<>();
    public static List<Integer> labels = new ArrayList<>();

    public static void loadData(String filename, int numFeatures) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            double[] feature = new double[numFeatures]; // Initialize features array with all zeros
            int label = Integer.parseInt(tokens[0]); // The first token is the label

            for (int i = 1; i < tokens.length; i++) {
                String[] featureValue = tokens[i].split(":");
                int featureIndex = Integer.parseInt(featureValue[0]) - 1; // Adjust for zero-based index
                double value = Double.parseDouble(featureValue[1]);
                feature[featureIndex] = value; // Set the specific feature index
            }

            features.add(feature);
            labels.add(label == 1 ? 1 : -1); // Assuming the labels are 1 for '>50K' and 0 for '<=50K', adjust as necessary
        }
	br.close();
    }

    public static double[][] getFeatures() {
        return features.toArray(new double[0][]);
    }

    public static int[] getLabels() {
        return labels.stream().mapToInt(i -> i).toArray();
    }
    public static void clear() {
        features.clear(); // Clear all loaded features
        labels.clear();   // Clear all loaded labels
    }
}
