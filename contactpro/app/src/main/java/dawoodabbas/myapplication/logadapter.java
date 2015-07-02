package dawoodabbas.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Dawood Abbas on 7/1/2015.
 */
public class logadapter extends ArrayAdapter<String> {
    logdb de;
    public String[] f, g, h,i,j;
    Context context;

    public logadapter(Context context, String[] objects1, String[] img1,String[] call1,String[] time,String[]type) {
        super(context, R.layout.calllog,objects1);
        f = objects1;
        g = img1;
        h =call1;
        i=time;
        j=type;

        this.context = context;
    }



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        LayoutInflater Inflater = LayoutInflater.from(getContext());
        View CallLog = Inflater.inflate(R.layout.calllog, parent, false);
        if (convertView == null)
        {
            convertView = (LinearLayout) Inflater.inflate(R.layout.calllog, null);
        }


        final String images;
        images = g[position];
        final String singlename = f[position];
        final String numb=h[position];
        final String te=i[position];
        final String ty=j[position];

        TextView abbasText1 = (TextView) CallLog.findViewById(R.id.abbasText1);
        ImageView abbasImage1 = (ImageView) CallLog.findViewById((R.id.abbasImage1));
        TextView abbasText2=(TextView)CallLog.findViewById(R.id.abbasText2);
        TextView time=(TextView)CallLog.findViewById(R.id.time);
        ImageView type=(ImageView)CallLog.findViewById(R.id.type);

        int h;
        int m;
        int s;
        int input= Integer.parseInt(te);

        h = (input% 86400 ) / 3600 ;
       m = ((input % 86400 ) % 3600 ) / 60;
      s = ((input % 86400 ) % 3600 ) % 60  ;

        if(h==1)
        {
            time.setText(h+"hour "+m+"mins "+s+ "secs");

        }
       else if(h>1)
        {
            time.setText(h+"hours "+m+"mins "+s+ "secs");
        }

       else if (h<1&&m>=1)
        {
            time.setText(m+"mins "+s+ "secs");
        }
        else if(h<1&&m<1)
        time.setText(te+ "sec");

        switch (ty)
        {
            case "MISSED":
                type.setImageResource(R.drawable.missed1);
                break;
            case "INCOMING":
                type.setImageResource(R.drawable.incoming);
                break;
            case "OUTGOING":
                type.setImageResource(R.drawable.outgoing1);
                break;
        }

        abbasText1.setText(singlename);
        abbasText2.setText(numb);

        if(images.equals("blank")) {
            abbasImage1.setImageResource(R.drawable.back);
        } else {
            Log.i("zen","int");
            abbasImage1.setImageBitmap(BitmapFactory.decodeFile(images));
        }

        return CallLog;
    }
}


