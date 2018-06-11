package edu.rshah12.grocerypositioningsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapActivity extends CheckBoxActivity {

    ScrollView sv = null;
    LinearLayout ll = null;
    SQLiteDatabase db;
    Bitmap map = null;
    Bitmap og;
    ImageView mapImage = null;
    Map coordMap = new HashMap<String, mapPoint>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapPoint BooksCards = new mapPoint(3920, 1088);
        coordMap.put("Books and Cards", BooksCards);
        mapPoint Condiments = new mapPoint(3600, 1088);
        coordMap.put("Condiments", Condiments);
        mapPoint International = new mapPoint(3268, 1088);
        coordMap.put("International", International);
        mapPoint Baking = new mapPoint(2952, 1088);
        coordMap.put("Baking Goods", Baking);
        mapPoint Beverages = new mapPoint(2640, 1088);
        coordMap.put("Juice and Water", Beverages);
        mapPoint TeaCoffee = new mapPoint(2320, 1088);
        coordMap.put("Tea and Coffee", TeaCoffee);
        mapPoint PersonalCare = new mapPoint(1996, 1088);
        coordMap.put("Personal Care", PersonalCare);
        mapPoint Snacks = new mapPoint(1688, 1088);
        coordMap.put("Snacks", Snacks);
        mapPoint Cleaning = new mapPoint(1356, 1088);
        coordMap.put("Cleaning Items", Cleaning);
        mapPoint Household = new mapPoint(1032, 1088);
        coordMap.put("Household Items", Household);
        mapPoint Produce = new mapPoint(5048, 652);
        coordMap.put("Produce", Produce);
        mapPoint Bakery = new mapPoint(4892, 1592);
        coordMap.put("Bakery", Bakery);
        mapPoint Deli = new mapPoint(5764, 1920);
        coordMap.put("Deli", Deli);
        mapPoint Dairy = new mapPoint(92, 1540);
        coordMap.put("Dairy", Dairy);
        mapPoint PackagedMeats = new mapPoint(2444, 180);
        coordMap.put("Packaged Meats", PackagedMeats);
        mapPoint Alcohol = new mapPoint(568, 3496);
        coordMap.put("Alcohol", Alcohol);
        mapPoint Frozen = new mapPoint(556, 1088);
        coordMap.put("Frozen Foods", Frozen);
        mapPoint Canned = new mapPoint(2952, 1088);
        coordMap.put("Canned Goods", Canned);
        mapPoint Breads = new mapPoint(4212, 1036);
        coordMap.put("Breads", Breads);
        mapPoint Flowers = new mapPoint(5592, 2652);
        coordMap.put("Flowers", Flowers);
        mapPoint Pizza = new mapPoint(4476, 2268);
        coordMap.put("Pizza", Pizza);

        mapImage = (ImageView) findViewById(R.id.imv);


        og = BitmapFactory.decodeResource(getResources(), R.drawable.floorplan);
        map = og.copy(Bitmap.Config.ARGB_8888, true);
        int test = map.getPixel(980, 272);
        int width = map.getWidth();
        int height = map.getHeight();
        Log.v("dimensions", "Width: " + Integer.toString(width) + " Height: " + Integer.toString(height));

        String [] catData = getIntent().getExtras().getStringArray("catData");

        for (int i = 0; i < catData.length; i++) {
            mapPoint temp = (mapPoint) coordMap.get(catData[i]);
            int x = temp.getX();
            int y =temp.getY();
            colorIn(x, y);
        }

        mapImage.setImageBitmap(map);

        String[] data = getIntent().getExtras().getStringArray("dbData");

        sv = (ScrollView) findViewById(R.id.sv);
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        for (int i = 0; i < data.length; i++) {
            CheckBox cb = new CheckBox(this);
            cb.setText(data[i]);
            ll.addView(cb);
        }


    }

    public void colorIn (int x, int y) {
        for (int i = x - 40; i < x + 40; i++) {
            for (int j = y - 40; j < y + 40; j++) {
                map.setPixel(i, j, Color.RED);
            }
        }
    }
}
