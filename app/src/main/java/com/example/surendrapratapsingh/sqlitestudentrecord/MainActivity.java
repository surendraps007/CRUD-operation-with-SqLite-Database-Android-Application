package com.example.surendrapratapsingh.sqlitestudentrecord;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText editTextId,editName,editEmail,editCC;
    Button buttonAdd,buttonGetData,buttonUpdate,buttonDelete,buttonViewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new DatabaseHelper(this);

        editTextId=findViewById(R.id.editText_id);
        editName=findViewById(R.id.editText_name);
        editEmail=findViewById(R.id.editText_email);
        editCC=findViewById(R.id.editText_CC);

        buttonAdd=findViewById(R.id.button_add);
        buttonGetData=findViewById(R.id.button_view);
        buttonUpdate=findViewById(R.id.button_update);
        buttonDelete=findViewById(R.id.button_delete);
        buttonViewAll=findViewById(R.id.button_viewAll);

        AddData();
        getData();
        ViewAll();
        updateData();
       // showMessage("title","testing the title");

    }
            public void AddData(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String myEmail=editEmail.getText().toString();
                boolean isInserted= mydb.insertData(editName.getText().toString(), editEmail.getText().toString(), editCC.getText().toString());
                if(isInserted == true)
                {
                    Toast.makeText(MainActivity.this,"Data Added successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"omething went Wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
            public void getData(){
                buttonGetData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id=editTextId.getText().toString();

                        if(id.equals(String.valueOf(""))){
                            editTextId.setError("Enter ID");
                            return ;
                        }
                        Cursor cursor=mydb.getData(id);
                        String data=null;

                        if(cursor.moveToNext())
                        {
                          data= "ID: "+cursor.getString(0)+"\n"+
                                    "Name: "+cursor.getString(1)+"\n"+
                                  "Email"+cursor.getString(2)+"\n"+
                                "Course Count"+cursor.getString(3)+"\n";
                        }
                        showMessage("Data : ",data);
                    }
                });

            }
            public void ViewAll() {
                        buttonViewAll.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Cursor cursor=mydb.getAllData();

                                //checking for empty cursor;
                                if(cursor.getCount() == 0)
                                {
                                    showMessage("Error","Nothing found in Database");
                                }
                                StringBuffer stringBuffer=new StringBuffer();

                                while (cursor.moveToNext())
                                {
                                    stringBuffer.append("Id : "+cursor.getString(0)+"\n");
                                    stringBuffer.append("Name : "+cursor.getString(1)+"\n");
                                    stringBuffer.append("Email : "+cursor.getString(2)+"\n");
                                    stringBuffer.append("Course Count : "+cursor.getString(3)+"\n\n");

                            }
                                    showMessage("All Data ",stringBuffer.toString());
                            }
                        });
                    }
            public void updateData(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=mydb.updateData(editTextId.getText().toString(),
                                                editName.getText().toString(),
                                                editEmail.getText().toString(),
                                                editCC.getText().toString());
                if(isUpdate == true)
                {
                    Toast.makeText(MainActivity.this,"Update Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Failed to Update",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showMessage(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}










