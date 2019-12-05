package com.remoteyourcam.usb.ptp;

import android.hardware.usb.UsbRequest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class PtpSocketConnection implements PtpConnection {
    private final InetAddress address;
    private Socket socket;

    public PtpSocketConnection(InetAddress address, int port) {
        this.address = address;
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
            socket = null;
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getMaxPacketInSize() {
        return 0;
    }

    @Override
    public int getMaxPacketOutSize() {
        return 0;
    }

    @Override
    public PtpRequest createInRequest() {
        return new PtpSocketRequest(socket);
    }

    @Override
    public int bulkTransferOut(byte[] buffer, int length, int timeout) {

        try {
            return socket.getChannel().write(ByteBuffer.wrap(buffer, 0, length));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int bulkTransferIn(byte[] buffer, int maxLength, int timeout) {

        try {
            return socket.getChannel().read(ByteBuffer.wrap(buffer, 0, maxLength));
        } catch (IOException e) {
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
