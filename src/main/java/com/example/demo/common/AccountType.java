package com.example.demo.common;

import java.io.Serializable;

public enum AccountType implements Serializable {
	CHECKING,REGULAR,INTEREST;

    public String toString(){
        switch(this){
        case CHECKING :
            return "Checking";
        case REGULAR :
            return "Regular";
        case INTEREST :
            return "Interest";
        }
        return null;
    }
    
    public static boolean compareAccountType(AccountType accountTypeEnum,String accountType) {
    	return accountTypeEnum.toString().equalsIgnoreCase(accountType);
    }
}
