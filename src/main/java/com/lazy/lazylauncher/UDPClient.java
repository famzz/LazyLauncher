package com.lazy.lazylauncher;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    private AsyncTask<Void, Void, Void> async_cient;
    private String message;
    private InetAddress broadcastAddress;
    private int port;

    @SuppressLint("StaticFieldLeak")
    public void SendMessage() {
        async_cient = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                DatagramSocket ds = null;

                try {
                    ds = new DatagramSocket();
                    DatagramPacket dp;
                    dp = new DatagramPacket(message.getBytes(), message.length(), broadcastAddress, port);
                    ds.setBroadcast(true);
                    ds.send(dp);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (ds != null) {
                        ds.close();
                    }
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };

        if (Build.VERSION.SDK_INT >= 15)
            async_cient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else async_cient.execute();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBroadcastAddress(InetAddress broadcastAddress) {
        this.broadcastAddress = broadcastAddress;
    }

    public void setPort(int port) {
        this.port = port;
    }

}