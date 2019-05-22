package controller;

import java.io.IOException;

import com.nokia.hackathon.simApi.rpc.numberRequest;
import com.nokia.hackathon.simApi.rpc.numberResponse;
import com.nokia.hackathon.simApi.rpc.plateRequest;
import com.nokia.hackathon.simApi.rpc.plateResponse;
import com.nokia.hackathon.simApi.rpc.simApiGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class DetailsExtractor {

	public static String numberConverter(String plateNumber) throws IOException {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 17001).usePlaintext(true).build();
		simApiGrpc.simApiBlockingStub stub = simApiGrpc.newBlockingStub(channel);
		numberResponse number = stub.numberRecognition(numberRequest.newBuilder().setPlateNo(plateNumber).build());
		return number.getPhoneNo();
	}

	public static String plateConverter(int imageId) throws IOException {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 17001).usePlaintext(true).build();
		simApiGrpc.simApiBlockingStub stub = simApiGrpc.newBlockingStub(channel);
		plateResponse plate = stub.plateRecognition(plateRequest.newBuilder().setImage(imageId).build());
		return plate.getPlateNo();
	}
}
