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

public class ChefAdapter extends RecyclerView.Adapter<ChefAdapter.UserViewHolderCook> implements Filterable {

    private Context context;
    private List<UserModel> ModelList;
    private List<UserModel> ChefsListFiltered;
    private ChefAdapter.ChefAdapterListener listener;

    public ChefAdapter (Context context, List<UserModel> contactList, ChefAdapter.ChefAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.ChefsListFiltered = contactList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    ChefsListFiltered = ModelList;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelList) {

                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    ChefsListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ChefsListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ChefsListFiltered = (ArrayList<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ChefAdapter.UserViewHolderCook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cooking_hz,parent,false);
        return new ChefAdapter.UserViewHolderCook(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefAdapter.UserViewHolderCook holder, int position) {
        final UserModel user = ChefsListFiltered.get(position);
        holder.ChefTxtUserName.setText("" + user.getUserName());
        holder.ChefTxtAddress.setText("" + user.getCountryId() + "," + user.getCityId());

    }
    public long getItemId(int position) {
        return position;
    }

    public void setList(ArrayList<UserModel> userList) {
        this.ModelList = userList;
        notifyDataSetChanged();
    }

    public void setListAll(ArrayList<UserModel> ModelList) {
        this.ChefsListFiltered = ModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ChefsListFiltered.size();
    }

    public interface ChefAdapterListener {
        void onContactSelected(UserModel userModel);
    }

    public class UserViewHolderCook extends RecyclerView.ViewHolder {
        TextView ChefTxtUserName,ChefTxtAddress;
        public UserViewHolderCook(@NonNull View itemView) {
            super(itemView);
            ChefTxtUserName = itemView.findViewById(R.id.ChefUserName);
            ChefTxtAddress = itemView.findViewById(R.id.ChefTXTAddress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactSelected(ChefsListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}
