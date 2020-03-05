package change.com.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText email,password;
    private Button login;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        login = findViewById(R.id.button4);
        auth = FirebaseAuth.getInstance();
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String em = email.getText().toString();
                        String pa = password.getText().toString();
                        loginUser(em,pa);
                    }

                    private void loginUser(final String em, String pa) {
                        auth.signInWithEmailAndPassword(em,pa).addOnSuccessListener(
                                new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this,MainActivity.class));
                                        finish();
                                    }
                                }
                        );
                    }
                }
        );
    }
}
