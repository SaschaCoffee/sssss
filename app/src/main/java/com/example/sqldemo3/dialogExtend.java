package com.example.sqldemo3;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class dialogExtend implements View.OnClickListener {



    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_VOLUME_DOWN){
          return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

    }
}
