package com.example.elvis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    MyPlayer myPlayer = new MyPlayer();
    Button btnStart;
    TextView tvElvis;
    AssetManager assetManager;
    String[] songsName;
    AssetFileDescriptor assetFileDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnPlay);
        tvElvis = findViewById(R.id.tvElvis);

        assetManager = getAssets();
        songsName = getFileNames("songs");

        try {
            assetFileDescriptor = getApplicationContext().getAssets().openFd(songsName);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor());
            myPlayer.addMelody(mediaPlayer);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String[] getFileNames(String path) {

        String [] list = null;
        try {

            list = assetManager.list(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void play(View view) {

        myPlayer.startMelody();
        if (btnStart.getText().equals("Play")) {
            btnStart.setText("Pause");
            myPlayer.startMelody();
        } else {
            btnStart.setText("Play");
            myPlayer.pauseMelody();
        }
    }


    public void next(View view) {

        myPlayer.nextMelody();
        btnStart.setText("Pause");
    }

    public void onChoose(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Выбор композиции");
//        builder.setView(songList);



        builder.setItems(songsName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myPlayer.startMelody();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }


}






