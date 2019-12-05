package com.remoteyourcam.usb.ptp;

import android.hardware.usb.UsbRequest;

import java.nio.ByteBuffer;

class PtpUsbRequest implements PtpRequest{
    private final UsbRequest request;

    public PtpUsbRequest(UsbRequest request) {
        this.request = request;
    }

    @Override
    public void close() {
        request.close();
    }

    @Override
    public void queue(ByteBuffer buffer, int size) {
        request.queue(buffer, size);
    }
}
