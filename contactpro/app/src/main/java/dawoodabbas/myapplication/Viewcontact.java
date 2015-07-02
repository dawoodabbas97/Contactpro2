package dawoodabbas.myapplication;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class Viewcontact extends ActionBarActivity {

    TextView n,c;
    String na,co,im;
    ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcontact);

        n = (TextView) findViewById(R.id.na);
        c = (TextView) findViewById(R.id.c);
        i=(ImageView)findViewById(R.id.imageView);
        Bundle b = getIntent().getExtras();
        if (b == null) {
        return;
        }
        na=b.getString("name");
        co=b.getString("num");
        im=b.getString("image");
        n.setText(na);
        c.setText(co);
        if(im=="blank")
        {
            i.setImageResource(R.drawable.back);
        }

else
        {
            i.setImageBitmap(BitmapFactory.decodeFile(im));
        }
    }

    
}
