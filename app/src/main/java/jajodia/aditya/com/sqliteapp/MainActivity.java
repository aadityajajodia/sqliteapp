package jajodia.aditya.com.sqliteapp;

import android.database.Cursor;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText sur;
    EditText marks;


    DataBaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb=new DataBaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.editText4);
        sur=(EditText)findViewById(R.id.editText5);
        marks=(EditText)findViewById(R.id.editText6);

    }
    public void submit(View v)
    {
       boolean isInserted= mydb.insertData(name.getText().toString(),sur.getText().toString(),marks.getText().toString());
        if(isInserted==true)
        Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Data Not inserted",Toast.LENGTH_SHORT).show();
    }
    public void viewData(View v)
    {
        Cursor res= mydb.getAllData();
        if(res.getCount()==0)
        {
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {

            buffer.append("ID :"+res.getString(0)+"\n");
            buffer.append("Name :"+res.getString(1)+"\n");
            buffer.append("Surname :"+res.getString(2)+"\n");
            buffer.append("Marks :"+res.getString(3)+"\n\n");
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Keep going");
        builder.setMessage(buffer.toString());
        builder.show();

    }
    public void updateData(View v)
    {
        boolean isUpdated=mydb.updateData("1",name.getText().toString(),sur.getText().toString(),marks.getText().toString());
        if(isUpdated==true)
            Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Data Not inserted",Toast.LENGTH_SHORT).show();

    }
    public void deleteData(View v)
    {
        Integer i=mydb.deleteData("1");
        if(i==0)
            Toast.makeText(getApplicationContext(),"Data not deleted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Data deleted",Toast.LENGTH_SHORT).show();
    }
}
