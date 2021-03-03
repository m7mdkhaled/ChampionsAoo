package com.foadia.champions.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.foadia.champions.Models.UserModel;
import com.foadia.champions.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DancerAdapterV extends RecyclerView.Adapter<DancerAdapterV.UserVHolder> implements Filterable {
    private Context context;
    private List<UserModel> ModelListV;
    private List<UserModel> UsersVListFiltered;
    private UserAdapterVListener listener;

    public DancerAdapterV(Context context, List<UserModel> contactList, UserAdapterVListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelListV = contactList;
        this.UsersVListFiltered = contactList;
    }

    @NonNull
    @Override
    public UserVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_popular_dancingv, parent, false);
        return new UserVHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserVHolder holder,final int position) {
        final UserModel user = UsersVListFiltered.get(position);
        holder.TxtUserNameV.setText(""+user.getUserName());
        holder.TxtAddressV.setText(""+user.getCountryId());
        holder.TAgeV.setText("Year "+ user.getAge().toString());
        if (user.getComments().size() == 0) {
            holder.DancerRateV.setText("0");
        }else {
            holder.DancerRateV.setText(""+user.getComments().get(position).getStarRate());
        }

    }

    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return UsersVListFiltered.size();
    }

    public void setList(ArrayList<UserModel> userList){
        this.ModelListV = userList;
        notifyDataSetChanged();
    }

    public void setListAll(ArrayList<UserModel> ModelList) {
        this.UsersVListFiltered = ModelList;
        notifyDataSetChanged();
    }

    public class UserVHolder extends RecyclerView.ViewHolder {
        public TextView TxtUserNameV,TxtAddressV,DancerRateV,TAgeV;

        public UserVHolder(@NonNull View view) {
            super(view);
            TxtUserNameV = view.findViewById(R.id.TxtUserNameV);
            TxtAddressV =view.findViewById(R.id.TXTAddressV);
            TAgeV = view.findViewById(R.id.TXTOldV);
            DancerRateV = view.findViewById(R.id.DancerRateV);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // send selected contact in callback
                    listener.onContactVSelected(UsersVListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    UsersVListFiltered = ModelListV;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelListV) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    UsersVListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = UsersVListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                UsersVListFiltered = (ArrayList<UserModel>) filterResults.values;
                notifyDataSetChanged();
            }

        };
    }

    public interface UserAdapterVListener {
        void onContactVSelected(UserModel userModelV);
    }

}
