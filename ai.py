import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers

# Define the neural network model
model = keras.Sequential()

# Input layer
model.add(layers.Input(shape=(input_shape,)))

# First hidden layer
model.add(layers.Dense(units=64, activation='relu'))
# Explanation:
# - We define the first hidden layer with 64 units (neurons) and ReLU activation function.
# - The input shape is determined automatically from the previous layer (the input layer).
# - The ReLU activation introduces non-linearity, allowing the network to learn complex patterns.

# Second hidden layer
model.add(layers.Dense(units=32, activation='relu'))
# Explanation:
# - Similar to the first hidden layer, we define the second hidden layer with 32 units and ReLU activation.
# - The number of units in each hidden layer is a hyperparameter that can be tuned.

# Output layer
model.add(layers.Dense(units=output_units, activation='softmax'))
# Explanation:
# - The output layer typically has a different activation function, depending on the problem.
# - In this example, we use the 'softmax' activation for a classification task, which normalizes the output into a probability distribution over classes.
# - The number of units in the output layer is determined by the number of classes in your classification task.

# Compile the model
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])
# Explanation:
# - We specify the optimizer ('adam' in this case), which is used to update the network's weights during training.
# - 'categorical_crossentropy' is a common loss function for multi-class classification problems.
# - 'accuracy' is a metric that will be monitored during training to assess the model's performance.

# Display the model summary
model.summary()
# Explanation:
# - This command displays a summary of the model architecture, including the number of parameters in each layer.

# Now, you can train and evaluate the model using your dataset.
