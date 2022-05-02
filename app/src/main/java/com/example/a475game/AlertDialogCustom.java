package com.example.a475game;

//Placeholder code for popup menus. Can be used on gameplay menu
//Can also be used for popup menu on custom grid button for user to enter a value



import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AlertDialogCustom extends AppCompatDialogFragment {
    private ImageView imageView, gamePlayBackBtn;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Insert activity for designated function
        View view = inflater.inflate(R.layout.alert_dialog_test, null);
        builder.setView(view)
                .setTitle("This is a test menu for future menu implementation")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

}
