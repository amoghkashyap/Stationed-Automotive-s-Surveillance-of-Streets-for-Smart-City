package controller;

import java.io.IOException;

import org.json.simple.JSONObject;

import com.nokia.hackathon.simulation.rpc.detectionRequest;
import com.nokia.hackathon.simulation.rpc.response;
import com.nokia.hackathon.simulation.rpc.simulationApiGrpc.simulationApiImplBase;

import Connections.UIAndUser;
import io.grpc.BindableService;
import io.grpc.stub.StreamObserver;

public class ControllerApiImpl extends simulationApiImplBase implements BindableService {

	@Override
	public void carDetection(detectionRequest request, StreamObserver<response> responseObserver) {
		String phoneNumber = null;
		String plateNumber = null;
		int image = request.getImageID();
		String latitude = request.getLatitude();
		String longitude = request.getLongitude();
		String status = request.getStatus();
		String parkedTime = request.getParkedTime();
		try {
			plateNumber = DetailsExtractor.plateConverter(image);
			phoneNumber = DetailsExtractor.numberConverter(plateNumber);

		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(image + " " + latitude + " " + longitude + " " + status + " " + parkedTime + " " + phoneNumber+" "+plateNumber);
	    UIAndUser.UIAndUserData(image, phoneNumber, status, plateNumber, latitude, longitude);
		
		
		
		responseObserver.onNext(null);
		responseObserver.onCompleted();

	}

}
