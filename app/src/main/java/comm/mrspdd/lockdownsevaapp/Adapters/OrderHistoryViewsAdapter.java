package comm.mrspdd.lockdownsevaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ssduo.lockdownsevaapp.R;

import java.util.List;

;import comm.mrspdd.lockdownsevaapp.Models.OrderHistoryModelClass;

public class OrderHistoryViewsAdapter extends RecyclerView.Adapter<OrderHistoryViewsAdapter.Resourses_ImageViewHolder> {

    private OnNoteListener mOnNoteListener;
    private Context mcontext;
    private List<OrderHistoryModelClass> muploads;

    public OrderHistoryViewsAdapter(Context context, List<OrderHistoryModelClass> uploads, OnNoteListener onNoteListener) {
        mcontext = context;
        muploads = uploads;
        this.mOnNoteListener = onNoteListener;

    }

    @NonNull
    @Override
    public Resourses_ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.itemholders_history, parent, false);
        return new Resourses_ImageViewHolder(v, mOnNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull Resourses_ImageViewHolder holder, int position) {
        OrderHistoryModelClass uploadCurrent = muploads.get(position);
        holder.tvShopName.setText(uploadCurrent.getShopname());
        holder.tvTime.setText(uploadCurrent.getTime());
        String Hack = ""+uploadCurrent.getStatus().toString();
        holder.tvpending.setText(""+uploadCurrent.getStatus());
        Toast.makeText(mcontext, Hack, Toast.LENGTH_SHORT).show();
//        switch (Hack){
//            case "REJECTED":{
//                holder.tvrejected.setVisibility(View.VISIBLE);
//                holder.tvapproved.setVisibility(View.INVISIBLE);
//                holder.tvpending.setVisibility(View.INVISIBLE);
//            }
//            case "PENDING":{
//                holder.tvrejected.setVisibility(View.INVISIBLE);
//                holder.tvapproved.setVisibility(View.INVISIBLE);
//                holder.tvpending.setVisibility(View.VISIBLE);
//            }
//            case "APPROVED":{
//                holder.tvrejected.setVisibility(View.INVISIBLE);
//                holder.tvapproved.setVisibility(View.VISIBLE);
//                holder.tvpending.setVisibility(View.INVISIBLE);
//            }
//        }
    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public static class Resourses_ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvShopName;
        TextView tvTime;
        TextView tvpending;
        TextView tvapproved;
        TextView tvrejected;

        OnNoteListener OnNoteListener;

        Resourses_ImageViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            tvShopName = itemView.findViewById(R.id.tvShopName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvpending = itemView.findViewById(R.id.tvpending);
//            tvapproved = itemView.findViewById(R.id.tvapproved);
//            tvrejected = itemView.findViewById(R.id.tvrejected);

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
