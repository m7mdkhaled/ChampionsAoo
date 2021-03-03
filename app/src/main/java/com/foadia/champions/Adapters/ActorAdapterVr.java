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

public class ActorAdapterVr extends RecyclerView.Adapter<ActorAdapterVr.UserViewHolderFilmVr> implements Filterable {

    private Context context;
    private List<UserModel> ModelList;
    private List<UserModel> ActorsVListFiltered;
    private ActorAdapterVr.ActorAdaptorVListener listener;

    public ActorAdapterVr(Context context, List<UserModel> contactList, ActorAdapterVr.ActorAdaptorVListener listener){

        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.ActorsVListFiltered = contactList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    ActorsVListFiltered = ModelList;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelList) {

                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    ActorsVListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ActorsVListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ActorsVListFiltered = (ArrayList<UserModel>) results.values;
                notifyDataSetChanged();
            }

        };
    }

    @NonNull
    @Override
    public ActorAdapterVr.UserViewHolderFilmVr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filming_vr,parent,false);
        return new ActorAdapterVr.UserViewHolderFilmVr(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapterVr.UserViewHolderFilmVr holder, int position) {
        final UserModel user = ActorsVListFiltered.get(position);
        holder.ActorTxtUserNameVr.setText("" + user.getUserName());
        holder.ActorTxtAddressVr.setText("" + user.getCountryId() + "," + user.getCityId());
        if (user.getComments().size() == 0) {
                holder.ActorRate.setText("0");
        } else {
            holder.ActorRate.setText(""+user.getComments().get(position).getStarRate());
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
        this.ActorsVListFiltered = ModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ActorsVListFiltered.size();
    }

    public class UserViewHolderFilmVr extends RecyclerView.ViewHolder {
        TextView ActorTxtUserNameVr,ActorTxtAddressVr,ActorRate;
        public UserViewHolderFilmVr(@NonNull View itemView) {
            super(itemView);
            ActorTxtUserNameVr = itemView.findViewById(R.id.ActorTxtUserNameV);
            ActorTxtAddressVr = itemView.findViewById(R.id.ActorTXTAddressV);
            ActorRate = itemView.findViewById(R.id.ActorRateV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactVSelected(ActorsVListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface ActorAdaptorVListener {
        void onContactVSelected(UserModel userModelV);
    }
}
