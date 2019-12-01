package com.lazy.lazylauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    public InetAddress BROADCASTADDRESS;
    public int PORT = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLaunchButtonClick();
    }

    private void setupLaunchButtonClick() {
        Button launchButton =  findViewById(R.id.launchButton);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UDPClient client = new UDPClient();
                try {
                    client.setBroadcastAddress(InetAddress.getByName("192.168.0.24"));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                client.setPort(PORT);
                client.setMessage("Spotify");
                client.SendMessage();
            }
        });
    }

}
