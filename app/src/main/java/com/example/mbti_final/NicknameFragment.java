package com.example.mbti_final;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class NicknameFragment extends DialogFragment {
    private final String TAG = "NicknameFragment";
    EditText edt_nickname;
    Button btn_profile;
    ImageView img_profile;
    DatabaseReference databaseReference;
    Bundle bundle;
    FirebaseUser firebaseUser;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog 호출");
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_nickname, null);
        edt_nickname = view.findViewById(R.id.edt_nickname);
        btn_profile = view.findViewById(R.id.btn_profile);
        img_profile = view.findViewById(R.id.img_profile);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        bundle = getArguments();
        String uid = bundle.getString("uid");
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("nickname");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("닉네임을 지정해주세요")
                .setView(view)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputUserData();
                        dialog.dismiss();
                    }
                });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent, 200);
            }
        });

        return builder.create();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 200 && resultCode == RESULT_OK && data!= null && data.getData() != null){
            Uri selectedImageUri = data.getData();
            img_profile.setImageURI(selectedImageUri);
        }
    }

    // 테스트 "Users" DB작성
    private void inputUserData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("name", firebaseUser.getDisplayName());
        userData.put("email", firebaseUser.getEmail());
        userData.put("uid", firebaseUser.getUid());
        userData.put("nickname", edt_nickname.getText().toString());

        databaseReference.setValue(userData);
    }
}