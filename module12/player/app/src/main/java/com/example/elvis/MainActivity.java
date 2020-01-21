package com.example.elvis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyPlayer myPlayer = new MyPlayer();
    Button btnStart;
    TextView tvElvis;
    ListView songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPlayer.addMelody(MediaPlayer.create(this, R.raw.elvis_tutti_frutti),"Тутти-Фрутти");
        myPlayer.addMelody(MediaPlayer.create(this, R.raw.elvis_jailhouse_rock),"Тюремный рок");
        myPlayer.addMelody(MediaPlayer.create(this, R.raw.elvis_heartbreak_hotel),"Отель разбитых сердец");
        myPlayer.addMelody(MediaPlayer.create(this, R.raw.elvis_falling_in_love),"Не могу не влюбиться");
        btnStart = findViewById(R.id.btnPlay);
        tvElvis = findViewById(R.id.tvElvis);

        songList = new ListView(this);
        ArrayAdapter<MediaPlayer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myPlayer.melodyList);
        songList.setAdapter(adapter);
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
        builder.setView(songList);

        //Здесь я попытался использовать метод setItems, для добавления списка мелодий. Но программа выводит ошибку.

/*        builder.setItems(myPlayer.melodyList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Выбранная мелодия: " + myPlayer.melodyList.get(which),
                        Toast.LENGTH_SHORT).show();
            }
        });*/

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



