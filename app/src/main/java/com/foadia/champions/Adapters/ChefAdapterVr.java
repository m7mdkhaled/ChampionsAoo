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

public class ChefAdapterVr extends RecyclerView.Adapter<ChefAdapterVr.UserViewHolderCookVr> implements Filterable {

    private Context context;
    private List<UserModel> ModelList;
    private List<UserModel> ChefsVListFiltered;
    private ChefAdapterVr.ChefAdapterVListener listener;

    public ChefAdapterVr (Context context, List<UserModel> contactList, ChefAdapterVr.ChefAdapterVListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.ChefsVListFiltered = contactList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    ChefsVListFiltered = ModelList;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelList) {

                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    ChefsVListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ChefsVListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ChefsVListFiltered = (ArrayList<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ChefAdapterVr.UserViewHolderCookVr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cooking_vr,parent,false);
        return new ChefAdapterVr.UserViewHolderCookVr(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefAdapterVr.UserViewHolderCookVr holder, int position) {
        final UserModel user = ChefsVListFiltered.get(position);
        holder.ChefTxtUserNameVr.setText(""+user.getUserName());
        holder.ChefTxtAddressVr.setText(""+user.getCountryId() + "," + user.getCityId());
        if (user.getComments().size() == 0 ) {
            holder.ChefRate.setText("0");
        }
        else {
            holder.ChefRate.setText(""+user.getComments().get(position).getStarRate());
        }
    }
    public long getItemId(int position) {
        return position;
    }

    public void setList(ArrayList<UserModel> userList) {
        this.ModelList = userList;
        notifyDataSetChanged();
    }

    public void setListAll(ArrayList<UserModel> ModelList) {
        this.ChefsVListFiltered = ModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ChefsVListFiltered.size();
    }

    public class UserViewHolderCookVr extends RecyclerView.ViewHolder {
        TextView ChefTxtUserNameVr,ChefTxtAddressVr,ChefRate;
        public UserViewHolderCookVr(@NonNull View itemView) {
            super(itemView);
            ChefTxtUserNameVr = itemView.findViewById(R.id.ChefTxtUserNameV);
            ChefTxtAddressVr = itemView.findViewById(R.id.ChefTXTAddressV);
            ChefRate = itemView.findViewById(R.id.ChefRate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactVSelected(ChefsVListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface ChefAdapterVListener {
        void onContactVSelected(UserModel userModelV);
    }
}

