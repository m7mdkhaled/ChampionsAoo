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

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.UserViewHolderSing> implements Filterable {
    private Context context;
    private List<UserModel> ModelList;
    private List<UserModel> UsersListFiltered;
    private SingerAdapter.SingerAdapterListener listener;

    public SingerAdapter(Context context, List<UserModel> contactList, SingerAdapter.SingerAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.UsersListFiltered = contactList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    UsersListFiltered = ModelList;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelList) {

                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    UsersListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = UsersListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                UsersListFiltered = (ArrayList<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public SingerAdapter.UserViewHolderSing onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_singing_hz, parent, false);
        return new SingerAdapter.UserViewHolderSing(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SingerAdapter.UserViewHolderSing holder, int position) {
        final UserModel user = UsersListFiltered.get(position);
        holder.SingerTxtUserName.setText(""+user.getUserName());
        holder.SingerTxtAddress.setText(""+user.getCountryId()+","+user.getCityId());
    }
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return UsersListFiltered.size();
    }
    public void setList(ArrayList<UserModel> userList){
        this.ModelList = userList;
        notifyDataSetChanged();
    }

    public void setListAll(ArrayList<UserModel> ModelList) {
        this.UsersListFiltered = ModelList;
        notifyDataSetChanged();
    }

    public interface SingerAdapterListener {
        void onContactSelected(UserModel userModel);
    }

    public class UserViewHolderSing extends RecyclerView.ViewHolder {
        public TextView SingerTxtUserName,SingerTxtAddress;
        public UserViewHolderSing(@NonNull View itemView) {
            super(itemView);
            SingerTxtUserName = itemView.findViewById(R.id.SingerUserName);
            SingerTxtAddress = itemView.findViewById(R.id.SingerTXTAddress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {

                    listener.onContactSelected(UsersListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}
