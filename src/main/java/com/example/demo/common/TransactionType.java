package com.example.demo.common;

public enum TransactionType {
	WITHDRAW,DEPOSIT,INTEREST;
	
	 public String toString(){
        switch(this){
        case WITHDRAW :
            return "Withdraw";
        case DEPOSIT :
            return "Deposit";
        case INTEREST : 
        	return "Interest";
        }
        return null;
    }
	 
	 public boolean compareTransactionType(String transactionType) {
	    	return this.toString().equalsIgnoreCase(transactionType);
	    }
}
