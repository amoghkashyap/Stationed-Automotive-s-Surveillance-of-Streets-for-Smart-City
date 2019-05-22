package controller;

import mongoDB.DbOperations;

public class AddDb {
	private static DbOperations dbOperations = new DbOperations();
	
	public static void main(String[] args){
		
		   
		  dbOperations.insertPhNoRecognition("KA 09 EF 8464","+919632873485");
		  dbOperations.insertPlateRecognition(2, "KA 09 EF 8464");
		/*  dbOperations.deletePhNoRecognition("KA 09 EF 8464", null);
		  dbOperations.deletePlateRecognition(2, null);
		 */
	}

}
