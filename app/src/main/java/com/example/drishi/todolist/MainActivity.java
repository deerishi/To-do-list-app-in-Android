package com.example.drishi.todolist;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import stanford.androidlib.SimpleActivity;

public class MainActivity extends SimpleActivity {
    private ArrayList<String> myList=new ArrayList<>();
    private ArrayAdapter<String> myAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTraceLifecycle(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void enterToList(View view) {
        EditText et=find(R.id.listItem);
        String item=et.getText().toString();
        if(item.length()>0){
            addToList(item);
        }
        et.setText("");
    }

    private void addToList(String item){
        Log.d("adding item"," item is : "+item);
        myList.add(item);
        updateListAdapter();
    }

    private void updateListAdapter(){
        if(myAdapter==null){
            myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
        }else{
            myAdapter.notifyDataSetChanged();
        }


        ListView lv=find(R.id.myList);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myList.remove(position);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });
        lv.setAdapter(myAdapter);
    }



    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("myList",myList);

    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if(bundle.containsKey("myList")){
            Log.d("list"," rotatted list");
            myList=bundle.getStringArrayList("myList");
            updateListAdapter();
        }
    }
}
