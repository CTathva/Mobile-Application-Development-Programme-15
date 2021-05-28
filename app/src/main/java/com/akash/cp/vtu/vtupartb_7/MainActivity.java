package com.akash.cp.vtu.vtupartb_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Base{
    EditText mEditText1,mEditText2;
    Button mButtonCopy,mButtonPaste;
    ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listener();
    }

    @Override
    public void init() {
        mButtonCopy=(Button) findViewById(R.id.copy);
        mButtonPaste=(Button) findViewById(R.id.paste);
        mEditText1=(EditText) findViewById(R.id.et1);
        mEditText2=(EditText) findViewById(R.id.et2);
    }

    @Override
    public void listener() {
        mButtonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mEditText1.getText()))
                {
                    Toast.makeText(MainActivity.this, "copy", Toast.LENGTH_SHORT).show();
                    String text=mEditText1.getText().toString();
                    clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", text);
                    clipboard.setPrimaryClip(clip);
                }

            }
        });
        mButtonPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "paste", Toast.LENGTH_SHORT).show();
                clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                try {
                    CharSequence text = clipboard.getPrimaryClip().getItemAt(0).getText();
                    mEditText2.setText(text);
                } catch (Exception e) {
                    Log.d("exception",e.getMessage());
                }
            }
        });

    }
}