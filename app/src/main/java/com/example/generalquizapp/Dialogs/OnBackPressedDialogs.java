package com.example.generalquizapp.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.generalquizapp.R;
import com.example.generalquizapp.Views.MainActivity;

public class OnBackPressedDialogs extends AppCompatDialogFragment {

    private dialogOnClickListener listener;
    private String LANGUAGE;

    public OnBackPressedDialogs(String LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
    }

//    @Override
//    public void onDismiss(@NonNull DialogInterface dialog) {
//        super.onDismiss(dialog);
//
//        listener.OnCancelClick();
//    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (LANGUAGE.equals("e")) {

            builder.setTitle("Warning!")
                    .setMessage("Your answers will not be saved!")
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface builderInterface, int i) {

                            listener.OnCancelClick();

                        }
                    }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface builderInterface, int i) {

                    listener.OnOkClick();

                }
            });
        }else {


            builder.setTitle(R.string.diqqat)
                    .setMessage(R.string.diqqat_xabari)
                    .setNegativeButton("Bekor Qilish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            listener.OnCancelClick();
                        }
                    }).setPositiveButton("Chiqish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    listener.OnOkClick();
                }
            });

        }

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);


        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener.resumeTimer();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        listener.OnCancelClick();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
        listener = (dialogOnClickListener) context;

        }catch (ClassCastException e){
            throw new ClassCastException("must implement dialog interface!");
        }

    }

    public interface dialogOnClickListener{
        void OnOkClick();
        void OnCancelClick();
        void resumeTimer();
    }


}
