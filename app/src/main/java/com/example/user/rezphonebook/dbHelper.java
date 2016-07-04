package com.example.user.rezphonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 6/28/2016.
 */
public class dbHelper extends SQLiteOpenHelper {


    final static String DATABASE_NAME = "phoneBook";
    final static int DATABASE_VERSION = 1;
    final static String TABLE_NAME = "ContactTable";
    final static String CONTACT_ID ="id";
    final static String CONTACT_NAME = "name";
    final static String CONTACT_PHONE = "phone";
    final static String QUERY_CREATE_CONTACT = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CONTACT_NAME+" TEXT, " +
            CONTACT_PHONE+" TEXT )";
    final static String QUERY_DROP_CONTACT = " DROP TABLE IF EXISTS "+ TABLE_NAME;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(QUERY_CREATE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(QUERY_DROP_CONTACT);
    }

    public long insertContact(ContactFolder values){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues val=new ContentValues();
        val.put(CONTACT_NAME,values.name);
        val.put(CONTACT_PHONE,values.phone);

       long ret = db.insert(TABLE_NAME,null,val);
        return ret;
    }

    public ArrayList<ContactFolder> getContactList(){
        ArrayList<ContactFolder> contactFolderArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

       Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);

        cursor.moveToFirst();

        for(int i=0;i<cursor.getCount();i++){

            int id = cursor.getInt(cursor.getColumnIndex(CONTACT_ID));
            String name =cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(CONTACT_PHONE));

            ContactFolder contactFolder =new ContactFolder(id,name,phone);
            contactFolderArrayList.add(contactFolder);
            cursor.moveToNext();

        }

        return contactFolderArrayList;
    }
}
