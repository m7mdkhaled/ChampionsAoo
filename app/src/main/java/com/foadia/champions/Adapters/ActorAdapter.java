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

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.UserViewHolderFilm> implements Filterable {

    private Context context;
    private List<UserModel> ModelList;
    private List<UserModel> ActorsListFiltered;
    private ActorAdapter.ActorAdapterListener listener;

    public ActorAdapter(Context context, List<UserModel> contactList, ActorAdapter.ActorAdapterListener listener){
        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.ActorsListFiltered = contactList;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    ActorsListFiltered = ModelList;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelList) {

                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    ActorsListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ActorsListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ActorsListFiltered = (ArrayList<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ActorAdapter.UserViewHolderFilm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filming_hz,parent,false);
        return new ActorAdapter.UserViewHolderFilm(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapter.UserViewHolderFilm holder, int position) {
        final UserModel user = ActorsListFiltered.get(position);
        holder.ActorTxtUserName.setText("" + user.getUserName());
        holder.ActorTxtAddress.setText("" + user.getCountryId() + "," + user.getCityId());
    }
    public long getItemId(int position) {
        return position;
    }

    public void setList(ArrayList<UserModel> userList) {
        this.ModelList = userList;
        notifyDataSetChanged();
    }

    public void setListAll(ArrayList<UserModel> ModelList) {
        this.ActorsListFiltered = ModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ActorsListFiltered.size();
    }

    public class UserViewHolderFilm extends RecyclerView.ViewHolder {
        TextView ActorTxtUserName,ActorTxtAddress;
        public UserViewHolderFilm(@NonNull View itemView) {
            super(itemView);
            ActorTxtUserName = itemView.findViewById(R.id.ActorUserName);
            ActorTxtAddress = itemView.findViewById(R.id.ActorTXTAddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactSelected(ActorsListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface ActorAdapterListener {
        void onContactSelected(UserModel userModel);
    }
}
