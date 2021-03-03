package com.foadia.champions.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.foadia.champions.Models.CountryModel;
import com.foadia.champions.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> implements Filterable {
    private Context context;
    private List<CountryModel> ModelList;
    private List<CountryModel> ListFiltered;
    private CountryAdapterListener listener;

    public CountryAdapter(Context context, List<CountryModel> contactList, CountryAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.ModelList = contactList;
        this.ListFiltered = contactList;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new CountryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountryHolder holder,final int position) {
        final CountryModel model = ListFiltered.get(position);
        holder.TxtCountries.setText(""+ model.getCountryName());

    }

    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return ListFiltered.size();
    }

    public void setList(ArrayList<CountryModel> countryList){
        this.ModelList = countryList;
        notifyDataSetChanged();
    }

    public void setListAll(ArrayList<CountryModel> ModelList) {
        this.ListFiltered = ModelList;
        notifyDataSetChanged();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {
        public TextView TxtCountries;

        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            TxtCountries = itemView.findViewById(R.id.TxtCountries);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    // send selected contact in callback
                  listener.onContactSelected(ListFiltered.get(getAdapterPosition()));
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
                    ListFiltered = ModelList;
                } else {
                    List<CountryModel> filteredList = new ArrayList<>();
                    for (CountryModel row : ModelList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getCountryName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    ListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ListFiltered = (ArrayList<CountryModel>) filterResults.values;
                notifyDataSetChanged();
            }

        };
    }

    public interface CountryAdapterListener {
        void onContactSelected(CountryModel countryModel);
    }

}
