package controller;

import com.nokia.hackathon.simApi.rpc.numberRequest;
import com.nokia.hackathon.simApi.rpc.numberResponse;
import com.nokia.hackathon.simApi.rpc.plateRequest;
import com.nokia.hackathon.simApi.rpc.plateResponse;
import com.nokia.hackathon.simApi.rpc.simApiGrpc.simApiImplBase;

import api.API;
import io.grpc.BindableService;
import io.grpc.stub.StreamObserver;

public class APIImpl extends simApiImplBase implements BindableService {

	@Override
	public void numberRecognition(numberRequest request, StreamObserver<numberResponse> responseObserver) {
		String phNo = API.phNoRecognitionAPI(request.getPlateNo());
		numberResponse res = numberResponse.newBuilder().setPhoneNo(phNo).build();
		responseObserver.onNext(res);
		responseObserver.onCompleted();
	}

	@Override
	public void plateRecognition(plateRequest request, StreamObserver<plateResponse> responseObserver) {
		String plateNo = API.plateRecognitionAPI(request.getImage());
		plateResponse res = plateResponse.newBuilder().setPlateNo(plateNo).build();
		responseObserver.onNext(res);
		responseObserver.onCompleted();

	}
	
	
	
}
