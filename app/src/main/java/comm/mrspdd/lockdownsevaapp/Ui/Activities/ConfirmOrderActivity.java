package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssduo.lockdownsevaapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmOrderActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef, ref;
    String shopname, shopimage, shopaddress, shopphone;
    String name, phone, address, Category, pethname;
    Bundle basket = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        Bundle bundle1 = getIntent().getExtras();
        shopname = bundle1.getString("ShopName");
        shopaddress = bundle1.getString("ShopAddress");
        shopimage = bundle1.getString("ShopImage");
        shopphone = bundle1.getString("ShopPhone");
        name = bundle1.getString("name");
        phone = bundle1.getString("phone");
        address = bundle1.getString("address");
        pethname = bundle1.getString("Peth");
        Category = bundle1.getString("Category");
    }

    public void Yes(View view) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        String formattedDate = df.format(c.getTime());

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        ref = mDatabaseRef.child("OrdersActivity").child(Category).child(phone).child(shopphone);
        ref.child("shopname").setValue(shopname);
        ref.child("status").setValue("PENDING");
        ref.child("shopphone").setValue(shopphone);
        ref.child("peth").setValue(pethname);
        ref.child("time").setValue(formattedDate);
        Toast.makeText(this, "Order sent Succesfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ShopChatActivity.class);
        basket.putString("ShopName", shopname);
        basket.putString("ShopAddress", shopaddress);
        basket.putString("ShopImage", shopimage);
        basket.putString("ShopPhone", shopphone);
        basket.putString("name", name);
        basket.putString("phone", phone);
        basket.putString("address", address);
        basket.putString("Peth", pethname);
        basket.putString("Category", Category);
        intent.putExtras(basket);
        startActivity(intent);
        finish();
    }

    public void No(View view) {

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        ref = mDatabaseRef.child("Shops").child(Category).child(pethname).child(shopphone).child("orders");
        ref.child(phone).removeValue();
        Toast.makeText(this, "Cancelling Order....", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ShopChatActivity.class);
        basket.putString("ShopName", shopname);
        basket.putString("ShopAddress", shopaddress);
        basket.putString("ShopImage", shopimage);
        basket.putString("ShopPhone", shopphone);
        basket.putString("name", name);
        basket.putString("phone", phone);
        basket.putString("address", address);
        basket.putString("Peth", pethname);
        basket.putString("Category", Category);
        intent.putExtras(basket);
        startActivity(intent);
        finish();
    }
}
