package com.foadia.champions.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foadia.champions.Models.UserModel;
import com.foadia.champions.R;

import java.util.ArrayList;
import java.util.List;

public class PainterAdapter extends RecyclerView.Adapter<PainterAdapter.UserViewHolderDraw> implements Filterable {
    private Context context;
    private List<UserModel> ModelList;
    private List<UserModel> PaintersListFiltered;
    private PainterAdapter.PainterAdapterListener listener;

    public PainterAdapter(Context context, List<UserModel> contactList, PainterAdapter.PainterAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.PaintersListFiltered = contactList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    PaintersListFiltered = ModelList;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelList) {

                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    PaintersListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = PaintersListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                PaintersListFiltered = (ArrayList<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public UserViewHolderDraw onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drawing_hz, parent, false);
        return new PainterAdapter.UserViewHolderDraw(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolderDraw holder, int position) {
        final UserModel user = PaintersListFiltered.get(position);
        holder.PainterTxtUserName.setText(""+user.getUserName());
        holder.PainterTxtAddress.setText(""+user.getCountryId()+","+user.getCityId());
    }

    public long getItemId(int position) {
        return position;
    }

    public void setList(ArrayList<UserModel> userList){
        this.ModelList = userList;
        notifyDataSetChanged();
    }

    public void setListAll(ArrayList<UserModel> ModelList) {
        this.PaintersListFiltered = ModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return PaintersListFiltered.size();
    }

    public interface PainterAdapterListener {
        void onContactSelected(UserModel userModel);
    }

    public class UserViewHolderDraw extends RecyclerView.ViewHolder {
        TextView PainterTxtUserName,PainterTxtAddress;
        public UserViewHolderDraw(@NonNull View itemView) {
            super(itemView);
            PainterTxtUserName = itemView.findViewById(R.id.PainterUserName);
            PainterTxtAddress = itemView.findViewById(R.id.PainterTXTAddress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactSelected(PaintersListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}
