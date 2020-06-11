package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ssduo.lockdownsevaapp.R;

///////////////////////////////////////////////////////////////////////////
// Made with ❤  by Satyamurti
///////////////////////////////////////////////////////////////////////////
public class AboutusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        finish();
                        break;
                    case R.id.action_messages:
                        Toast.makeText(AboutusActivity.this, "Will Be Added Soon", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
