package com.login.loginUser.dataTest;

import com.login.loginUser.model.User;

public class Data {
    public static User user01(boolean conId){
        User user = (conId) ? new User (1L,"jonathan","fonseca",318703584L,
                "jonathan@gmail.com","SnowPatrol21%","SnowPatrol21%"):
                new User (null,"jonathan","fonseca",318703584L,
                        "jonathan@gmail.com","SnowPatrol21%","SnowPatrol21%");
        return user;
    }

    public static User user02(){
        return new User(null,
                "jose",
                "marquez",
                3107532768L,
                "jose@gmail.com",
                "SnowPatrol21%",
                "SnowPatrol21%"
        );
    }

    public static User user03(){
        return new User(null,
                "Andrea",
                "Garzon",
                3225308585L,
                "Andrea20@gmail.com",
                "SnowPatrol21%",
                "SnowPatrol21%"
        );
    }
}
