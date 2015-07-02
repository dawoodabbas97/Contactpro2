package dawoodabbas.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class calllog extends Activity {
    private String callType;
    private String phoneNumber;
    private String callDate;
    private String callDuration;
    private Date callDateTime;


    List<String> lname1,lpid1,lpno1,ltime,ltype;

    String[] r1,pic1,time,type;
    String[] con1,con,pic,nam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calllog);
        Intent intent=getIntent();
        con=intent.getStringArrayExtra("num");
        pic=intent.getStringArrayExtra("images");
        nam=intent.getStringArrayExtra("names");


        lpno1=new ArrayList<String>();
        lname1 = new ArrayList<String>();
        lpid1 =new ArrayList<String>();
        ltime=new ArrayList<String>();
        ltype=new ArrayList<String>();
        con1=new String[lpno1.size()];
        con1=lpno1.toArray(con1);
        pic1 = new String[lpid1.size()];
        pic1 = lpid1.toArray(pic1);
       ltype= new ArrayList<>();
       getCallDetails();

        r1 = new String[lname1.size()];
        r1= lname1.toArray(r1);
        con1=new String[lpno1.size()];
        con1=lpno1.toArray(con1);
        pic1 = new String[lpid1.size()];
        pic1 = lpid1.toArray(pic1);
        time=new String[ltime.size()];
        time=ltime.toArray(time);
        type=new String[ltype.size()];
        type=ltype.toArray(type);



           ListAdapter calladapter=new logadapter(this,r1,pic1,con1,time,type);
        final ListView abbasListview2=(ListView)findViewById(R.id.abbasListview2);
        abbasListview2.setAdapter(calladapter);
           Log.i("zen", "bored");



    }

    public void getCallDetails()
    {
        String sequenceCaptureTime = "";
        BigDecimal roundThreeCalc = new BigDecimal("0");
        BigDecimal hours = new BigDecimal("0");
        BigDecimal myremainder = new BigDecimal("0");
        BigDecimal minutes = new BigDecimal("0");
        BigDecimal seconds = new BigDecimal("0");
        BigDecimal var3600 = new BigDecimal("3600");
        BigDecimal var60 = new BigDecimal("60");
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = managedQuery( CallLog.Calls.CONTENT_URI,null, null,null, null);

        int number = managedCursor.getColumnIndex( CallLog.Calls.NUMBER );
        int type = managedCursor.getColumnIndex( CallLog.Calls.TYPE );
        int date = managedCursor.getColumnIndex( CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex( CallLog.Calls.DURATION);


        while (managedCursor.moveToNext())
        {
              String ne=null,im=null;
            int f=0;
            phoneNumber = managedCursor.getString(number);
            callType = managedCursor.getString(type);
            callDate = managedCursor.getString(date);

            callDateTime = new Date(Long.valueOf(callDate));

            callDuration = managedCursor.getString(duration);

            String cType = null;

            int cTypeCode = Integer.parseInt(callType);

            switch(cTypeCode)
            {
                case CallLog.Calls.OUTGOING_TYPE:
                    cType = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    cType= "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    cType = "MISSED";
                    break;
            }

            for(int i=0;i<con.length;i++)
            {
                if(con[i].equals(phoneNumber))
                {
                    ne=nam[i];
                    im=pic[i];
                    f++;
                }

            }

            if(f==0)
            {
                ne="Unknown";
                im="blank";
            }




            lpno1.add(phoneNumber);
            ltype.add(cType);
            ltime.add(callDuration);
            lname1.add(ne);
            lpid1.add(im);


        }

        managedCursor.close();
    }

}
