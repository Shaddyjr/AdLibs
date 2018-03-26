package com.example.asc_guest.adlibs;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.widget.TextView;

/** Represents an Ad Lib Word.
 * @author Mahdi Shadkamfarrokhi
 * @author mahdis.pw
 * @version 1.0
 * @since 2018-03-25
 */

class Word {
    String wordType;
    EditText userWord;
    TextView tv;
    private String word;

    /**
     * Word constructor
     * @param wordType the type of word as hint for user (noun, adj, verb, etc.)
     */
    Word(String wordType) {
        this.wordType = wordType;
    }

    String getUserWord(){
        if(word!=null){
            return word;
        }

        String _word = userWord.getText().toString();
        if (!_word.matches("")){
            word = _word;
        }
        return _word;
    }

    void turnRed(){
        tv.setTextColor(Color.parseColor("#FF0000"));
    }

    void turnDefault(){
        tv.setTextColor(ContextCompat.getColor(tv.getContext(), R.color.colorAccent));
    }

}
