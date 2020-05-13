package comm.mrspdd.lockdownsevaapp.Ui.Activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ssduo.lockdownsevaapp.R;

import java.util.ArrayList;
import java.util.Date;

import comm.mrspdd.lockdownsevaapp.Models.MessageModelClass;
import comm.mrspdd.lockdownsevaapp.MessegingDataSource;

public class MessegingActivity extends AppCompatActivity implements View.OnClickListener,
        MessegingDataSource.MessagesCallbacks {
    public static final String USER_EXTRA = "USER";

    public static final String TAG = "ChatActivity";
    String shopname, shopimage, shopaddress, shopphone;
    String name, phone, address;
    private ArrayList<MessageModelClass> mMessages;
    private MessagesAdapter mAdapter;
    private ListView mListView;
    private Date mLastMessageDate = new Date();
    private String mConvoId;
    private MessegingDataSource.MessagesListener mListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messeging);

        Bundle bundle1 = getIntent().getExtras();
        shopname = bundle1.getString("ShopName");
        shopaddress = bundle1.getString("ShopAddress");
        shopimage = bundle1.getString("ShopImage");
        shopphone = bundle1.getString("ShopPhone");
        name = bundle1.getString("name");
        phone = bundle1.getString("phone");
        address = bundle1.getString("address");

        mListView = (ListView) findViewById(R.id.messages_list);
        mMessages = new ArrayList<>();
        mAdapter = new MessagesAdapter(mMessages);
        mListView.setAdapter(mAdapter);

        setTitle(phone);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button sendMessage = (Button) findViewById(R.id.send_message);
        sendMessage.setOnClickListener((View.OnClickListener) MessegingActivity.this);

        String ids = phone + shopphone;
        mConvoId = ids;

        mListener = MessegingDataSource.addMessagesListener(mConvoId, this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        hideKeyboard();
        EditText newMessageView = (EditText) findViewById(R.id.new_message);
        String newMessage = newMessageView.getText().toString();
        if (!(newMessage.equals(""))) {

            newMessageView.setText("");
            MessageModelClass msg = new MessageModelClass();
            msg.setDate(new Date());
            msg.setText(newMessage);
            msg.setSender(phone);

            MessegingDataSource.saveMessage(msg, mConvoId, phone);
        }

    }

    @Override
    public void onMessageAdded(MessageModelClass message) {
        mMessages.add(message);
        mAdapter.notifyDataSetChanged();

    }
    public void hideKeyboard() {
        try {
            InputMethodManager inputmanager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputmanager != null) {
                inputmanager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception var2) {
        }

    }

    private class MessagesAdapter extends ArrayAdapter<MessageModelClass> {
        MessagesAdapter(ArrayList<MessageModelClass> messages) {
            super(MessegingActivity.this, R.layout.message, R.id.message, messages);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            MessageModelClass message = getItem(position);

            TextView nameView = (TextView) convertView.findViewById(R.id.message);
            nameView.setText(message.getText());

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nameView.getLayoutParams();

            int sdk = Build.VERSION.SDK_INT;
            if (message.getSender().equals(phone)) {
                if (sdk >= Build.VERSION_CODES.JELLY_BEAN) {
                    nameView.setBackgroundResource(R.drawable.bubble_right_green);
                } else {

                    nameView.setBackgroundResource(R.drawable.bubble_right_green);
                }
                layoutParams.gravity = Gravity.RIGHT;
            } else {
                if (sdk >= Build.VERSION_CODES.JELLY_BEAN) {
                    nameView.setBackgroundResource(R.drawable.bubble_left_gray);
                } else {
                    nameView.setBackgroundResource(R.drawable.bubble_left_gray);
                }
                layoutParams.gravity = Gravity.LEFT;
            }

            nameView.setLayoutParams(layoutParams);


            return convertView;
        }
    }
}
