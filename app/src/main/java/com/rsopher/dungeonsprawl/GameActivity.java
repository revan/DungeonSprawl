package com.rsopher.dungeonsprawl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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

        final GameActivity self = this;

        final TextView gameBoard = (TextView) findViewById(R.id.gameBoard);
        gameBoard.setTypeface(Typeface.MONOSPACE);

        final TextView goldView = (TextView) findViewById(R.id.goldView);

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

                final GameManager game = new GameManager(webSocket, self);

                webSocket.send("0");

                final Handler uiUpdater = new Handler(getApplicationContext().getMainLooper());
                final Runnable uiUpdate = new Runnable() {
                    @Override
                    public void run() {
                        gameBoard.setText(game.room.toString());
                        goldView.setText(game.getGold() + " gold");
                    }
                };
                uiUpdater.post(uiUpdate);

                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        System.out.println("I got a string: " + s);

                        //TODO switch to JSON instead of raw keys
                        if (!game.maybeSetGold(s)) {
                            game.setPlayerAction(s);
                            game.turn();
                        }

                        uiUpdater.post(uiUpdate);
                    }
                });
            }
        });
    }

    public void dimScreen() {
        new Handler(getApplicationContext().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.gameBoardHider).setVisibility(View.VISIBLE);
            }
        });
    }
    public void unDimScreen() {
        new Handler(getApplicationContext().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.gameBoardHider).setVisibility(View.INVISIBLE);
            }
        });
    }
}
