
package dawoodabbas.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class customadapter extends ArrayAdapter<String> {
    public String[] a, pname;
    public String[] e;
    mydbhandler han;
    logdb se;
    Button b;

    Context context;


    public customadapter(Context context, String[] objects, String[] img,String[] call) {
        super(context, R.layout.customview, objects);
        a = img;
        e=call;
        pname = objects;
        this.context=context;
        han = new mydbhandler(context, null, null, 1);
        se=new logdb(context,null,null,1);

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        LayoutInflater abbasInflater = LayoutInflater.from(getContext());
        View CustomView = abbasInflater.inflate(R.layout.customview, parent, false);
        if (convertView == null) {
            convertView = (LinearLayout) abbasInflater.inflate(R.layout.customview, null);
        }

        final String images;
        images = a[position];
        final String singlename = pname[position];
        ImageButton del = (ImageButton) CustomView.findViewById(R.id.dele);
        ImageButton msg=(ImageButton)CustomView.findViewById(R.id.msg);
        ImageButton call=(ImageButton)CustomView.findViewById(R.id.call);
        TextView abbasText = (TextView) CustomView.findViewById(R.id.abbasText);
        ImageView abbasImage = (ImageView) CustomView.findViewById((R.id.abbasImage));
        abbasText.setText(singlename);

        if(images.equals("blank"))
        {
            abbasImage.setImageResource(R.drawable.back);
        }

        else {
            abbasImage.setImageBitmap(BitmapFactory.decodeFile(images));
        }
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h=pname[position];
                han.delcont(h);
                Intent i=new Intent(context,MainActivity.class);
                context.startActivity(i);


                            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("abas", "call");

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+e[position]));
                context.startActivity(intent);
            }
        });
msg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.i("abas", "msg");

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"+e[position]));
        context.startActivity(sendIntent);
    }
});
        return CustomView;

    }
}





