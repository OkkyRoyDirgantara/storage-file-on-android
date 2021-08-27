package com.freshcipher.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String FILENAME = "dts2021.txt";
    TextView result_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createFile = findViewById(R.id.button_create_file);
        Button editFile = findViewById(R.id.button_edit_file);
        Button readFile = findViewById(R.id.button_read_file);
        Button deleteFile = findViewById(R.id.button_delete_file);
        result_file = findViewById(R.id.result_file);

        createFile.setOnClickListener(this);
        editFile.setOnClickListener(this);
        readFile.setOnClickListener(this);
        deleteFile.setOnClickListener(this);
    }

    void createFile(){
        String contents = "Okky Roy Dirgantara";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(contents.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(this, getFilesDir().toString(), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void editFile(){
        String ubah = "Kuwat Santoso";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(this, "file berhasil diubah", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void readFile(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);

        if(file.exists()){
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
                Toast.makeText(this, "Membaca File", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e){
                System.out.println("Error "+e.getMessage());
            }
            result_file.setText(text.toString());
        }
    }

    void deleteFile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            file.delete();
            Toast.makeText(this, "File Sudah Terhapus", Toast.LENGTH_SHORT).show();
        }
    }

    public void runIntructions(int id){
        switch (id){
            case  R.id.button_create_file:
                createFile();
                break;
            case R.id.button_edit_file:
                editFile();
                break;
            case R.id.button_read_file:
                readFile();
                break;
            case R.id.button_delete_file:
                deleteFile();
                break;
        }
    }


    @Override
    public void onClick(View view) {
        runIntructions(view.getId());
    }
}