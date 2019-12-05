package com.remoteyourcam.usb.ptp;

interface PtpConnection {
    void close();

    int getMaxPacketInSize();

    int getMaxPacketOutSize();

    PtpRequest createInRequest();

    int send(byte[] buffer, int length, int timeout);

    int receive(byte[] buffer, int maxLength, int timeout);

    void requestWait();

    int getVendorId();

    int getProductId();
}
