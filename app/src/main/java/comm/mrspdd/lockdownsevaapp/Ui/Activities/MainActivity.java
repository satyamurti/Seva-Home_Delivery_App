package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import com.ssduo.lockdownsevaapp.R;

import java.util.ArrayList;
import java.util.List;
///////////////////////////////////////////////////////////////////////////
// Made with ❤  by Satyamurti
///////////////////////////////////////////////////////////////////////////
public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseStudent;
    private DrawerLayout dl;
    Boolean hack = false;
    private NavigationView nv;
    private static long back_pressed;
    CardView cardView;
    ImageView ig, sideview;
    Bundle basket;
    DatabaseReference ref, ref1, mDatabaseRef;
    TextView tvName, ggg;
    String name, phone, address, Category;
    List<Integer> listOfImage = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = getIntent().getStringExtra("phoneNo");
        getCustomerDetails(phone);

        ig = findViewById(R.id.imageMainView);
        ggg = findViewById(R.id.textViewKarad);
        sideview = findViewById(R.id.sideview);
        listOfImage.add(R.drawable.delivery1);
        listOfImage.add(R.drawable.registration_illu);
        listOfImage.add(R.drawable.medicine1);
        listOfImage.add(R.drawable.chat_illu);
        listOfImage.add(R.drawable.call_illu);
        listOfImage.add(R.drawable.login_illustri);


        ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbuser = ref.child("Shops").child("Kirana Store");
        dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String gg = dataSnapshot.child("kirana_key").getValue().toString();
                if (gg.equals("1")) {
                    hack = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "database error", Toast.LENGTH_LONG).show();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        FirebaseMessaging.getInstance().subscribeToTopic("client")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
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


        dl = (DrawerLayout) findViewById(R.id.activity_main);
        sideview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(Gravity.LEFT);

                nv = (NavigationView) findViewById(R.id.nv);
                nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.logout:
                                Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginFormActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case R.id.settings:
                                Toast.makeText(MainActivity.this, "Settings will be added soon", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.aboutus:
                                Intent intent2 = new Intent(MainActivity.this, OrdersActivity.class);
                                basket = new Bundle();
                                basket.putString("phone", phone);
                                basket.putString("name",name);
                                basket.putString("address",address);
                                intent2.putExtras(basket);
                                startActivity(intent2);
                                break;
                            default:
                                return true;
                        }


                        return true;

                    }
                });
            }
        });


    }

    private void getCustomerDetails(final String phone) {

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        ref1 = mDatabaseRef.child("Customers");
        ref1.orderByChild("phone").equalTo(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    name = postSnapshot.child("name").getValue().toString();
                    address = postSnapshot.child("address").getValue().toString();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "Network Error Code 415", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void groceryGo(View view) {

        if (hack) {
            Category = "1";
            Intent intent = new Intent(this, KaradPethsActivity.class);
            basket = new Bundle();
            basket.putString("name", name);
            basket.putString("phone", phone);
            basket.putString("address", address);
            basket.putString("Category", Category);
            intent.putExtras(basket);
            startActivity(intent);
        }
    }

    public void midicinego(View view) {
        Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
        Category = "2";
        Intent intent = new Intent(this, KaradPethsActivity.class);
        basket = new Bundle();
        basket.putString("name", name);
        basket.putString("phone", phone);
        basket.putString("address", address);
        basket.putString("Category", Category);
        intent.putExtras(basket);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

}
