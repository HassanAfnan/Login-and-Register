package change.com.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText email,password;
    private Button register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        register = findViewById(R.id.button3);
        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String em = email.getText().toString();
                        String pa = password.getText().toString();
                        if(TextUtils.isEmpty(em)||TextUtils.isEmpty(pa))
                        {
                            Toast.makeText(Register.this,"Empty Cardinals",Toast.LENGTH_SHORT).show();
                        }
                        else if(pa.length() < 6)
                        {
                            Toast.makeText(Register.this,"Password Is Too Weak",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            registerUser(em,pa);
                        }
                    }
                }
        );
    }

    private void registerUser(String em, String pa) {
        auth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(
                Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, StartActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Register.this,"Register Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
