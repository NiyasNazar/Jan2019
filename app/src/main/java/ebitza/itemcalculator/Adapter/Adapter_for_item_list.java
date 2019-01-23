package ebitza.itemcalculator.Adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ebitza.itemcalculator.Models.Model_category_item;
import ebitza.itemcalculator.R;

public class Adapter_for_item_list extends RecyclerView.Adapter<Adapter_for_item_list.MyViewHolder> {
    private List<Model_category_item> itemList;
    Context mcontext;
    public Adapter_for_item_list(List<Model_category_item> itemList, Context mcontext)
    {
        this.itemList = itemList;
        this.mcontext=mcontext;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        LinearLayout ll;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.row_item_name);
            genre = (TextView) view.findViewById(R.id.row_item_price);
            ll=(LinearLayout)view.findViewById(R.id.lllayouts);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
                    AlertDialog.Builder alert = new AlertDialog.Builder(mcontext);
                    alert.setTitle("");
                    alert.setView(alertLayout);
                    alert.setCancelable(true);
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });

        }
    }

    private void Load() {

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Model_category_item movie = itemList.get(position);
        holder.title.setText(movie.getItemname());
        holder.genre.setText(movie.getItemprice());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}