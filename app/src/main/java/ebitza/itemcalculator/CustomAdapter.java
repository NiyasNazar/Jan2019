package ebitza.itemcalculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ebitza.itemcalculator.Db_Helper.DatabaseHelper;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    DatabaseHelper controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Name = new ArrayList<String>();

    public CustomAdapter(Context  context,ArrayList<String> Id
    )
    {
        this.mContext = context;
        this.Id = Id;

    }
    @Override
    public int getCount() {
        return Name.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final    viewHolder holder;
        controldb =new DatabaseHelper(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_view_record, null);
            holder = new viewHolder();
         //   holder.id = (TextView) convertView.findViewById(R.id.title);
            holder.name = (TextView) convertView.findViewById(R.id.titles);
           /* holder.mail = (TextView) convertView.findViewById(R.id.tvmailid);
            holder.age = (TextView) convertView.findViewById(R.id.tvage);*/
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
       // holder.id.setText(Id.get(position));
        holder.name.setText(Name.get(position));
        Toast.makeText(mContext,"dbtoast"+Name.get(position),Toast.LENGTH_SHORT).show();
      //  holder.mail.setText(MailId.get(position));
       // holder.age.setText(Age.get(position));
        return convertView;
    }
    public class viewHolder {
        TextView id;
        TextView name;
        TextView mail;
        TextView age;
    }
}