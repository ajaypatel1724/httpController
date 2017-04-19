package com.elitecore.elitejson;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
	LOGIN("Login"),
	LOGOUT("Logout"),
	;
	
	public final String operationName;
	private static final Map<String, Operation> mapOperation;
	
	Operation(String operation){
		this.operationName = operation;
	}
	
	static{
		mapOperation = new HashMap<String, Operation>();
		for(Operation operation : values()){		
			mapOperation.put(operation.getOperationName(), operation);
		}
	}
	
	public String getOperationName() {
		return operationName;
	}
	
	public static Operation getOperation(String operationKey){
		return mapOperation.get(operationKey);
	}
}
