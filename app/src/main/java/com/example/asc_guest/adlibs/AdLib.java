package com.example.asc_guest.adlibs;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

/** Represents an Ad Lib.
 * @author Mahdi Shadkamfarrokhi
 * @author mahdis.pw
 * @version 1.0
 * @since 2018-03-25
 */
class AdLib {
    private final String paragraph;
    Word[] words;

    /**
     * AdLib constructor
     * @param paragraph String delimited by "$$" for word replacement
     * @param words array of Word objects
     */
    AdLib(String paragraph, Word[] words) {
        this.paragraph = paragraph;
        this.words = words;
    }

    /**
    * @return the paragraph with the user's data as a stylized Spannable
     */
    Spannable getResult(){
        String[] p_Array = paragraph.split("\\$\\$");
        String str = "";

        for (int i = 0; i < p_Array.length; i++){
            str += p_Array[i];
            if(i<words.length){
                str += words[i].getUserWord();
            }
        }

        Spannable sb = new SpannableString( str );

        int start = 0;
        int end;
        for (int i = 0; i < p_Array.length; i++){
            start += p_Array[i].length();
            if(i<words.length){
                int wordL = words[i].getUserWord().length();
                end = start + wordL;
                sb.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                start = end;
            }
        }

        return sb;
    }

    /**
     *
     * @return true if user has input values for all words, false otherwise
     */
    boolean checkUserInput(){
        for (Word word: words){
            if("".equals(word.getUserWord())) return false;
        }
        return true;
    }

    /**
     * highlights red all words without user input and returns words with input to default text color
     */
    void highlightEmpty(){
        for (Word word: words){
            if(word.getUserWord().matches("")) {
                word.turnRed();
            }else{
                word.turnDefault();
            }
        }
    }
}
