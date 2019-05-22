package simulator;

import org.json.simple.JSONObject;

import com.nokia.hackathon.simApi.rpc.simApiGrpc.simApiImplBase;
import com.nokia.hackathon.simulation.rpc.detectionRequest;
import com.nokia.hackathon.simulation.rpc.response;
import com.nokia.hackathon.simulation.rpc.simulationApiGrpc;
import com.nokia.hackathon.simulation.rpc.simulationApiGrpc.simulationApiImplBase;

import io.grpc.BindableService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

public class DetectedCarRequest extends simulationApiImplBase implements BindableService {

	
	public void requestGrpc(JSONObject jsonObject) {
		String latitude = (String) jsonObject.get("latitude");
		String longitude = (String) jsonObject.get("longitude");
		int imageId = (int) jsonObject.get("ImageId");
		String status = (String) jsonObject.get("status");
		String parkedTime = (String) jsonObject.get("parkedTime");
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 17002).usePlaintext(true).build();
		simulationApiGrpc.simulationApiBlockingStub stub = simulationApiGrpc.newBlockingStub(channel);
	    stub.carDetection(detectionRequest.newBuilder().setImageID(imageId).setLatitude(latitude).setLongitude(longitude).setParkedTime(parkedTime).setStatus(status).build());

	    
	}

}
