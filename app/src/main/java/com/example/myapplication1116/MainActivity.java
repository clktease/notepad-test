package com.example.myapplication1116;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class MainActivity extends AppCompatActivity {
    EditText textmsg1;
    EditText emailmsg1;
    String msg="";
    static final int READ_BLOCK_SIZE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textmsg1=(EditText)findViewById(R.id.editText1);

    }
    // write text to file
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout1=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout1);
            outputWriter.write(textmsg1.getText().toString());
            outputWriter.close();
            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ReadBtn(View v){
        //read
        try{
            FileInputStream filein1 = openFileInput("mytextfile.txt");
            InputStreamReader inputReader = new InputStreamReader(filein1);
            char []s = new char[1024];
            int len = inputReader.read(s);
            msg = new String(s,0,len);
            //System.out.println(new String(s,0,len));
            inputReader.close();
            TextView tv1 = (TextView) findViewById(R.id.textView2);
            tv1.setText(new String(s,0,len));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void EmailBtn(View v) {

        Intent email = new Intent(Intent.ACTION_SEND);

        email.setType("message/rfc822");
        //email.putExtra(Intent.EXTRA_EMAIL,textmsg1.getText().toString().trim());
        email.putExtra(Intent.EXTRA_SUBJECT, "Note".trim());
        email.putExtra(Intent.EXTRA_TEXT   , msg.trim());
        System.out.println("Email set");
        try {
            //display email sent message
            System.out.println("Email sent");
            startActivity(Intent.createChooser(email, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}