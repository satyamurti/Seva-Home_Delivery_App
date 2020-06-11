package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.ssduo.lockdownsevaapp.R;

import java.io.ByteArrayOutputStream;

import comm.mrspdd.lockdownsevaapp.customLoadingBar;
///////////////////////////////////////////////////////////////////////////
// Made with ❤  by Satyamurti
///////////////////////////////////////////////////////////////////////////
public class ShopChatActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    TextView tvProgrss;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://lockdown-seva-app.appspot.com");
    String name, phone, address;
    comm.mrspdd.lockdownsevaapp.customLoadingBar customLoadingBar = new customLoadingBar(this);
    Bundle basket = new Bundle();

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    Intent intent = getIntent();
    Bitmap bitmap;
    String downloadURL = "no np";
    String pethname;
    String Category;
    String shopname, shopimage, shopaddress, shopphone;
    TextView tvshopname, tvshopaddress, tvMessage;
    ImageView igimage, igdialbutton;
    private DatabaseReference mDatabaseRef, ref, refImageurl;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Uri imageFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shop_chat);
        Bundle bundle1 = getIntent().getExtras();
         Boolean Hack1;
        shopname = bundle1.getString("ShopName");
        shopaddress = bundle1.getString("ShopAddress");
        shopimage = bundle1.getString("ShopImage");
        shopphone = bundle1.getString("ShopPhone");
        name = bundle1.getString("name");
        phone = bundle1.getString("phone");
        address = bundle1.getString("address");
        pethname = bundle1.getString("Peth");
        Category = bundle1.getString("Category");

        tvshopname = findViewById(R.id.tvShopName1);
        tvshopaddress = findViewById(R.id.tvShopAddress1);
        igdialbutton = findViewById(R.id.CallButton);
        igimage = findViewById(R.id.tvShopImage1);
        tvMessage = findViewById(R.id.tvmessage);
        tvProgrss =findViewById(R.id.tvProgress);
        mProgressBar =findViewById(R.id.progressbar);


        tvshopname.setText(shopname);
        tvshopaddress.setText(shopaddress);
        ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbuser = ref.child("Shops").child(Category).child(pethname).child(phone);
        dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("imageurl").exists()) {
                    String imageurl = dataSnapshot.child("imageurl").getValue().toString();
                    address = dataSnapshot.child("address").getValue().toString();
                    name = dataSnapshot.child("name").getValue().toString();
                    phone = dataSnapshot.child("phone").getValue().toString();
                    if (!imageurl.isEmpty()) {
                        Picasso.get()
                                .load(imageurl)
                                .into(igimage);
                    }else {
                        Picasso.get()
                                .load(R.drawable.medicine1)
                                .centerCrop()
                                .fit()
                                .into(igimage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "database error", Toast.LENGTH_LONG).show();
            }
        });




    }


    public void addPhoto(View view) {
       Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*") ;
        startActivityForResult(intent, 1);
//        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
//                == PackageManager.PERMISSION_DENIED){
//            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 401);
//        }
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        imageFileUri = Uri.fromFile(new File("path/to/images/rivers.jpg"));
//        takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, 1);
//        }
    }

    public void addmessage(View view) {
        ref = mDatabaseRef.child("Shops").child(Category).child(pethname).child(shopphone).child("orders");
        ref.child(phone).child("phone").setValue(phone);

        Intent intent = new Intent(this, MessegingActivity.class);
        basket.putString("ShopName", shopname);
        basket.putString("ShopAddress", shopaddress);
        basket.putString("ShopImage", shopimage);
        basket.putString("ShopPhone", shopphone);
        basket.putString("name", name);
        basket.putString("phone", phone);
        basket.putString("address", address);
        intent.putExtras(basket);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            bitmap = (Bitmap) extras.get("data");

            imageFileUri = data.getData();

            StorageReference mountainsRef = storageRef.child(phone + shopphone + ".png");

// Create a reference to 'images/mountains.jpg'
            StorageReference mountainImagesRef = storageRef.child("images/" + phone + shopphone + ".png");
            final StorageReference mountainImagesRef2 = storageRef.child("images/" + phone + shopphone + ".png");

// While the file names are the same, the references point to different files
            mountainsRef.getName().equals(mountainImagesRef.getName());    // true
            mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//            byte[] data1 = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putFile(imageFileUri);
            mProgressBar.setVisibility(View.VISIBLE);
            tvProgrss.setVisibility(View.VISIBLE);
            mProgressBar.setIndeterminate(false);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mProgressBar.setProgress((int) progress, true);
                    }
                    tvProgrss.setText(progress + " %");
                    Log.d("Photo_Progress", "onProgress: file " + progress + " % uploaded");

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful()) ;
                    Uri downloadUrl222 = urlTask.getResult();

                    downloadURL = String.valueOf(downloadUrl222);
                    tvProgrss.append(" File Uploaded");
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ShopChatActivity.this, R.string.fdd, Toast.LENGTH_SHORT).show();
//                    Log.d("satya", downloadURL.toString());
                    refImageurl = mDatabaseRef.child("Shops").child(Category).child(pethname).child(shopphone).child("orders");
                    refImageurl.child(phone).child("imageurl").setValue(downloadURL);
                    refImageurl.child(phone).child("phone").setValue(phone);
                    refImageurl.child(phone).child("address").setValue(address);
                    refImageurl.child(phone).child("name").setValue(name);

                    Intent intent = new Intent(ShopChatActivity.this, ConfirmOrderActivity.class);
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
                }

            }).addOnCanceledListener(new OnCanceledListener() {
                @Override
                public void onCanceled() {
                    tvProgrss.append(" Cancelled");
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            });

        }
    }

    public void CallShop(View view) {

        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:" + shopphone));
        if (ActivityCompat.checkSelfPermission(ShopChatActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 401);

        }
        startActivity(phoneIntent);


    }
}