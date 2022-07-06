package com.example.trivia.helpers;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.trivia.LoginActivity;
import com.example.trivia.MainActivity;
import com.example.trivia.model.UserDetails;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.io.Console;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserHelper {

    FirebaseFirestore db;

    public void setUserFirstTime(String userName, String email)
    {
        db = FirebaseFirestore.getInstance();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("animals", 1);
        userInfo.put("food", 1);
        userInfo.put("movies", 1);
        userInfo.put("music", 1);
        userInfo.put("sport", 1);
        userInfo.put("score", 0);
        userInfo.put("userName", userName);
        CollectionReference usersDB = db.collection("Users");
        usersDB.document(email).set(userInfo);
    }





}
