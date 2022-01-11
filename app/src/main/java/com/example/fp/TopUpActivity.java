package com.example.fp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fp.Activity.ProfileActivity;

public class TopUpActivity extends AppCompatActivity {
    ImageView btnback;
    TextView title;
    Button btnAdd;
    String channelnotif = "channelku";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        title = findViewById(R.id.titlee);
        title.setText("Top Up Saldo");
        btnback = findViewById(R.id.iconleft);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TopUpActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        btnAdd = findViewById(R.id.topupakun);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(TopUpActivity.this, "Testing Notif");
                builder.setContentTitle("Pengisian anda telah sukses !");
                builder.setContentText("Selamat!!!");
                builder.setSmallIcon(R.drawable.ic_baseline_check_24);
                builder.setAutoCancel(true);

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new
                            NotificationChannel(channelnotif, "contoh channel", importance);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    builder.setChannelId(channelnotif);
                    assert mNotificationManager != null;
                    mNotificationManager.createNotificationChannel(notificationChannel);
                }
                assert mNotificationManager != null;
                mNotificationManager.notify((int) System.currentTimeMillis(), builder.build());
            }
        });
    }

}