package edu.temple.signupform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final EditText name = (EditText) findViewById(R.id.editText);
        final EditText email = (EditText) findViewById(R.id.editText4);
        final EditText password = (EditText) findViewById(R.id.editText5);
        final EditText confirmPassword = (EditText) findViewById(R.id.editText6);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals("") || confirmPassword.getText().toString().equals("") || name.getText().toString().equals("") || email.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please, fill in all fields.", Toast.LENGTH_LONG).show();
                }else if(!password.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Your passwords do not match, try again.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Changes saved!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
