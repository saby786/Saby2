package com.example.saby7.databasenew;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et, et1, et2;
    Button b, b1,b3,b4;
    RecyclerView rv;
    MyDatabase db;
    MyAdapter ma;
    int pos;
    int idpos;


    ArrayList<Emoplyee> al;
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View v = getLayoutInflater().inflate(R.layout.row, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Emoplyee emoplyee = al.get(position);
            holder.tv1.setText(emoplyee.getEname());
            holder.tv2.setText(emoplyee.getEsalary());
            holder.tv3.setText(emoplyee.getEdesignation());
        }

        @Override
        public int getItemCount() {
            return al.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1, tv2, tv3;

            public ViewHolder(View itemView) {
                super(itemView);
                tv1 = (TextView) itemView.findViewById(R.id.textview);
                tv2 = (TextView) itemView.findViewById(R.id.textview1);
                tv3 = (TextView) itemView.findViewById(R.id.textview2);
                LinearLayout linearLayout = (LinearLayout) itemView.findViewById(R.id.lv);
                       linearLayout.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               pos=getAdapterPosition();
                               pos++;
                               db = new MyDatabase(MainActivity.this);
                               db.open();
                               Cursor c = db.readdata();
                               if (c != null) {
                                   String aname = null;
                                   String mname = null;
                                   String dname = null;
                                   for(int i=1;i<=pos;i++)
                                   {
                                       c.moveToNext();
                                       idpos = c.getInt(0);
                                       aname = c.getString(1);
                                       mname = c.getString(2);
                                       dname = c.getString(3);
                                   }

                                   et.setText(aname);
                                   et1.setText(mname);
                                   et2.setText(dname);

                               }
                               db.close();
                           }

                       });
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText1);
        et1 = (EditText) findViewById(R.id.editText2);
        et2 = (EditText) findViewById(R.id.editText3);

        b = (Button) findViewById(R.id.button);
        b1 = (Button) findViewById(R.id.button1);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        rv = (RecyclerView) findViewById(R.id.rc);
        al = new ArrayList<Emoplyee>();


        ma = new MyAdapter();
        rv.setAdapter(ma);
        LinearLayoutManager lvm = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lvm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ename = et.getText().toString();
                String esalary = et1.getText().toString();
                String edesignation = et2.getText().toString();
                db = new MyDatabase(MainActivity.this);
                db.open();
                db.insertdata(ename, esalary, edesignation);
                db.close();
                et.setText("");
                et1.setText("");
                et2.setText("");
                db.close();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new MyDatabase(MainActivity.this);
                db.open();
                Cursor c = db.readdata();
                al.clear();
                if (c != null) {
                    while (c.moveToNext()) {
                        String aname = c.getString(1);
                        String mname = c.getString(2);
                        String dname1 = c.getString(3);
                        Emoplyee ee = new Emoplyee(aname, mname, dname1);
                        al.add(ee);

                    }
                }

                db.close();
                ma.notifyDataSetChanged();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new MyDatabase(MainActivity.this);
                db.open();
                db.deletedata(idpos);
                et.setText("");
                et1.setText("");
                et2.setText("");
                Cursor c = db.readdata();
                al.clear();
                if (c != null) {
                    while (c.moveToNext()) {
                        String aname = c.getString(1);
                        String mname = c.getString(2);
                        String dname1 = c.getString(3);
                        Emoplyee ee = new Emoplyee(aname, mname, dname1);
                        al.add(ee);

                    }
                }

                db.close();
                ma.notifyDataSetChanged();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actname = et.getText().toString();
                String mvname = et1.getText().toString();
                String dname = et2.getText().toString();

                db = new MyDatabase(MainActivity.this);
                db.open();
                db.updatedata(idpos,actname,mvname,dname);
                et.setText("");
                et1.setText("");
                et2.setText("");
                Cursor c = db.readdata();
                al.clear();
                if (c != null) {
                    while (c.moveToNext()) {
                        String aname = c.getString(1);
                        String mname = c.getString(2);
                        String dname1 = c.getString(3);
                        Log.d("B35","aname is "+aname);
                        Emoplyee ee = new Emoplyee(aname, mname, dname1);
                        al.add(ee);

                    }
                }

                db.close();
                ma.notifyDataSetChanged();
            }
        });
    }
}
