package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ssduo.lockdownsevaapp.R;

import comm.mrspdd.lockdownsevaapp.Models.CustomerModelClass;

public class RegisterFormActivity extends AppCompatActivity {
    EditText Cname, Caddress;
    String address, name;
    DatabaseReference databaseStudent;
    String Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        Phone = getIntent().getStringExtra("phoneNo");
        Caddress = (EditText) findViewById(R.id.address);
        Cname = (EditText) findViewById(R.id.name);


    }

    public void GotoMainActivity(View view) {
        if (Cname.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Your Name", Toast.LENGTH_SHORT).show();
        } else if (Caddress.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
        } else {
            databaseStudent = FirebaseDatabase.getInstance().getReference();

            address = Caddress.getText().toString();
            name = Cname.getText().toString();

            databaseStudent.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        CustomerModelClass customer = new CustomerModelClass(Phone, address, name);
                        databaseStudent.child("Customers").child(Phone).setValue(customer);
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterFormActivity.this, MainActivity.class);
                       intent.putExtra("phoneNo",Phone);
                        startActivity(intent);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }




}
