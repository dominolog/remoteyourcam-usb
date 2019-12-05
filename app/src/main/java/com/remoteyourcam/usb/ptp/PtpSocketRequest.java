package com.remoteyourcam.usb.ptp;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

class PtpSocketRequest implements PtpRequest {
    private final Socket socket;

    public PtpSocketRequest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void close() {

    }

    @Override
    public void queue(ByteBuffer bigIn1, int nextSize) {
        try {
            socket.getOutputStream().write(bigIn1.array(), 0, nextSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
