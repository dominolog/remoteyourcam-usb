package com.remoteyourcam.usb.ptp;

import android.content.Context;
import android.content.Intent;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class PtpSocketService implements PtpService {
    private Camera.CameraListener listener;
    private PtpCamera camera;

    public PtpSocketService(Context context) {

    }

    @Override
    public void setCameraListener(Camera.CameraListener listener) {
        this.listener = listener;
    }

    @Override
    public void initialize(Context context, Intent intent) {
        int vendorId = PtpConstants.CanonVendorId;
        InetAddress address = null;
        try {
            address = InetAddress.getByName("192.168.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int port = 15750;
        if (vendorId == PtpConstants.CanonVendorId) {
            PtpSocketConnection connection = new PtpSocketConnection(address, port);
            camera = new EosCamera(connection, listener, new WorkerNotifier(context));
        } else if (vendorId == PtpConstants.NikonVendorId) {
            PtpSocketConnection connection = new PtpSocketConnection(address, port);
            camera = new NikonCamera(connection, listener, new WorkerNotifier(context));
        }
        camera.retrieveStorages(new Camera.StorageInfoListener() {
                                    @Override
                                    public void onStorageFound(int handle, String label) {

                                    }

                                    @Override
                                    public void onAllStoragesFound() {

                                    }

                                    @Override
                                    public void onImageHandlesRetrieved(int[] handles) {

                                    }
                                }
        );
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void lazyShutdown() {

    }
}
