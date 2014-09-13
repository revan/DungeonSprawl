package com.rsopher.dungeonsprawl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;

/**
 * Created by revan on 9/13/14.
 */
public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final TextView gameBoard = (TextView) findViewById(R.id.gameBoard);
        gameBoard.setTypeface(Typeface.MONOSPACE);

        Intent intent = getIntent();
        String address = intent.getStringExtra("address");

        AsyncHttpClient.getDefaultInstance().websocket(address, "http",
                new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception e, WebSocket webSocket) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }

                //TODO: construct new room as a callback
                final GameManager game = new GameManager();

                //TODO: send unique identifier
                webSocket.send("android send");

                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        System.out.println("I got a string: " + s);

                        //TODO switch to JSON instead of raw keys
                        game.setPlayerAction(s);

                        System.out.println(game.room.toString());

                        new Handler(getApplicationContext().getMainLooper()).post(new Runnable(){
                            @Override
                            public void run() {
                                gameBoard.setText(game.room.toString());
                            }
                        });
                        game.turn();
                    }
                });
            }
        });
    }
}
