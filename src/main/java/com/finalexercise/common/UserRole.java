package com.finalexercise.common;

import java.io.Serializable;

public enum UserRole implements Serializable {
	ADMIN,USER;

    public String toString(){
        switch(this){
        case ADMIN :
            return "ADMIN";
        case USER :
            return "USER";
        }
        return null;
    }
    
    public static boolean compareAccountType(UserRole userRoleEnum,String role) {
    	return userRoleEnum.toString().equalsIgnoreCase(role);
    }
}
