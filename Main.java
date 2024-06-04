import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static final int NUMBER_OF_FEATURES = 123; // Adjust this based on your dataset

    public static void main(String[] args) {
        try {
            // Define paths to the dataset files
            String basePath = "/u/cs242/hw5/"; // Ensure this path is correct
            String trainPath = basePath + "a7a.train";
            String testPath = basePath + "a7a.test";
            String devPath = basePath + "a7a.dev";

            // Load training data
            DataLoader.loadData(trainPath, NUMBER_OF_FEATURES);
            double[][] trainingFeatures = DataLoader.getFeatures();
            int[] trainingLabels = DataLoader.getLabels();
            DataLoader.clear();

            // Load testing data
            DataLoader.loadData(testPath, NUMBER_OF_FEATURES);
            double[][] testingFeatures = DataLoader.getFeatures();
            int[] testingLabels = DataLoader.getLabels();
            DataLoader.clear();

            // Optionally load development data
            DataLoader.loadData(devPath, NUMBER_OF_FEATURES);
            double[][] devFeatures = DataLoader.getFeatures();
            int[] devLabels = DataLoader.getLabels();

            // Create logistic regression model
            LogisticRegression model = new LogisticRegression(NUMBER_OF_FEATURES);
            /***
             * CHANGED
             */

            // Array of alpha values
            double[] alphas = {0.001, 0.01, 0.1, 0.5, 1.0};

            // Open the file once, outside the alpha loop
            try (FileWriter writer = new FileWriter("training_results.csv", false)) {  // false to overwrite if exists
                // Write headers
                writer.write("Alpha,Epoch,Train Accuracy,Test Accuracy\n");

                // Iterate over each alpha value
                for (double alpha : alphas) {
                    // Clear or reinitialize accuracy and loss lists
                    model.trainAccuracies.clear();
                    model.testAccuracies.clear();

                    // Train model on training data and evaluate on testing data after each epoch
                    model.train(trainingFeatures, trainingLabels, testingFeatures, testingLabels, new double[]{alpha}, 100);  // epochs = 100

                    // Write data for each epoch
                    for (int epoch = 0; epoch < model.trainAccuracies.size(); epoch++) {
                        double trainAccuracy = model.trainAccuracies.get(epoch);
                        double testAccuracy = model.testAccuracies.get(epoch);
                        writer.write(alpha + "," + (epoch + 1) + "," + trainAccuracy + "," + testAccuracy + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();  // Handle exceptions appropriately
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    

