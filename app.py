import tkinter as tk
from tkinter.scrolledtext import ScrolledText

root = tk.Tk()
root.title("CyberAI")
root.geometry("400x650")
root.configure(bg="#101820")

title = tk.Label(
    root, text="CyberAI",
    font=("Arial", 22, "bold"),
    fg="#00e5ff", bg="#101820"
)
title.pack(pady=15)

subtitle = tk.Label(
    root, text="AI • Cybersecurity • Termux",
    fg="white", bg="#101820"
)
subtitle.pack()

chat = ScrolledText(
    root, wrap=tk.WORD,
    bg="#182631", fg="white",
    font=("Arial", 12)
)
chat.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)
chat.insert(tk.END, "CyberAI: أهلاً بك! هذه واجهة مشروعك الأول.\n")
chat.configure(state="disabled")

entry = tk.Entry(root, font=("Arial", 12))
entry.pack(fill=tk.X, padx=10, pady=5)

def send():
    msg = entry.get().strip()
    if not msg:
        return
    chat.configure(state="normal")
    chat.insert(tk.END, f"\nأنت: {msg}\n")
    chat.insert(tk.END, "CyberAI: الواجهة تعمل، وسنربطها بمحرك ذكاء اصطناعي في الخطوة التالية.\n")
    chat.configure(state="disabled")
    chat.see(tk.END)
    entry.delete(0, tk.END)

tk.Button(
    root, text="إرسال",
    command=send,
    bg="#00bcd4", fg="black"
).pack(pady=10)

root.mainloop()
