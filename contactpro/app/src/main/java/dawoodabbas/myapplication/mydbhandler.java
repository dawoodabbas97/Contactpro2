package dawoodabbas.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawood Abbas on 6/28/2015.
 */
public class mydbhandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";


    public mydbhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db3) {

        String query="CREATE TABLE CONTACTS(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "image TEXT," +
                "pno TEXT);";

        db3.execSQL(query);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db3, int oldVersion, int newVersion) {
 db3.execSQL("DROP TABLE IF EXISITS CONTACTS");
        if (newVersion > oldVersion) {
            db3.execSQL("ALTER TABLE foo ADD COLUMN pno TEXT DEFAULT 0");
            }
                onCreate(db3);
        db3.close();
    }

    public void addcont(String n,String i,String k)
    {
        ContentValues values=new ContentValues();
        values.put("name", n);
        values.put("image",i);
        values.put("pno",k);
        SQLiteDatabase db3=getWritableDatabase();
        db3.insert("CONTACTS", null, values);
    }

    public void delcont(String name)
    {
        SQLiteDatabase db3=getWritableDatabase();
        Log.i("abbas", "delcont");
        db3.execSQL("DELETE FROM CONTACTS WHERE name=\""+name+"\";");
        db3.close();


    }

    public List<String> getnames(){
        String temp="";
        List<String> names=new ArrayList<String>();
        SQLiteDatabase db3=getWritableDatabase();
        String query="SELECT name FROM CONTACTS WHERE 1;";
        Cursor c=db3.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            temp=c.getString(c.getColumnIndex("name"));
            if(temp!=null){
                names.add(temp);

            }
            c.moveToNext();

        }

        return names;
    }
    public List<String> getpic(){
        List<String> pics=new ArrayList<String>();
        SQLiteDatabase db3=getWritableDatabase();
        String query="SELECT image FROM CONTACTS WHERE 1;";
        Cursor c=db3.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("image"))!=null){
                pics.add(c.getString(c.getColumnIndex("image")));

            }
            c.moveToNext();

        }
        return pics;
    }

    public List<String> getpno(){
        String temp="";
        List<String> p=new ArrayList<String>();
        SQLiteDatabase db3=getWritableDatabase();
        String query="SELECT pno FROM CONTACTS WHERE 1;";
        Cursor c=db3.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            temp=c.getString(c.getColumnIndex("pno"));
            if(temp!=null){
                p.add(temp);

            }
            c.moveToNext();

        }

        return p;
    }
}
