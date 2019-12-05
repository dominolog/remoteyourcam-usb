package com.remoteyourcam.usb.ptp;

import java.nio.ByteBuffer;

interface PtpRequest {
    void close();

    void queue(ByteBuffer bigIn1, int nextSize);
}
