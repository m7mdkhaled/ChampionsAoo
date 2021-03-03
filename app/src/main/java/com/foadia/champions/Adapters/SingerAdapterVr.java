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

public class SingerAdapterVr extends RecyclerView.Adapter<SingerAdapterVr.UserViewHolderSingVr> implements Filterable {
    private Context context;
    private List<UserModel> ModelListV;
    private List<UserModel> SingersVListFiltered;
    private SingerAdapterVr.SingerAdapterVListener listener;

    public SingerAdapterVr (Context context, List<UserModel> contactList, SingerAdapterVr.SingerAdapterVListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelListV = contactList;
        this.SingersVListFiltered = contactList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    SingersVListFiltered = ModelListV;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelListV) {

                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    SingersVListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = SingersVListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                SingersVListFiltered = (ArrayList<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public SingerAdapterVr.UserViewHolderSingVr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_singing_vr, parent, false);
        return new SingerAdapterVr.UserViewHolderSingVr(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SingerAdapterVr.UserViewHolderSingVr holder, int position) {
        final UserModel user = SingersVListFiltered.get(position);
        holder.SingerTxtUserNameV.setText(""+user.getUserName());
        holder.SingerTxtAddressV.setText(""+user.getCountryId()+","+user.getCityId());
        if (user.getComments().size() == 0 ) {
            holder.SingerRate.setText("0");
        } else {
            holder.SingerRate.setText(""+user.getComments().get(position).getStarRate());
        }
    }
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return SingersVListFiltered.size();
    }
    public void setList(ArrayList<UserModel> userList){
        this.ModelListV = userList;
        notifyDataSetChanged();
    }

    public void setListAll(ArrayList<UserModel> ModelList) {
        this.SingersVListFiltered = ModelList;
        notifyDataSetChanged();
    }

    public interface SingerAdapterVListener {
        void onContactVSelected(UserModel userModel);
    }

    public class UserViewHolderSingVr extends RecyclerView.ViewHolder {
        public TextView SingerTxtUserNameV, SingerTxtAddressV, SingerRate;

        public UserViewHolderSingVr(@NonNull View itemView) {
            super(itemView);
            SingerTxtUserNameV = itemView.findViewById(R.id.SingerTxtUserNameV);
            SingerTxtAddressV = itemView.findViewById(R.id.SingerTXTAddressV);
            SingerRate = itemView.findViewById(R.id.SingerRateV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onContactVSelected(SingersVListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}
