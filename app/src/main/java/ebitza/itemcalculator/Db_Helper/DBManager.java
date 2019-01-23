package ebitza.itemcalculator.Db_Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ebitza.itemcalculator.Category_model;
import ebitza.itemcalculator.Models.Model_category_item;

public class DBManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;
    public DBManager(Context c) {
        context = c;
    }
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        database=dbHelper.getReadableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public void addcategory(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SUBJECT, name);
        contentValue.put(DatabaseHelper.DESC, desc);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        CreateDynamicTables(name);
    }


    public List<Category_model> getAllCategory() {
        List<Category_model> contactList = new ArrayList<Category_model>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category_model category_model = new Category_model();
                category_model.set_id(Integer.parseInt(cursor.getString(0)));
                category_model.set_description(cursor.getString(1));
                category_model.set_subject(cursor.getString(2));
                // Adding contact to list
                contactList.add(category_model);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
    public void CreateDynamicTables(String Table_Name)
    {
        String CID="itemid";
        String DName="item_name";
        String PName="item_price";
        String Item_Q="item_quantity";

        database = dbHelper.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        String query = "CREATE TABLE " + Table_Name + "(" + CID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DName + " TEXT, " + PName + " TEXT, " + Item_Q + " TEXT);";
        database.execSQL(query);
       /* database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CID, Contact_ID);
        cv.put(DName, Display_Name);
        database.insert(Table_Name, null, cv);*/
        database.close();
    }
    public void additemstocategory(String tablename,String itemname,String item_price)
    {
        database = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("item_name", itemname);
        cv.put("item_price", item_price);
        database.insert(tablename, null, cv);
        database.close();


    }
    public List<Model_category_item> getAllitems(String tablename) {
        List<Model_category_item> contactList = new ArrayList<Model_category_item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tablename;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model_category_item category_model = new Model_category_item();
               // category_model.set_id(Integer.parseInt(cursor.getString(0)));
                category_model.setItemname(cursor.getString(1));
                category_model.setItemprice(cursor.getString(2));
                // Adding contact to list
                contactList.add(category_model);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

}
