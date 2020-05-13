package comm.mrspdd.lockdownsevaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;;
import com.squareup.picasso.Picasso;
import com.ssduo.lockdownsevaapp.R;

import java.util.List;

import comm.mrspdd.lockdownsevaapp.Models.ShopsModelClass;

public class ShopsViewsAdapter extends RecyclerView.Adapter<ShopsViewsAdapter.Resourses_ImageViewHolder> {

    OnNoteListener mOnNoteListener;
    private Context mcontext;
    private List<ShopsModelClass> muploads;

    public ShopsViewsAdapter(Context context, List<ShopsModelClass> uploads, OnNoteListener onNoteListener) {
        mcontext = context;
        muploads = uploads;
        this.mOnNoteListener = onNoteListener;

    }

    @NonNull
    @Override
    public Resourses_ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.itemholders_shops, parent, false);
        return new Resourses_ImageViewHolder(v, mOnNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull Resourses_ImageViewHolder holder, int position) {
        ShopsModelClass uploadCurrent = muploads.get(position);
        holder.tvShopName.setText(uploadCurrent.getName());
        holder.tvAddress.setText(uploadCurrent.getAddress());
        Picasso.get()
                .load(R.drawable.grocery1)
                .centerCrop()
                .fit()
                .into(holder.igShopImage);

    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class Resourses_ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvShopName;
        public ImageView igShopImage;
        public TextView tvAddress;
        OnNoteListener OnNoteListener;

        public Resourses_ImageViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            tvShopName = itemView.findViewById(R.id.tvShopName);
            tvAddress = itemView.findViewById(R.id.tvShopAddress);
            igShopImage = itemView.findViewById(R.id.tvShopImage);
            this.OnNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            OnNoteListener.onNoteClickkk(getAdapterPosition());

        }
    }

    public interface OnNoteListener {
        void onNoteClickkk(int position);
    }
}
