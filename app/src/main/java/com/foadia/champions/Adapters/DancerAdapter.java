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
import com.foadia.champions.Ui.ViewModel.UsersViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DancerAdapter extends RecyclerView.Adapter<DancerAdapter.UserViewHolder> implements Filterable {
    private Context context;
    private List<UserModel> ModelList;
    private List<UserModel> UsersListFiltered;
    private UserAdapterListener listener;

    public DancerAdapter(Context context, List<UserModel> contactList, UserAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.UsersListFiltered = contactList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_popular_dancing1, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder,final int position) {
        final UserModel user = UsersListFiltered.get(position);
        holder.TxtUserName.setText(""+user.getUserName());
        holder.TxtAddress.setText(""+user.getCountryId());
        holder.TxtAge.setText("Year "+user.getAge().toString());

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

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView TxtUserName,TxtAddress,TxtAge,TxtRate;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            TxtUserName = itemView.findViewById(R.id.TXTUserName);
            TxtAddress = itemView.findViewById(R.id.TXTAddress);
            TxtAge = itemView.findViewById(R.id.TXTOld);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {

                    // send selected contact in callback
                  listener.onContactSelected(UsersListFiltered.get(getAdapterPosition()));
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
                    UsersListFiltered = ModelList;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
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
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                UsersListFiltered = (ArrayList<UserModel>) filterResults.values;
                notifyDataSetChanged();
            }

        };
    }

    public interface UserAdapterListener {
        void onContactSelected(UserModel userModel);
    }

}
