package ebitza.itemcalculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ebitza.itemcalculator.Db_Helper.DBManager;
import ebitza.itemcalculator.Db_Helper.DatabaseHelper;

public class MyListFragment1 extends ListFragment {
    private DBManager dbManager;
    private SimpleCursorAdapter adapter;
    SQLiteDatabase db;
DatabaseHelper databaseHelper;
    final String[] from = new String[] {DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.DESC };
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Name = new ArrayList<String>();
    String[] options ={
            "ATO",
            "BETA",
            "DELT",
            "PHI DELT",
            "SAE",
            "SIG NU",
            "FIJI",
            "SIG CHI",
            "PHI PSI"
    };

    final int[] to = new int[] { R.id.title};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DBManager(getActivity());
        dbManager.open();
        databaseHelper=new DatabaseHelper(getActivity());
        db=databaseHelper.getReadableDatabase();
        Log.i("read","readingdbs");
        //ListView listView=(ListView)view.findViewById(R.id.mylist);

        List<String> labels = dbManager.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),R.layout.listview_item_category, labels);
        setListAdapter(dataAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listfragment1, container, false);

       /* Log.d("Reading: ", "Reading all contacts..");
        List<Category_model> contacts = dbManager.getAllCategory();
        for (Category_model cn : contacts) {
            String log = "Id: " + cn.get_id() + " ,Name: " + cn.get_subject() + " ,Phone: " +
                    cn.get_description();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }*/




        ImageView add_category_btn = (ImageView) view.findViewById(R.id.addcat);
        add_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is = new Intent(getActivity(), Addcategory.class);
                startActivity(is);
                Toast.makeText(getActivity(), "Toast", Toast.LENGTH_SHORT).show();
            }
        });


       /* Cursor cursor = db.rawQuery("SELECT * FROM  CATEGORIES", null);
        Id.clear();
        Name.clear();
        if (cursor.moveToFirst()) {
            do {
                Name.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SUBJECT)));
String a=cursor.getString(cursor.getColumnIndex(databaseHelper.SUBJECT));
                Log.i("read","readingdb");
                Log.i("read",a);

            } while (cursor.moveToNext());
            CustomAdapter ca = new CustomAdapter(getActivity(), Name);
         listView.setAdapter(ca);
            cursor.close();


        }*/
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
      //  TextView idTextView = (TextView) view.findViewById(R.id.id);
        TextView titleTextView = (TextView) view.findViewById(R.id.title);
       // TextView descTextView = (TextView) view.findViewById(R.id.desc);
        // TODO Auto-generated method stub
        getListView().setSelector(R.drawable.fragment_listselector);
        Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        String data=getListView().getItemAtPosition(position).toString();
        Bundle bundle=new Bundle();
        bundle.putString("message", data);
       Fragment2  myListFragment1=new Fragment2();
       myListFragment1.setArguments(bundle);
      getActivity().  getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,myListFragment1).commit();
    }
}
