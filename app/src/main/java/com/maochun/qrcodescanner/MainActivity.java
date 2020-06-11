package com.maochun.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private IntentIntegrator mScanIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onScanQRCodeButtonClick(View v)
    {

        mScanIntegrator = new IntentIntegrator(MainActivity.this);
        mScanIntegrator.setPrompt("Start scan ...");
        mScanIntegrator.setTimeout(30000);
        mScanIntegrator.setCaptureActivity(ScanQRCodeActivity.class);
        mScanIntegrator.initiateScan();

    }

    //Callback when scan done
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null && scanningResult.getContents() != null)
        {
            final String scanContent = scanningResult.getContents();
            if (scanContent.length() > 0)
            {
                Log.i("ScanQRCode", scanContent);
                Toast toast = Toast.makeText(this, scanContent, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();

                return;
            }

        }

        super.onActivityResult(requestCode, resultCode, intent);
        Toast.makeText(getApplicationContext(),"Scan failed!!",Toast.LENGTH_LONG).show();

    }
}
