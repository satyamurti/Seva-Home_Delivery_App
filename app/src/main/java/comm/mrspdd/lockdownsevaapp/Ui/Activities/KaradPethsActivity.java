package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ssduo.lockdownsevaapp.R;
///////////////////////////////////////////////////////////////////////////
// Made with ❤  by Satyamurti
///////////////////////////////////////////////////////////////////////////
public class KaradPethsActivity extends AppCompatActivity {
    String name, phone, address, Category;
    Bundle basket;
    String pethname;
    Spinner pethname1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karad_peths);
        pethname1 = findViewById(R.id.pethssp);
        Bundle bundle1 = getIntent().getExtras();
        assert bundle1 != null;
        name = bundle1.getString("name");
        phone = bundle1.getString("phone");
        address = bundle1.getString("address");
        Category = bundle1.getString("Category");
        Toast.makeText(this, Category, Toast.LENGTH_SHORT).show();
        basket = new Bundle();
        basket.putString("name", name);
        basket.putString("phone", phone);
        basket.putString("address", address);

        basket.putString("Category", Category);


    }

    public void Go(View view) {
        pethname = pethname1.getSelectedItem().toString();
        Toast.makeText(this, "peth name is "+ pethname, Toast.LENGTH_SHORT).show();
        if (Category.equals("1")) {
                    Intent intent = new Intent(KaradPethsActivity.this, KiranaStoreActivity.class);
                    basket.putString("Peth", pethname);
                    intent.putExtras(basket);
                    startActivity(intent);

                } else if (Category.equals("2")) {
                    Intent intent = new Intent(KaradPethsActivity.this, MedicineActivity.class);
                    basket.putString("Peth", pethname);
                    intent.putExtras(basket);
                    startActivity(intent);
                }
    }
}