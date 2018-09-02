package com.fehr.nanodegree.musicalstructure.activity;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.fehr.nanodegree.musicalstructure.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    IInAppBillingService mService;
    Bundle skuDetails;
    String album1Price;
    String album2Price;

    Button buyAlbum1;
    Button buyAlbum2;
    Button mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_payment);

        buyAlbum1 = (Button) findViewById (R.id.buy_album_1);
        buyAlbum2 = (Button) findViewById (R.id.buy_album_2);
        mediaPlayer = (Button) findViewById (R.id.media_player);
        Log.v ("Before Intent", "done");

        Intent serviceIntent =
                new Intent ("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage ("com.android.vending");

        bindService (serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);

        ArrayList<String> skuList = new ArrayList<String> ();
        skuList.add ("BuyAlbum1");
        skuList.add ("BuyAlbum2");
        Bundle querySkus = new Bundle ();
        querySkus.putStringArrayList ("ITEM_ID_LIST", skuList);

        /*
         * I commented these below lines because i didn't upload the App to Google PLay console and bind it with In-App Purchases
         */

//        try {
//            skuDetails = mService.getSkuDetails(3,
//                    getPackageName(), "inapp", querySkus);
//        } catch (RemoteException e) {
//            e.printStackTrace ();
//        }

//        int response = skuDetails.getInt("RESPONSE_CODE");
//        if (response == 0) {
//            ArrayList<String> responseList
//                    = skuDetails.getStringArrayList("DETAILS_LIST");
//
//            for (String thisResponse : responseList) {
//
//                JSONObject object = null;
//                try {
//                    object = new JSONObject (thisResponse);
//                } catch (JSONException e) {
//                    e.printStackTrace ();
//                }
//                String sku = null;
//                try {
//                    sku = object.getString("productId");
//                } catch (JSONException e) {
//                    e.printStackTrace ();
//                }
//                String price = null;
//                try {
//                    price = object.getString("price");
//                } catch (JSONException e) {
//                    e.printStackTrace ();
//                }
//                if (sku.equals("BuyAlbum1"))
//                    album1Price = price;
//                else if (sku.equals("BuyAlbum2"))
//                    album2Price = price;
//            }
//        }
//
//        try {
//            Bundle ownedItems = mService.getPurchases(3, getPackageName(), "inapp", null);
//            if (response == 0) {
//                ArrayList<String> ownedSkus =
//                        ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
//                ArrayList<String>  purchaseDataList =
//                        ownedItems.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
//                ArrayList<String>  signatureList =
//                        ownedItems.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
//                String continuationToken =
//                        ownedItems.getString("INAPP_CONTINUATION_TOKEN");
//
//                for (int i = 0; i < purchaseDataList.size(); ++i) {
//                    String purchaseData = purchaseDataList.get(i);
//                    String signature = signatureList.get(i);
//                    String sku = ownedSkus.get(i);
//
//                    Toast.makeText (this,"You have bought " + sku + " with the purchased data " + purchaseData + " with the signature " + signature + ".",Toast.LENGTH_SHORT).show ();
//                }
//
//                // if continuationToken != null, call getPurchases again
//                // and pass in the token to retrieve more items
//            }
//        } catch (RemoteException e) {
//            e.printStackTrace ();
//        }


        buyAlbum1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                try {
                    Bundle buyIntentBundle = mService.getBuyIntent (3, getPackageName (),
                            "BuyAlbum1", "inapp", "");
//                    PendingIntent pendingIntent = buyIntentBundle.getParcelable ("BUY_INTENT");
//                    startIntentSenderForResult (pendingIntent.getIntentSender (),
//                            1001, new Intent (), Integer.valueOf (0), Integer.valueOf (0),
//                            Integer.valueOf (0));
                } catch (RemoteException e) {
                    e.printStackTrace ();
                }
//                catch (IntentSender.SendIntentException e) {
//                    e.printStackTrace ();
//                }

            }
        });

        buyAlbum2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                try {
                    Bundle buyIntentBundle = mService.getBuyIntent (3, getPackageName (),
                            "BuyAlbum2", "inapp", "");
//                    PendingIntent pendingIntent = buyIntentBundle.getParcelable ("BUY_INTENT");
//                    startIntentSenderForResult (pendingIntent.getIntentSender (),
//                            1001, new Intent (), Integer.valueOf (0), Integer.valueOf (0),
//                            Integer.valueOf (0));
                } catch (RemoteException e) {
                    e.printStackTrace ();
                }
//                catch (IntentSender.SendIntentException e) {
//                    e.printStackTrace ();
//                }
            }
        });

        mediaPlayer.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (PaymentActivity.this, MainActivity.class));
            }
        });
    }

    ServiceConnection mServiceConn = new ServiceConnection () {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
            mService = IInAppBillingService.Stub.asInterface (service);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy ();
        if (mService != null) {
            unbindService (mServiceConn);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            int responseCode = data.getIntExtra ("RESPONSE_CODE", 0);
            String purchaseData = data.getStringExtra ("INAPP_PURCHASE_DATA");
            String dataSignature = data.getStringExtra ("INAPP_DATA_SIGNATURE");

            if (resultCode == RESULT_OK) {
                try {
                    JSONObject jo = new JSONObject (purchaseData);
                    String sku = jo.getString ("productId");
                    String purchaseToken = jo.getString ("purchaseToken");
                    Toast.makeText (this, "You have bought the " + sku + ". Excellent choice", Toast.LENGTH_SHORT).show ();
                } catch (JSONException e) {
                    Toast.makeText (this, "Failed to parse purchase data.", Toast.LENGTH_SHORT).show ();
                    e.printStackTrace ();
                }
            }
        }
    }
}
