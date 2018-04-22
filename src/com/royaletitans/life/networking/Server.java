package com.royaletitans.life.networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.royaletitans.life.Configuration;
import com.royaletitans.life.lib.Buffer;
import com.royaletitans.life.networking.messages.ClientMessage;
import com.royaletitans.life.networking.messages.Headers;
import com.royaletitans.life.networking.messages.ServerMessage;
import com.royaletitans.life.utils.Debugger;

public class Server {
    private boolean mRunning = true;

    public void connect() throws IOException {
        ServerLogic.getInstance().initialize();

        final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9339));
        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel channel, Object attachment) {
                listener.accept(null, this);

                boolean running = true;

                try {
                    Buffer buf = Buffer.allocate(7);
                    int read = channel.read(buf.getByteBuffer()).get(5, TimeUnit.SECONDS);

                    while (read != -1 && running) {
                        if (buf.position() > 2) {
                            buf.flip();

                            Headers headers = new Headers(buf);

                            if (Configuration.DEBUG) {
                                Debugger.info("Received " + headers.getId() + "! (Length: " + headers.getLength() + ")");
                            }

                            buf.clear();
                            buf = Buffer.allocate(headers.getLength());
                            read = 0;
                            while (read != headers.getLength()) {
                                read = channel.read(buf.getByteBuffer()).get(10, TimeUnit.SECONDS);
                            }

                            buf.flip();
                            ClientMessage message = ServerLogic.getInstance().route(headers, buf);

                            if (message != null) {
                                Buffer b = message.getBuffer();
                                if (Configuration.DEBUG) {
                                    b.rewind();
                                }

                                ServerMessage[] handled = ServerLogic.getInstance().handle(headers, message);

                                if (handled != null) {
                                    for (ServerMessage serverMessage : handled) {

                                        b = serverMessage.getBuffer();

                                        Buffer encrypted = ServerLogic.getInstance().mRC4.encrypt(b.array());

                                        if (encrypted != null) {
                                            headers = new Headers(serverMessage.getId(), encrypted.capacity(), serverMessage.getVersion());
                                            channel.write(headers.toBuffer().getByteBuffer());

                                            if (Configuration.DEBUG) {
                                                Debugger.info("Sent " + headers.getId() + "! (Length: " + headers.getLength() + ")");
                                            }

                                            channel.write(encrypted.getByteBuffer());
                                            encrypted.clear();

                                            if (Configuration.DEBUG) {
                                                b.rewind();
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (Configuration.DEBUG) {
                                    Debugger.info("Id: " + headers.getId() + " not managed.");
                                }
                            }

                            buf.clear();
                            buf = Buffer.allocate(7);
                            read = channel.read(buf.getByteBuffer()).get(10, TimeUnit.SECONDS);
                        } else {
                            running = false;
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException ignored) {
                    // Client disconnected
                }

                Debugger.info("End of connection");
                try {
                    if (channel.isOpen()) {
                        channel.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                Debugger.error("Error: " + exc.toString());
            }
        });
    }

    public boolean isRunning() {
        return mRunning;
    }
}
