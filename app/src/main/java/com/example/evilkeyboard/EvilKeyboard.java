package com.example.evilkeyboard;

import org.apache.commons.text.StringEscapeUtils;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.KeyEvent;

public class EvilKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;
    String character = "\\u";
    String output = "";

    @Override
    public View onCreateInputView() {
        // Charge la vue du clavier
        Log.d("KEYBOARD", "onCreateInputView appelé 1 !");

        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Log.d("KEYBOARD", "onCreateInputView appelé 2 !");
        // Charge le clavier défini dans res/xml/qwerty_keyboard.xml
        keyboard = new Keyboard(this, R.xml.qwerty_keyboard);
        Log.d("KEYBOARD", "onCreateInputView appelé 3 !");
        keyboardView.setKeyboard(keyboard);
        Log.d("KEYBOARD", "onCreateInputView appelé 4 !");
        keyboardView.setOnKeyboardActionListener(this);
        Log.d("KEYBOARD", "onCreateInputView appelé 5 !");
        return keyboardView;

    }

    // Détecte l'appui immédiat (avant le relâchement)
    @Override
    public void onPress(int primaryCode) {
       if (primaryCode == 49) { // 97 = 'a'

                character = character + "1";

        }
        if (primaryCode == 50) {
            character = character + "2";
        }
        if (primaryCode == 51) {
            character = character + "3";
        }
        if (primaryCode == 52) {
            character = character + "4";
        }
        if (primaryCode == 53) {
            character = character + "5";
        }
        if (primaryCode == 54) {
            character = character + "6";
        }
        if (primaryCode == 55) {
            character = character + "7";
        }
        if (primaryCode == 56) {
            character = character + "8";
        }
        if (primaryCode == 57) {
            character = character + "9";
        }
        if (primaryCode == 48) {
            character = character + "0";
        }
        if (primaryCode == 65) {
            character = character + "A";
        }
        if (primaryCode == 66) {
            character = character + "B";
        }
        if (primaryCode == 67) {
            character = character + "C";
        }
        if (primaryCode == 68) {
            character = character + "D";
        }
        if (primaryCode == 69) {
            character = character + "E";
        }
        if (primaryCode == 70) {
            character = character + "F";
        }
        if (primaryCode == 100) {
            InputConnection ic = getCurrentInputConnection();
            if ((character.length() > 3) && (character.length() < 7)) {
                output = StringEscapeUtils.unescapeJava(character);

                if (ic != null) {
                    ic.commitText(output, 1);
                }
            }
            character = "\\u";
            output = "";
        }
        if (primaryCode == -4) {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
            }
        }
        if (primaryCode == -5) {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                ic.deleteSurroundingText(1, 0);

            }
        }
        if (primaryCode == 32) {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                character = "\\u";
                output = "";
            }
        }
    }

    // Détecte le relâchement de la touche
    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
       if (primaryCode != 0) {
           Log.d("KEYBOARD", "Touche pressée");
       }





    }

    @Override public void onRelease(int primaryCode) { }
    @Override public void onText(CharSequence text) { }
    @Override public void swipeLeft() { }
    @Override public void swipeRight() { }
    @Override public void swipeDown() { }
    @Override public void swipeUp() { }
}
