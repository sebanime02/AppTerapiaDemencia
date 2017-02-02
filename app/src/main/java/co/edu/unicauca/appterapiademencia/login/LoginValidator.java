package co.edu.unicauca.appterapiademencia.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by ENF on 02/02/2017.
 */

 public abstract class LoginValidator implements TextWatcher {
    private EditText editText;

    public LoginValidator(EditText editText)
    {
        this.editText = editText;
    }
    public abstract void validate(EditText editText,String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = editText.getText().toString();
        validate(editText, text);
        validateSpecialCharacters(editText,text);
    }

    public abstract void validateSpecialCharacters(EditText editText,String text);

}
