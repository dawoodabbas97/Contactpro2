package dawoodabbas.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class  MainActivity extends Activity {
    private TextView tv;
    private String callType;
    private String phoneNumber;
    private String callDate;
    private String callDuration;
    private Date callDateTime;

    mydbhandler handle;
    Button add, del, log;
    List<String> lname, lpid, lpno;
    String[] r, names, pic;
    String temp;
    int temp1;
    String[] con;
    logdb handle2;
    String[] num = pic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handle = new mydbhandler(this, null, null, 1);

        lpno = new ArrayList<String>();
        lname = new ArrayList<String>();
        lpid = new ArrayList<String>();
        lname = handle.getnames();
        lpid = handle.getpic();
        lpno = handle.getpno();
        r = new String[lname.size()];
        r = lname.toArray(r);
        con = new String[lpno.size()];
        con = lpno.toArray(con);
        pic = new String[lpid.size()];
        pic = lpid.toArray(pic);

        ListAdapter abbasadapter = new customadapter(this, r, pic, con);
        final ListView abbasListView = (ListView) findViewById(R.id.abbasListView);
        abbasListView.setAdapter(abbasadapter);
        abbasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, Viewcontact.class);
                String re = String.valueOf(parent.getItemAtPosition(position));
                i.putExtra("name", re);
                i.putExtra("num", con[position]);
                i.putExtra("image", pic[position]);
                Log.i("void", "list");
                startActivity(i);
            }
        });
        add = (Button) findViewById(R.id.add);
        log = (Button) findViewById(R.id.log);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddContact.class);
                startActivity(i);
            }
        });


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, calllog.class);
                intent.putExtra("names",r);
                intent.putExtra("images",pic);
                intent.putExtra("num",con);
                startActivity(intent);
            }
        });




       /* Button delete=(Button)findViewById(R.id.dele);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position=abbasListView.getPositionForView(v);
                String h=r[position];
                handle.delcont(h);
            }
        });*/


    }

    public void main()

    {
        String[] Array = r, num = pic, b = con;

        String temp;

        for (int j = 0; j < Array.length; j++) {
            for (int i = j + 1; i < Array.length; i++) {
                if (Array[i].compareTo(Array[j]) < 0) {
                    temp = Array[j];
                    Array[j] = Array[i];
                    Array[i] = temp;
                    temp = num[j];
                    num[j] = num[i];
                    num[i] = temp;
                    temp = b[j];
                    b[j] = b[i];
                    b[i] = temp;


                }
            }

            r = Array;
            pic = num;

        }
    }

    public void main1()

    {


        String[] Array = r, num = pic, b = con;
        String temp;

        for (int j = 0; j < Array.length; j++) {
            for (int i = j + 1; i < Array.length; i++) {
                if (Array[i].compareTo(Array[j]) > 0) {
                    temp = Array[j];
                    Array[j] = Array[i];
                    Array[i] = temp;
                    temp = num[j];
                    num[j] = num[i];
                    num[i] = temp;

                    temp = b[j];
                    b[j] = b[i];
                    b[i] = temp;

                }
            }

            r = Array;
            pic = num;


        }
    }


    public void OnToggleClicked(View view1) {
        boolean on = ((ToggleButton) view1).isChecked();

        if (on) {
            main();

            ListAdapter abbasadapter = new dawoodabbas.myapplication.customadapter(this, r, pic, con);
            final ListView abbasListView = (ListView) findViewById(R.id.abbasListView);
            abbasListView.setAdapter(abbasadapter);
        } else {
            main1();
            ListAdapter abbasadapter = new dawoodabbas.myapplication.customadapter(this, r, pic, con);
            final ListView abbasListView = (ListView) findViewById(R.id.abbasListView);
            abbasListView.setAdapter(abbasadapter);
        }
    }


    public void search(View v) {
        int flag = 0;
        EditText name = (EditText) findViewById(R.id.search);
        String see = name.getText().toString();

        for (String i : r) {
            if (i.compareToIgnoreCase(see) == 0) {
                Toast.makeText(getApplicationContext(), "Found: " + i, Toast.LENGTH_SHORT).show();
                flag = 1;

            }

        }
        if (flag == 0)
            Toast.makeText(getApplicationContext(), "NotFound", Toast.LENGTH_SHORT).show();


    }


}