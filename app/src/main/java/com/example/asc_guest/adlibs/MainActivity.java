package com.example.asc_guest.adlibs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    AdLib[] adlibs;
    LayoutInflater layoutInflater;

    LinearLayout main_LL_container;
    LinearLayout main_LL;
    LinearLayout results_LL_container;
    TextView resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutInflater      = LayoutInflater.from(this);
        main_LL_container   = (LinearLayout) findViewById(R.id.main_LL_container);
        main_LL             = (LinearLayout) findViewById(R.id.main_LL);
        results_LL_container   = (LinearLayout) findViewById(R.id.results_LL_container);
        resultsView         = (TextView) findViewById(R.id.resultsView);

        //populating data
        createAdLibs();
    }

    /*
    * Makes the main LinearLayout visible and hides else
     */
    private void showMain(){
        main_LL_container.setVisibility(View.VISIBLE);
        results_LL_container.setVisibility(View.GONE);
    }

    /*
    * Makes the resultsLinearLayout visible and hides else
     */
    private void showResults(){
        main_LL_container.setVisibility(View.GONE);
        results_LL_container.setVisibility(View.VISIBLE);
    }
    private void createAdLibs(){
        showMain();
        main_LL.removeAllViews(); // clears potentially old content

        adlibs = new AdLib[1];
        // $$ takes place of word in ordered array (order matters)
        String para = "This is a $$. You should $$ all by yourself. What kind of $$ person are you?";

        Word[] words = new Word[]{
            new Word("noun"),
            new Word("verb"),
            new Word("adjective")
        };
        adlibs[0] = new AdLib(para, words);
        createViews(adlibs[0]);
    }

    public void createAdLibs(View v){
        createAdLibs();
    }

    private void createViews(AdLib adlib){
        for(Word word: adlib.words){
            addView(word);
        }
    }

    private void addView(Word word){
        // NOTE: passing in parent view group with false attachToRoot
        // This is more work, but won't cause additionally added views to be distorted
        final View wordView = layoutInflater.inflate(R.layout.wordoutline, main_LL, false);


        // storing views for later
        word.tv = (TextView) wordView.findViewById(R.id.w_text);
        word.tv.setText(word.wordType);

        word.userWord = (EditText) wordView.findViewById(R.id.e_text);
        word.userWord.onEditorAction(EditorInfo.IME_ACTION_DONE); //hides keyboard
        word.userWord.setHint("Enter " + word.wordType + " here");

        main_LL.addView(wordView);
    }

    /*
    *
     */
    public void submitAns(View v){
        closeKeyboard();
        if(adlibs[0].checkUserInput()){
            showResults();
            resultsView.setText(adlibs[0].getResult());
        }else{
            adlibs[0].highlightEmpty();
            toastError("Incomplete form");
        }
    }

    private void toastError(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }

    private void closeKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

//        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
