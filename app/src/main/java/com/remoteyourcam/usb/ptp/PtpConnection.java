package com.remoteyourcam.usb.ptp;

interface PtpConnection {
    void close();

    int getMaxPacketInSize();

    int getMaxPacketOutSize();

    PtpRequest createInRequest();

    int bulkTransferOut(byte[] buffer, int length, int timeout);

    int bulkTransferIn(byte[] buffer, int maxLength, int timeout);

    void requestWait();

    int getVendorId();

    int getProductId();
}
