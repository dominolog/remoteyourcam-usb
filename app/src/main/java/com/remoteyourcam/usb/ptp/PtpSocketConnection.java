package com.remoteyourcam.usb.ptp;

import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class PtpSocketConnection implements PtpConnection {
    private final InetAddress address;
    private final int port;
    private Socket socket;

    public PtpSocketConnection(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    private void connect() {
        try {
            socket = new Socket(address, port);
        } catch (Exception e) {
            e.printStackTrace();
            socket = null;
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getMaxPacketInSize() {
        try {
            return socket.getReceiveBufferSize();
        } catch (Exception e) {
            return 65535;
        }
    }

    @Override
    public int getMaxPacketOutSize() {
        try {
            return socket.getSendBufferSize();
        } catch (Exception e) {
            return 65535;
        }
    }

    @Override
    public PtpRequest createInRequest() {
        if (socket == null) {
            connect();
        }
        return new PtpSocketRequest(socket);
    }

    @Override
    public int send(byte[] buffer, int length, int timeout) {

        try {
            socket.getOutputStream().write(buffer, 0, length);
            return length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int receive(byte[] buffer, int maxLength, int timeout) {

        try {
            return socket.getInputStream().read(buffer, 0, maxLength);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void requestWait() {
        try {
            socket.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getVendorId() {
        return 0;
    }

    @Override
    public int getProductId() {
        return 0;
    }
}
