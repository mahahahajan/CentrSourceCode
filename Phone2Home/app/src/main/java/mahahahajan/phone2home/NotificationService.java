package mahahahajan.phone2home;

/**
 * Created by Tiklum on 1/31/15.
 */

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;

public class NotificationService extends NotificationListenerService {
    Context context;
    ArrayList <String> messages = new ArrayList();
    private static final String TAG = "BluetoothChatServices";
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        String pack = sbn.getPackageName();
        String ticker = sbn.getNotification().tickerText.toString();
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();
        messages.add(title);
        messages.add(text);
        Log.i("Package",pack);
        Log.i("Ticker",ticker);
        Log.i("Title",title);
        Log.i("Text",text);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();



        Intent msgrcv = new Intent("Msg");
        msgrcv.putExtra("package", pack);
        msgrcv.putExtra("ticker", ticker);
        msgrcv.putExtra("title", title);
        msgrcv.putExtra("text", text);
        LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
    }
        private void sendDataToPairedDevice() {
            Log.i(TAG, "Begin mConnectedThread");
        }
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");
    }
}