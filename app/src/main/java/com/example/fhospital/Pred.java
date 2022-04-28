package com.example.fhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Pred extends AppCompatActivity {

    int anspos=0;

    LottieAnimationView lottiAnimationView;
    SearchView mySearchView;
    ListView myList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    ArrayList<String> classes;

    ArrayList<ArrayList<Double>>coef;
    ArrayList<Double>intercept;
    int [] Xdata;

    Button predict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pred);

        mySearchView = findViewById(R.id.searchView);
        myList = findViewById(R.id.myList);
        predict = findViewById(R.id.predict);
        lottiAnimationView = findViewById(R.id.animation1);

        list = new ArrayList<String>();
        classes = new ArrayList<String>();

        coef = new ArrayList<ArrayList<Double>>();
        intercept = new ArrayList<Double>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("symptoms");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    String sym = postSnapshot.getValue(String.class);
                    list.add(sym);
                }
                adapter.notifyDataSetChanged();
                Xdata = new int[list.size()];
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Failed to read", databaseError.toException());
            }
        });


        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,list);
        myList.setAdapter(adapter);

        DatabaseReference classref = database.getReference("diseases");
        classref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    String sym = postSnapshot.getValue(String.class);
                    classes.add(sym);
                }
                Collections.sort(classes);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Failed to read", databaseError.toException());
            }
        });


        DatabaseReference coef_fire = database.getReference("weights").child("Coef");
        coef_fire.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0,j=0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    j=0;
                    coef.add(new ArrayList<Double>());
                    for (DataSnapshot val : postSnapshot.getChildren()) {
                        coef.get(i).add(j,val.getValue(Double.class));
                        j++;
                    }
                    i++;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference intercept_fire = database.getReference("weights").child("Intercept");
        intercept_fire.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    intercept.add(postSnapshot.getValue(Double.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(MainActivity.this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
                String str = adapter.getItem(position);
                if(myList.isItemChecked(position)) {
                    Xdata[list.indexOf(str)] = 1;
                }
                else {
                    Xdata[list.indexOf(str)] = 0;
                }
            }
        });

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double temp=0,max=-99999999;
                int anspos=0;
                for(int i=0;i<intercept.size();i++)
                {
                    temp = intercept.get(i);
                    for(int j=0;j<list.size();j++)
                    {
                        temp += Xdata[j] * coef.get(i).get(j);
                    }
                    //System.out.println(temp);
                    if(temp>max)
                    {
                        max = temp;
                        anspos = i;
                    }
                }

                lottiAnimationView.setVisibility(View.VISIBLE);
                mySearchView.setVisibility(View.GONE);
                myList.setVisibility(View.GONE);
                lottiAnimationView.playAnimation();

                final Intent intent = new Intent(Pred.this, Pred1.class);
                intent.putExtra("EXTRA_MESSAGE", classes.get(anspos));
                Intent intent1 = new Intent(Pred.this, Pred1.class);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(intent);
                    }
                }, 4000);




                if(!lottiAnimationView.isAnimating()) {
                    lottiAnimationView.cancelAnimation();
                }





                //Toast.makeText(Pred.this,classes.get(anspos),Toast.LENGTH_LONG).show();
                //Toast toast = new Toast(getApplicationContext());
                //View v = LayoutInflater.from(Pred.this).inflate(R.layout.toast_layout, null);
                //TextView toastview = v.findViewById(R.id.predtoast);
                //toast.setView(v);
                ////toastview.setText(classes.get(anspos));
                //toast.setDuration(Toast.LENGTH_SHORT);
                //startActivity(new Intent(getApplicationContext(),Pred1.class));
                //toast.show();
                //Xdata = new int[list.size()];
            }
        });
    }


}

