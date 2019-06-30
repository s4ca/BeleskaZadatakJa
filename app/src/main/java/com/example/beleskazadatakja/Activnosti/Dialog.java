package com.example.beleskazadatakja.Activnosti;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Dialog extends AlertDialog.Builder {

    public Dialog(Context context) {
        super(context);
        setTitle("Moj Dialog");
        setMessage("Aleksandar Bojanic AAD");
        setPositiveButton("Potrvrdi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }


    public AlertDialog prepereDialog (){
        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}

