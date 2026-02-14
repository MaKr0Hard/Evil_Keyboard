package com.example.evilkeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.KeyEvent;

public class EvilKeyboard extends InputMethodService {

    private String character = "\\u";

    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard_view, null);

        // Chiffres
        setKey(view, R.id.key_1, "1");
        setKey(view, R.id.key_2, "2");
        setKey(view, R.id.key_3, "3");
        setKey(view, R.id.key_4, "4");
        setKey(view, R.id.key_5, "5");
        setKey(view, R.id.key_6, "6");
        setKey(view, R.id.key_7, "7");
        setKey(view, R.id.key_8, "8");
        setKey(view, R.id.key_9, "9");
        setKey(view, R.id.key_0, "0");

        // Lettres
        setKey(view, R.id.key_A, "A");
        setKey(view, R.id.key_B, "B");
        setKey(view, R.id.key_C, "C");
        setKey(view, R.id.key_D, "D");
        setKey(view, R.id.key_E, "E");
        setKey(view, R.id.key_F, "F");

        // Validation
        view.findViewById(R.id.key_validate).setOnClickListener(v -> validate());

        // ENTER
        view.findViewById(R.id.key_enter).setOnClickListener(v -> sendEnter());

        // This is not ai
        view.findViewById(R.id.humans_make_mistakes).setOnClickListener(v -> eraseCharacter());

        view.findViewById(R.id.bk).setOnClickListener(v -> sendBackspace());

        return view;
    }

    private void setKey(View parent, int id, String value) {
        parent.findViewById(id).setOnClickListener(v -> character += value);
    }

    private void validate() {
        if (character.length() > 3 && character.length() < 7) {
            String output = org.apache.commons.text.StringEscapeUtils.unescapeJava(character);
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) ic.commitText(output, 1);
        }
        character = "\\u";
    }

    private void eraseCharacter() {
        character = "\\u";
    }

    private void sendEnter() {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
        }
    }

    private void sendBackspace() {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        }
    }
}
