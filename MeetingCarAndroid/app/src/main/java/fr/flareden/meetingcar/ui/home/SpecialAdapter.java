package fr.flareden.meetingcar.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.flareden.meetingcar.R;
import fr.flareden.meetingcar.ui.mail.MailViewModel;
import fr.flareden.meetingcar.ui.mail.MessageViewModel;

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.ViewHolder> implements Filterable {

    public enum Type{
        Advert,
        Discussion,
        Message
    }
    private ArrayList<IViewModel> data;
    private ArrayList<IViewModel> fullData;
    private Filter filter;
    private Type type;

    public SpecialAdapter(ArrayList<IViewModel> d, Type type) {
        this.type = type;
        this.data = d;
        this.fullData = new ArrayList<>(d);
        this.filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                ArrayList<IViewModel> filteredList = new ArrayList<>();
                if (charSequence == null || charSequence.length() == 0) {
                    filteredList.addAll(fullData);
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (IViewModel item : fullData) {
                        if (item.getSearchString().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data.clear();
                data.addAll((ArrayList) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Advert & Discussion
        TextView tv_title;
        //Advert
        TextView tv_desc, tv_loc, tv_price, tv_type;
        //Discussion
        TextView tv_name_contact;
        //Message
        TextView tv_message;


        public ViewHolder(View itemView) {
            super(itemView);
            if(type == Type.Advert){
                tv_title = itemView.findViewById(R.id.rv_tv_title);
                tv_desc = itemView.findViewById(R.id.rv_tv_desc);
                tv_loc = itemView.findViewById(R.id.rv_tv_loc);
                tv_price = itemView.findViewById(R.id.rv_tv_price);
                tv_type = itemView.findViewById(R.id.rv_tv_type);
            } else if (type == Type.Discussion){
                tv_title = itemView.findViewById(R.id.rv_tv_title);
                //tv_name_contact = itemView.findViewById(R.id.rv_tv_name_contact);
            }else if (type == Type.Discussion){
                //tv_message = itemView.findViewById(R.id.rv_tv_message);
            }

        }
    }

    @Override
    public SpecialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(SpecialAdapter.ViewHolder holder, int position) {
        if(type == Type.Advert){
            AdvertViewModel avm =(AdvertViewModel) data.get(position);
            holder.tv_title.setText(avm.getTitle());
            holder.tv_desc.setText(avm.getDesc());
            holder.tv_loc.setText(avm.getLoc());
            holder.tv_price.setText(avm.getPrice());
            holder.tv_type.setText((avm.getType() == AdvertViewModel.TYPE.RENT ? "RENT" : "SELL"));
        } else if (type == Type.Discussion){
            MailViewModel mvm = (MailViewModel) data.get(position);
            holder.tv_title.setText(mvm.getTitle());
            holder.tv_name_contact.setText(mvm.getContactName());
            //TODO
        } else if (type == Type.Message){
            MessageViewModel mvm = (MessageViewModel) data.get(position);
            holder.tv_message.setText(mvm.getContent());
            //TODO
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public Filter getFilter() {
        return this.filter;
    }
}