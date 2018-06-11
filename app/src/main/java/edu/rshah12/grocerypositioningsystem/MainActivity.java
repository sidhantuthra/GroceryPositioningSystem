package edu.rshah12.grocerypositioningsystem;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CheckBoxActivity {

    SQLiteDatabase db = null;
    EditText item;
    Spinner category;
    CheckBox cb;
    LinearLayout ll;
    ScrollView sv;

    //test
    // test again

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = (EditText) findViewById(R.id.item);
        category = (Spinner) findViewById(R.id.spinner);
        sv = (ScrollView) findViewById(R.id.sv);
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        ll.setId(00);

        db = openOrCreateDatabase("MyDatabase", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS List");
        db.execSQL("CREATE TABLE List (Item TEXT, Category TEXT)");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


    }

    public void makeGroceryList (View v) {
        String groceryItem = item.getText().toString();
        String categoryItem = category.getSelectedItem().toString();
        db.execSQL("INSERT INTO List VALUES ('" + groceryItem + "', '" + categoryItem + "')");
        cb = new CheckBox(this);
        cb.setText(groceryItem);
        ll.addView(cb);
        item.setText("");
        sv.invalidate();
    }

    public String[] getData(String query) throws SQLException {

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String[] data = new String[c.getCount()];
        int i = 0;

        c.moveToFirst();
        while (!c.isAfterLast()) {
            data[i] = c.getString(0);
            i++;
            c.moveToNext();
        }

        return data;
    }

    public void mapIt (View v) {
        String[] dbData = getData("SELECT Item FROM List");
        String [] catData = getData("SELECT Category FROM List");
        Intent map = new Intent(this, MapActivity.class);
        map.putExtra("dbData", dbData);
        map.putExtra("catData", catData);
        startActivity(map);
    }

   }
