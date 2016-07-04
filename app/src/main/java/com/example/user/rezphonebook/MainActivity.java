package com.example.user.rezphonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<ContactFolder> contactFolderArrayList ;
    dbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lv = (ListView)findViewById(R.id.listView);
        dbhelper = new dbHelper(getApplicationContext());
       populateContactList();





    }

    public void populateContactList(){
        contactFolderArrayList =dbhelper.getContactList();
        lv.setAdapter(new ContactAdapter());
    }

    //extended base adepter  for contactAdapter
    class ContactAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return contactFolderArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup) {
            View layout = convertview;
                if(layout == null){
                    getLayoutInflater().inflate(R.layout.custom_layout_list,viewGroup,false);
                }
            TextView textName = (TextView) layout.findViewById(R.id.textView);
            TextView textPhone = (TextView) layout.findViewById(R.id.textView2);


            ContactFolder contactFolder = contactFolderArrayList.get(position);
            textName.setText(contactFolder.name);
            textPhone.setText(contactFolder.phone);


            return layout;
        }
    }



   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            Toast.makeText(getApplicationContext(),"DATA SUCSESSFULLY INSERTED",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            populateContactList();
            startActivityForResult(new Intent(MainActivity.this,addPhoneNum.class),100);
        }

        return super.onOptionsItemSelected(item);
    }


}
