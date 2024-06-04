import sys
import pandas as pd
import matplotlib.pyplot as plt

def plot_learning_curve(csv_filename):
    # Read the training results from the CSV file
    df = pd.read_csv(csv_filename)

    # Extract unique alpha values from the 'Alpha' column which represents the learning rate
    alphas = df['Alpha'].unique()

    # Set a color map with distinct colors for each learning rate
    colors = plt.cm.get_cmap('viridis', len(alphas))

    # Initialize a new plot
    plt.figure(figsize=(10, 6))

    # Plot the accuracy for each alpha value
    for idx, alpha in enumerate(alphas):
        subset = df[df['Alpha'] == alpha]
        color = colors(idx)
        # Solid line for training accuracy
        plt.plot(subset['Epoch'], subset['Train Accuracy'], label=f'Train LR={alpha}', color=color, linestyle='-')
        # Dashed line for testing accuracy
        plt.plot(subset['Epoch'], subset['Test Accuracy'], label=f'Test LR={alpha}', color=color, linestyle='--')

    # Title and labels
    plt.title('Learning Curve: Updates vs Accuracy on Test Data')
    plt.xlabel('Epoch')
    plt.ylabel('Accuracy')

    # Add legend outside the plot
    plt.legend(loc='lower left', framealpha=0.5)
    # plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left')

    # Show grid
    plt.grid(True)

    # Save the plot to a file
    output_filename = csv_filename.replace('.csv', '_learning_curve.pdf')
    plt.savefig(output_filename)

    # Show the plot
    plt.show()

if __name__ == '__main__':
    if len(sys.argv) < 2:
        print("Usage: python3 script.py [CSV file path]")
        sys.exit(1)

    csv_file_path = sys.argv[1]
    plot_learning_curve(csv_file_path)