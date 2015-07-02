package dawoodabbas.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Dawood Abbas on 7/1/2015.
 */

public class logdb extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="caller.db";

    public logdb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db5) {
        String query="CREATE TABLE CALLER(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name1 TEXT," +
                "image1 TEXT," +
                "pno1 TEXT,"+
                "time TEXT,"+
                "calltype TEXT);";
        db5.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db5, int oldVersion, int newVersion) {

        db5.execSQL("DROP TABLE IF EXISITS CALLER");
        if (newVersion > oldVersion) {
            db5.execSQL("ALTER TABLE foo ADD COLUMN pno TEXT DEFAULT 0");
        }
        onCreate(db5);
        db5.close();

    }
    public void addlog(String n,String i,String k,String t,String ty)
    {
        ContentValues values=new ContentValues();
        values.put("name1", n);
        values.put("image1", i);
        values.put("pno1", k);
        values.put("time",t);
        values.put("calltype",ty);
        SQLiteDatabase db5=getWritableDatabase();
        db5.insert("CALLER", null, values);
    }
    public List<String> getnames1(){
        String temp="";
        List<String> names=new ArrayList<String>();
        SQLiteDatabase db5=getWritableDatabase();
        String query="SELECT name1 FROM CALLER WHERE 1;";
        Cursor c1=db5.rawQuery(query,null);
        c1.moveToFirst();
        while(!c1.isAfterLast()){
            temp=c1.getString(c1.getColumnIndex("name1"));
            if(temp!=null){
                names.add(temp);

            }
            c1.moveToNext();

        }

        return names;
    }
    public List<String> getpic1(){
        List<String> pics=new ArrayList<String>();
        SQLiteDatabase db5=getWritableDatabase();
        String query="SELECT image1 FROM CALLER WHERE 1;";
        Cursor c1=db5.rawQuery(query,null);
        c1.moveToFirst();
        while(!c1.isAfterLast()){
            if(c1.getString(c1.getColumnIndex("image1"))!=null){
                pics.add(c1.getString(c1.getColumnIndex("image1")));

            }
            c1.moveToNext();

        }
        return pics;
    }

    public List<String> getpno1(){
        String temp="";
        List<String> p=new ArrayList<String>();
        SQLiteDatabase db5=getWritableDatabase();
        String query="SELECT pno1 FROM CALL WHERE 1;";
        Cursor c1=db5.rawQuery(query,null);
        c1.moveToFirst();
        while(!c1.isAfterLast()){
            temp=c1.getString(c1.getColumnIndex("pno1"));
            if(temp!=null){
                p.add(temp);

            }
            c1.moveToNext();

        }

        return p;
    }

    public List<String> gettime(){
        List<String> time=new ArrayList<String>();
        SQLiteDatabase db5=getWritableDatabase();
        String query="SELECT time FROM CALL WHERE 1;";
        Cursor c1=db5.rawQuery(query,null);
        c1.moveToFirst();
        while(!c1.isAfterLast()){
            if(c1.getString(c1.getColumnIndex("time"))!=null){
                time.add(c1.getString(c1.getColumnIndex("time")));

            }
            c1.moveToNext();

        }
        return time;
    }
    public List<String> type(){
        List<String> type=new ArrayList<String>();
        SQLiteDatabase db5=getWritableDatabase();
        String query="SELECT calltype FROM CALL WHERE 1;";
        Cursor c1=db5.rawQuery(query,null);
        c1.moveToFirst();
        while(!c1.isAfterLast()){
            if(c1.getString(c1.getColumnIndex("calltype"))!=null){
                type.add(c1.getString(c1.getColumnIndex("calltype")));

            }
            c1.moveToNext();

        }
        return type;
    }
}


