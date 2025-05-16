import tkinter as tk
from tkinter import filedialog
import numpy as np
import tensorflow as tf
from keras.models import load_model
from keras.preprocessing import image
import matplotlib.pyplot as plt

# Load the trained model
model_path = r"D:\plant leaf ML\backkk\plant_disease_model.h5" # Make sure this is the correct path
model = load_model(model_path)

# Correct 20 class labels (as provided)
class_labels = [
    "Apple___Apple_scab",
    "Apple___healthy",
    "Cherry_(including_sour)___Powdery_mildew",
    "Cherry_(including_sour)___healthy",
    "Corn_(maize)___Cercospora_leaf_spot Gray_leaf_spot",
    "Corn_(maize)___Northern_Leaf_Blight",
    "Corn_(maize)___healthy",
    "Grape___Esca_(Black_Measles)",
    "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)",
    "Grape___healthy",
    "Peach___Bacterial_spot",
    "Peach___healthy",
    "Pepper,_bell___Bacterial_spot",
    "Pepper,_bell___healthy",
    "Potato___Late_blight",
    "Potato___healthy",
    "Strawberry___Leaf_scorch",
    "Strawberry___healthy",
    "Tomato___Late_blight",
    "Tomato___healthy",
]

# File picker dialog
root = tk.Tk()
root.withdraw()
file_path = filedialog.askopenfilename(
    title="Select a Leaf Image", 
    filetypes=[("Image files", "*.jpg *.jpeg *.png")]
)

if not file_path:
    print("❌ No file selected.")
    exit()

# Preprocess the selected image
img = image.load_img(file_path, target_size=(224, 224))
img_array = image.img_to_array(img) / 255.0
img_array = np.expand_dims(img_array, axis=0)

# Predict
predictions = model.predict(img_array)
predicted_index = np.argmax(predictions)
predicted_class = class_labels[predicted_index]
confidence = predictions[0][predicted_index] * 100

# Output results
print(f"✅ Predicted Disease: {predicted_class}")
print(f"🔍 Confidence: {confidence:.2f}%")

# Display the image with prediction and confidence
plt.imshow(img)
plt.title(f"{predicted_class}\nConfidence: {confidence:.2f}%", fontsize=14)
plt.axis('off')
plt.show()
