package com.example.mbti_final;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NicknameFragment extends DialogFragment {
    private final String TAG = "NicknameFragment";
    EditText edt_nickname;
    DatabaseReference databaseReference;
    Bundle bundle;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog 호출");
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_nickname, null);
        edt_nickname = view.findViewById(R.id.edt_nickname);

        bundle = getArguments();
        String uid = bundle.getString("uid");
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("nickname");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("닉네임을 지정해주세요")
                .setView(view)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "닉네임" + edt_nickname.getText().toString());
                        doPositiveClick();
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
    public void doPositiveClick(){
        databaseReference.setValue(edt_nickname.getText().toString());
    }
}