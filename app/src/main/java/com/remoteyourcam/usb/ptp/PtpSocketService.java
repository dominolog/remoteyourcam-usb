package com.remoteyourcam.usb.ptp;

import android.content.Context;
import android.content.Intent;

import java.net.InetAddress;
import java.net.SocketAddress;

public class PtpSocketService implements PtpService {
    private Camera.CameraListener listener;
    private PtpCamera camera;

    @Override
    public void setCameraListener(Camera.CameraListener listener) {
        this.listener = listener;
    }

    @Override
    public void initialize(Context context, Intent intent) {
        int vendorId = PtpConstants.CanonVendorId;
        InetAddress address = new InetAddress("192.168.0.1");
        if (vendorId == PtpConstants.CanonVendorId) {
            PtpSocketConnection connection = new PtpSocketConnection(address);
            camera = new EosCamera(connection, listener, new WorkerNotifier(context));
        } else if (vendorId == PtpConstants.NikonVendorId) {
            PtpSocketConnection connection = new PtpSocketConnection(address);
            camera = new NikonCamera(connection, listener, new WorkerNotifier(context));
        }
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void lazyShutdown() {

    }
}
