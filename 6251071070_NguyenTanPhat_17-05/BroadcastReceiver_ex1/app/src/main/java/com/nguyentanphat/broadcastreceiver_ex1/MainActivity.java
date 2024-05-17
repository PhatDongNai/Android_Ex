package com.nguyentanphat.broadcastreceiver_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.nguyentanphat.broadcastreceiver_ex1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Code here
//            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if(networkInfo != null && networkInfo.isConnected()){
//                if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
//                    binding.imvState.setImageResource(R.drawable.baseline_wifi);
//                    binding.txtState.setText("Connected with Wifi");
//                }else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
//                    binding.imvState.setImageResource(R.drawable.baseline_4g_plus_mobiledata_24);
//                    binding.txtState.setText("Connected with Mobile Data");
//                }
//            }else{
//                binding.imvState.setImageResource(R.drawable.baseline_disconnect_inactive_24);
//                binding.txtState.setText("No internet connection");
//            }

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            Network nw = connectivityManager.getActiveNetwork();
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            if(actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))){
                if(actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                    binding.imvState.setImageResource(R.drawable.baseline_wifi);
                    binding.txtState.setText("Connected with Wifi");
                } else if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    binding.imvState.setImageResource(R.drawable.baseline_4g_plus_mobiledata_24);
                    binding.txtState.setText("Connected with Mobile Data");
                }
            }else{
                binding.imvState.setImageResource(R.drawable.baseline_disconnect_inactive_24);
                binding.txtState.setText("No internet connection");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}