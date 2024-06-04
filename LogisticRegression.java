import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogisticRegression {
    private double[] weights;
    public List<Double> testAccuracies; // List to store accuracy after each epoch
    public List<Double> trainAccuracies = new ArrayList<>();
    public List<Double> trainLosses = new ArrayList<>();
    public List<Double> testLosses = new ArrayList<>();
    public List<Double> learningRates = new ArrayList<>();

    public LogisticRegression(int numFeatures) {
        weights = new double[numFeatures];
        testAccuracies = new ArrayList<>();
    }

    public void train(double[][] trainFeatures, int[] trainLabels, double[][] testFeatures, int[] testLabels, double[] alphas, int epochs) {
        for (double alpha : alphas) { // Iterate over each alpha value
            Random random = new Random();
            for (int epoch = 0; epoch < epochs; epoch++) {
                double trainLoss = 0;
                int correctPredictions = 0;
                for (int i = 0; i < trainFeatures.length; i++) {
                    int index = random.nextInt(trainFeatures.length); // Random selection of index for SGD
                    double dot = dotProduct(weights, trainFeatures[index]);
                    double z = dot * trainLabels[index];
                    double prediction = sigmoid(z);
                    double error = (1 - prediction) * trainLabels[index]; // Adjusted error calculation
                    for (int j = 0; j < weights.length; j++) {
                        weights[j] += alpha * error * trainFeatures[index][j];
                    }
                    trainLoss += computeLoss(prediction, trainLabels[index]);
                }
                double trainAccuracy = evaluate(trainFeatures, trainLabels);
                double testAccuracy = evaluate(testFeatures, testLabels);
                double testLoss = computeTotalLoss(testFeatures, testLabels);

                trainAccuracies.add(trainAccuracy);
                testAccuracies.add(testAccuracy);
                trainLosses.add(trainLoss / trainFeatures.length); // Average loss per sample
                testLosses.add(testLoss);
                learningRates.add(alpha);

                System.out.println("Alpha: " + alpha + ", Epoch " + (epoch + 1) + " - Train Accuracy: " + trainAccuracy + ", Test Accuracy: " + testAccuracy);
            }
        }
    }

    private double computeLoss(double prediction, int actual) {
        return -actual * Math.log(prediction) - (1 - actual) * Math.log(1 - prediction);
    }

    private double computeTotalLoss(double[][] features, int[] labels) {
        double totalLoss = 0;
        for (int i = 0; i < features.length; i++) {
            double prediction = sigmoid(dotProduct(weights, features[i]));
            totalLoss += computeLoss(prediction, labels[i]);
        }
        return totalLoss;
    }

    public double evaluate(double[][] features, int[] labels) { // Changed from 'private' to 'public'
        int correct = 0;
        for (int i = 0; i < features.length; i++) {
            double score = dotProduct(weights, features[i]);
            int predictedLabel = score >= 0 ? 1 : -1;
            if (predictedLabel == labels[i]) {
                correct++;
            }
        }
        return (double) correct / labels.length;
    }

    private static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    private static double dotProduct(double[] vectorA, double[] vectorB) {
        double result = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            result += vectorA[i] * vectorB[i];
        }
	return result;
    }
}

