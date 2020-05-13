package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;
import com.ssduo.lockdownsevaapp.R;

import java.util.ArrayList;
import java.util.List;

import comm.mrspdd.lockdownsevaapp.customLoadingBar;

public class LoginFormActivity extends AppCompatActivity {
    Button login;
    DatabaseReference ref;
    String userid, pass;
    public static final String RMDATA = "rmdata";
    String dbpassword;
    Bundle basket, basket1;
    EditText phonenumber, password;
    comm.mrspdd.lockdownsevaapp.customLoadingBar customLoadingBar = new customLoadingBar(this);
    CheckBox cbRememberme;
    public boolean verificationBl = false;
    List<Integer> listOfImage = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        login = findViewById(R.id.login_button);
        phonenumber = findViewById(R.id.OTPentered);

        final ImageView ig = findViewById(R.id.imageView9);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {

                Picasso.get().load(listOfImage.get(i))
                        .centerCrop()
                        .fit()
                        .into(ig);
                i++;
                if (i > listOfImage.size() - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 3000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 2); //for initial delay..

        listOfImage.add(R.drawable.delivery1);
        listOfImage.add(R.drawable.registration_illu);
        listOfImage.add(R.drawable.medicine1);
        listOfImage.add(R.drawable.chat_illu);
        listOfImage.add(R.drawable.call_illu);
        listOfImage.add(R.drawable.login_illustri);
//        password = findViewById(R.id.password);



//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userid = phonenumber.getText().toString();
//                pass = password.getText().toString();
//                customLoadingBar.startLoader();
//                basket = new Bundle();
//                basket.putString("phone", userid);
//
//                ref = FirebaseDatabase.getInstance().getReference();
//                DatabaseReference dbuser = ref.child("Customers").child(userid.toString());
//
//
//                dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        customLoadingBar.dismissLoader();
//                       // dbpassword = dataSnapshot.child("pass").getValue().toString();
//                        verify(dataSnapshot);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(getApplicationContext(), "database error", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//        });

    }

    public void verify(DataSnapshot dataSnapshot) {
        if (!dataSnapshot.exists()) {
            Toast.makeText(getApplicationContext(), "Please Enter valid user id or password", Toast.LENGTH_LONG).show();
            verificationBl = false;
        } else if (userid.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Username cannot be empty", Toast.LENGTH_LONG).show();
        } else if (pass.equalsIgnoreCase(dataSnapshot.child("pass").getValue().toString())) {
            //  if (userid.equalsIgnoreCase("admin") && pass.equals("admin")) {

            basket.putString("name", dataSnapshot.child("name").getValue().toString());
            basket.putString("address", dataSnapshot.child("address").getValue().toString());
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(basket);
            customLoadingBar.dismissLoader();
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
            verificationBl = true;


            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter valid user id or password", Toast.LENGTH_LONG).show();
            verificationBl = false;
        }
    }

    public void RegisterGo(View view) {
        Intent intent = new Intent(this, RegisterFormActivity.class);
        startActivity(intent);

    }

    public void LoginByOTP(View view) {
        final String phoneNo = phonenumber.getText().toString();
        Intent intent = new Intent(getApplicationContext(), OTPVerificationActivity.class);
        intent.putExtra("phoneNo", phoneNo);
        startActivity(intent);
    }
}
