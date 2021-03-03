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

public class PainterAdapterVr extends RecyclerView.Adapter<PainterAdapterVr.UserViewHolderDrawVr> implements Filterable {

    private Context context;
    private List<UserModel> ModelList;
    private List<UserModel> PaintersVListFiltered;
    private PainterAdapterVr.PainterAdapterVListener listener;

    public PainterAdapterVr(Context context, List<UserModel> contactList, PainterAdapterVr.PainterAdapterVListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.PaintersVListFiltered = contactList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    PaintersVListFiltered = ModelList;
                } else {
                    List<UserModel> filteredList = new ArrayList<>();
                    for (UserModel row : ModelList) {

                        if (row.getUserName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    PaintersVListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = PaintersVListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                PaintersVListFiltered = (ArrayList<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public PainterAdapterVr.UserViewHolderDrawVr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drawing_vr, parent, false);
        return new PainterAdapterVr.UserViewHolderDrawVr(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PainterAdapterVr.UserViewHolderDrawVr holder, int position) {
        final UserModel user = PaintersVListFiltered.get(position);
        holder.PainterTxtUserNameVr.setText("" + user.getUserName());
        holder.PainterTxtAddressVr.setText("" + user.getCountryId() + "," + user.getCityId());
        if (user.getComments().size() == 0) {
            holder.PainterRateV.setText("0");
        }else {
            holder.PainterRateV.setText(""+user.getComments().get(position).getStarRate());
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
        this.PaintersVListFiltered = ModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return PaintersVListFiltered.size();
    }

    public interface PainterAdapterVListener {
        void onContactVSelected(UserModel userModelV);
    }

    public class UserViewHolderDrawVr extends RecyclerView.ViewHolder {
        TextView PainterTxtUserNameVr, PainterTxtAddressVr,PainterRateV;

        public UserViewHolderDrawVr(@NonNull View itemView) {
            super(itemView);
            PainterTxtUserNameVr = itemView.findViewById(R.id.PainterTxtUserNameV);
            PainterTxtAddressVr = itemView.findViewById(R.id.PainterTXTAddressV);
            PainterRateV = itemView.findViewById(R.id.PainterRateV);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onContactVSelected(PaintersVListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}
