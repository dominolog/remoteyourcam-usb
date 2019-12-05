package com.remoteyourcam.usb.ptp;

import android.hardware.usb.UsbRequest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

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
        return 0;
    }

    @Override
    public int bulkTransferIn(byte[] buffer, int maxLength, int timeout) {
        return 0;
    }

    @Override
    public void requestWait() {

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
