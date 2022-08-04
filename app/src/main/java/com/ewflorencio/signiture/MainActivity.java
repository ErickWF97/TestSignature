package com.ewflorencio.signiture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    File folder;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnSalvar);
        SignaturePad signature = (SignaturePad) findViewById(R.id.sgtTest);
        ImageView img = (ImageView) findViewById(R.id.imgTest);
        TextView txt = (TextView) findViewById(R.id.txtTest);

        folder = new File(Environment.getExternalStorageDirectory(),Environment.DIRECTORY_PICTURES);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = signature.getSignatureBitmap();

                img.setImageBitmap(bitmap);
                txt.setText(String.valueOf(bitmap.toString().getBytes()));

                try {
                    FileOutputStream fileOutputStream = context.openFileOutput("teste", Integer.parseInt(folder.toString()));
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                signature.clear();

            }
        });

    }
}