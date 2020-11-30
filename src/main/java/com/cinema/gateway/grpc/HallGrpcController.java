package com.cinema.gateway.grpc;

import com.cinema.hall.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class HallGrpcController extends HallServiceGrpc.HallServiceImplBase {

    private String url = "cinema-halls";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 7083).usePlaintext().build();;
    private HallServiceGrpc.HallServiceBlockingStub stub = HallServiceGrpc.newBlockingStub(channel);

    @Override
    public void all(AllHallRequest request, StreamObserver<AllHallsResponse> responseObserver) {
        AllHallRequest allFilmsRequest = AllHallRequest.newBuilder().build();
        AllHallsResponse response = stub.all(allFilmsRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(HallRequest request, StreamObserver<HallResponse> responseObserver) {
        HallRequest hall = HallRequest.newBuilder().
                setName(request.getName()).
                setLinesNum(request.getLinesNum()).
                setSeatsNum(request.getSeatsNum()).
                build();
        HallResponse response = stub.add(hall);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(HallByIdRequest request, StreamObserver<HallResponse> responseObserver) {
        HallByIdRequest hall = HallByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        HallResponse response = stub.byId(hall);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(HallByIdRequest request, StreamObserver<DeleteHallResponse> responseObserver) {
        HallByIdRequest hall = HallByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteHallResponse response = stub.delete(hall);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
