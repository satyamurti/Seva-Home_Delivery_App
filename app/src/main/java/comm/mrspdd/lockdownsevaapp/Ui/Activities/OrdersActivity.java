package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ssduo.lockdownsevaapp.R;

import java.util.ArrayList;
import java.util.List;

import comm.mrspdd.lockdownsevaapp.Adapters.OrderHistoryViewsAdapter;
import comm.mrspdd.lockdownsevaapp.Models.OrderHistoryModelClass;
import comm.mrspdd.lockdownsevaapp.customLoadingBar;

public class OrdersActivity extends AppCompatActivity implements OrderHistoryViewsAdapter.OnNoteListener {

    private DatabaseReference mDatabaseRef, ref;
    comm.mrspdd.lockdownsevaapp.customLoadingBar customLoadingBar = new customLoadingBar(this);
    String Category ,Peth;
    private RecyclerView mRecyclerView;
    private OrderHistoryViewsAdapter mAdapter;
    private List<OrderHistoryModelClass> shops;
    String phone , name , address;
    String shopname, shopimage, shopaddress, shopphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        shops = new ArrayList<>();

        Bundle bundle1 = getIntent().getExtras();
        Boolean Hack1;
        phone = bundle1.getString("phone");
        name = bundle1.getString("name");
        address = bundle1.getString("address");
        SingleChoice();


        mRecyclerView = findViewById(R.id.orderList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void loaddata() {

        customLoadingBar.startLoader();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        ref = mDatabaseRef.child("OrdersActivity").child(Category.toString()).child(phone);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                shops.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    OrderHistoryModelClass upload = postSnapshot.getValue(OrderHistoryModelClass.class);
                    shops.add(upload);

                }
                setadapter(shops);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                customLoadingBar.dismissLoader();
                Toast.makeText(OrdersActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setadapter(List<OrderHistoryModelClass> shops) {
        customLoadingBar.dismissLoader();
        mAdapter = new OrderHistoryViewsAdapter(OrdersActivity.this, shops, this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onNoteClickkk(int position) {
        Peth = shops.get(position).getPeth();

        Bundle basket;
        basket = new Bundle();
        basket.putString("name",name);
        basket.putString("phone",phone);
        basket.putString("address",address);
        Log.d("GGGG", "onNoteClickkk:$ "+name+phone+" "+address+" "+Peth+Category+" "+shops.get(position).getShopphone());
        Intent intent = new Intent(this, ShopChatActivity.class);
        basket.putString("ShopName",shops.get(position).getShopname());
        basket.putString("ShopImage","ggg");
        basket.putString("ShopAddress","");
        basket.putString("ShopPhone",shops.get(position).getShopphone());
        basket.putString("Peth",Peth);
        basket.putString("Category",Category);
        intent.putExtras(basket);
        startActivity(intent);
    }

    private void SingleChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrdersActivity.this);
        builder.setTitle("Select Category");
        final String[] charSequences = new String[]{"Medical Store", "Kirana Store","Vegitables","Hotel Parsel"};
        builder.setItems(charSequences, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        charSequences[which] + "  Selected", Toast.LENGTH_LONG)
                        .show();
                Category = charSequences[which];
                dialog.dismiss();
                loaddata();

            }
        });
        builder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void ChangeCategory(View view) {
        SingleChoice();
    }
}
