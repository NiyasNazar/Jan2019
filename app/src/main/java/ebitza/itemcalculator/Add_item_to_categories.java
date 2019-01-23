package ebitza.itemcalculator;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import ebitza.itemcalculator.Db_Helper.DBManager;
import ebitza.itemcalculator.Db_Helper.DatabaseHelper;

public class Add_item_to_categories extends AppCompatActivity {
    private DBManager dbManager;
    private SimpleCursorAdapter adapter;
    SQLiteDatabase db;
    DatabaseHelper databaseHelper;
    Button additem;
    EditText item_name,item_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_itm_to_category);
        additem=(Button)findViewById(R.id.add_item);
        item_name=(EditText)findViewById(R.id.edittext_item_name);
        item_price=(EditText)findViewById(R.id.edittext_item_price);
        final String tablename=getIntent().getStringExtra("strtext");
        dbManager = new DBManager(this);
        dbManager.open();
        databaseHelper=new DatabaseHelper(this);
        db=databaseHelper.getReadableDatabase();

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Itemsname=item_name.getText().toString();
                String Itemprice=item_price.getText().toString();
                dbManager.additemstocategory(tablename,Itemsname,Itemprice);
            }
        });

    }
}
