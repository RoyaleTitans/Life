package com.royaletitans.life;

import java.io.IOException;

import com.royaletitans.life.networking.Server;
import com.royaletitans.life.utils.Debugger;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.connect();

        while (server.isRunning()) {
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Debugger.info("Server died.");
    }
}
