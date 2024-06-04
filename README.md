# README

## Command Lines:

*(For running the program)*

javac *.java

java Main

*(For plotting the curve)*

pip install pandas matplotlib

python plot_learning_curve.py training_results.csv

**(To see what the chart looks like, please email it to yourself using the mutt command)**

To install mutt: sudo brew install mutt

To send attachment to yourself: echo "Here is the learning curve PDF attached." | mutt -s "Learning Curve PDF" -a ./training_results_learning_curve.pdf -- [your email address]


## Observation:

If overfitting is occurring, the training accuracy would continue to increase and become high, while the testing accuracy would either start to decrease or plateau, showing that improvements in the model are not translating to unseen data.
If overfitting is not occurring, both training and testing accuracies should increase in tandem, with the testing accuracy possibly plateauing but not decreasing significantly.

## Considerations:

- Convergence: All lines seem to be converging to a certain accuracy level, which suggests that the model has stabilized in its learning.
- Gap Between Training and Testing: There is a visible gap between the training and testing accuracy lines. A small gap is normal, but a large and increasing gap may indicate overfitting.
- Fluctuations in Testing Accuracy: The testing accuracy lines, especially for higher learning rates, show considerable fluctuations. This could suggest that the model is sensitive to the particularities of the training data, which is a sign of overfitting.
- Learning Rate Effects: Different learning rates can have an impact on overfitting. Higher learning rates may cause the model to converge quickly but overfit, whereas lower learning rates may result in slower convergence but better generalization.

## Conclusion:

The graph depicts the learning curve of a model with respect to accuracy on training and test data across various learning rates (LR). Overfitting is typically indicated by a model performing significantly better on training data compared to test data. Here, the test accuracy closely follows the train accuracy across all learning rates without a pronounced gap, suggesting that overfitting is minimal. However, for learning rates 0.5 and 1.0, the fluctuations in test accuracy are more pronounced and less stable, which can be a sign of the model not generalizing well, potentially leading to overfitting if the trend continues beyond the 100 epochs shown. The highest stable accuracy on test data appears to be achieved with a learning rate of 0.001, where both the train and test accuracies plateau without much discrepancy between them.

There is no clear evidence of overfitting; the test accuracies seem relatively stable and do not show a significant decrease as epochs increase. However, the variability in the testing lines for larger learning rates suggests that these models may be less stable, which could be a precursor to overfitting if the trend were to continue past the number of epochs shown.


## Further observations:

- Increasing the Learning Rate (Alpha)

Higher Alpha: Increasing the learning rate can speed up convergence by taking larger steps toward the minimum of the loss function during each update. However, if the learning rate is too high, it might might cause the training process to become unstable, leading the weights to oscillate around the minimum or diverge entirely.

Lower Alpha: A lower learning rate ensures smaller, more precise updates, which might help converge smoothly to a better minimum. The trade-off is that training can become very slow, requiring more epochs to reach convergence.
- Adjusting the Number of Epochs

More Epochs: Increasing the number of epochs means the training algorithm will have more opportunities to update the weights and potentially reduce the loss further. This can be particularly beneficial if the learning rate is small. However, training for too many epochs can lead to overfitting, especially if the training data is not representative of the general population or is noisy.

Fewer Epochs: Training for fewer epochs can be useful to prevent overfitting in some cases, especially when using a higher learning rate. This might be appropriate if early stopping is used, where you monitor the model’s performance on a validation set and stop training when performance begins to degrade.

## Training Results Learning Curve
<img width="1272" alt="Screenshot 2024-06-04 at 9 24 46 PM" src="https://github.com/elmahsieh/LogisticRegressionTraining/assets/141378765/d84cf103-0daf-45b1-97c8-057d0168e4c5">