package com.cyberai.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private LinearLayout messages;
    private ScrollView scroll;
    private EditText input;

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private TextView makeText(String text, int size, int color) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setPadding(dp(12), dp(9), dp(12), dp(9));
        return view;
    }

    private void addMessage(String sender, String text, boolean user) {
        TextView message = makeText(
            sender + "\n" + text,
            15,
            Color.WHITE
        );

        message.setBackgroundColor(
            Color.parseColor(user ? "#174A70" : "#263746")
        );

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                -1,
                -2
            );

        params.setMargins(0, dp(5), 0, dp(5));
        messages.addView(message, params);

        scroll.post(() -> scroll.fullScroll(View.FOCUS_DOWN));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#101820"));
        root.setPadding(dp(12), dp(8), dp(12), dp(8));

        TextView title = makeText("CyberAI", 25, Color.WHITE);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(-1, dp(55)));

        TextView subtitle = makeText(
            "Cybersecurity • Termux • Programming • OSINT",
            12,
            Color.parseColor("#65D6AD")
        );
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle);

        scroll = new ScrollView(this);
        messages = new LinearLayout(this);
        messages.setOrientation(LinearLayout.VERTICAL);
        messages.setPadding(dp(3), dp(8), dp(3), dp(8));

        scroll.addView(messages);

        root.addView(
            scroll,
            new LinearLayout.LayoutParams(
                -1,
                0,
                1
            )
        );

        LinearLayout bottom = new LinearLayout(this);
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        bottom.setGravity(Gravity.CENTER_VERTICAL);

        input = new EditText(this);
        input.setHint("اكتب سؤالك هنا...");
        input.setTextColor(Color.WHITE);
        input.setHintTextColor(Color.LTGRAY);
        input.setTextSize(15);
        input.setSingleLine(false);
        input.setMaxLines(3);
        input.setBackgroundColor(Color.parseColor("#263746"));
        input.setPadding(dp(10), dp(5), dp(10), dp(5));

        bottom.addView(
            input,
            new LinearLayout.LayoutParams(0, dp(52), 1)
        );

        Button send = new Button(this);
        send.setText("إرسال");

        bottom.addView(
            send,
            new LinearLayout.LayoutParams(-2, dp(52))
        );

        root.addView(bottom);
        setContentView(root);

        addMessage(
            "CyberAI",
            "مرحبًا! أنا مساعدك في الأمن السيبراني والبرمجة. " +
            "واجهة المحادثة جاهزة، لكن محرك الذكاء الاصطناعي لم يُربط بعد.",
            false
        );

        send.setOnClickListener(v -> {
            String question = input.getText().toString().trim();

            if (question.isEmpty()) {
                input.setError("اكتب سؤالًا أولًا");
                return;
            }

            addMessage("أنت", question, true);
            input.setText("");

            InputMethodManager imm =
                (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
                );

            if (imm != null) {
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
            }

            addMessage(
                "CyberAI",
                "وصل سؤالك بنجاح. الخطوة التالية هي ربط التطبيق " +
                "بمحرك ذكاء اصطناعي ليتمكن من الإجابة عنه.",
                false
            );
        });
    }
}
