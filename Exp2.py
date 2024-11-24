import tkinter as tk

def show_message():
    name = text_field.get()
    output_label.config(text=f"Hello, {name}!")

# Create the main window
root = tk.Tk()
root.title("Simple UI")
root.geometry("300x200")

# Text field
text_field = tk.Entry(root, width=30)
text_field.pack(pady=10)

# Button
button = tk.Button(root, text="Click Me", command=show_message)
button.pack(pady=10)

# Output label
output_label = tk.Label(root, text="")
output_label.pack(pady=10)

# Run the application
root.mainloop()
