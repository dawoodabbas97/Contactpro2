package dawoodabbas.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class AddContact extends ActionBarActivity {
    mydbhandler handler;
    String nam,imag,pno;
Button save;
    ImageView pic;
    EditText no,pn;
    String image,name;
    private  String TEMP_PHOTO_FILE = ".jpg";
    Integer REQ_CODE_PICK_IMAGE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    save=(Button)findViewById(R.id.save);

    }




   public void inputpic(View v)
    {
                                   Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                                   i.setType("image/*");
                                   i.putExtra("crop", "true");
                                   i.putExtra("aspectX", 1);
                                   i.putExtra("aspectY", 1);
                                   i.putExtra("outputX", 300);
                                   i.putExtra("outputY", 300);
                                   i.putExtra("return-data", true);
                                   i.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
                                   i.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                                   startActivityForResult(i, REQ_CODE_PICK_IMAGE);
                               }

    public void savecontact(View v)

    {
        handler=new mydbhandler(this,null,null,1);
        no=(EditText)findViewById(R.id.name);
        nam=no.getText().toString();
        pn=(EditText)findViewById(R.id.pno);
        pno=pn.getText().toString();


        if(image==null)
        {
            image="blank";
        }

      imag=image;
        Log.i("abbas","success");
        handler.addcont(nam,imag,pno);
        Toast.makeText(this,"Added "+nam,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }




        private Uri getTempUri() {
            return Uri.fromFile(getTempFile());
        }

        private File getTempFile() {

            Calendar c= Calendar.getInstance();
            c.setTime(new Date());
            int d=c.get(Calendar.DATE);
            int m=c.get(Calendar.MONTH);
            int y=c.get(Calendar.YEAR);
            TEMP_PHOTO_FILE=Long.toString(System.currentTimeMillis())+"_"+
                    Integer.toString(d)+"_"+Integer.toString(m)+"_"+Integer.toString(y)+".jpg";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {


                File file = new File(Environment.getExternalStorageDirectory(),TEMP_PHOTO_FILE);
                try {
                    file.createNewFile();
                } catch (IOException e) {}

                return file;
            } else {

                return null;
            }
        }
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode==REQ_CODE_PICK_IMAGE) {

            if (resultCode == RESULT_OK) {
                if (imageReturnedIntent!=null) {



                    String filePath= Environment.getExternalStorageDirectory()
                            +"/"+TEMP_PHOTO_FILE;
                    image=filePath;


                    Bitmap selectedImage =  BitmapFactory.decodeFile(filePath);
                    Log.i("abbas","imageset");
                    pic = (ImageView) findViewById(R.id.img);
                    pic.setImageBitmap(selectedImage);
                }
            }
        }
    }


}
