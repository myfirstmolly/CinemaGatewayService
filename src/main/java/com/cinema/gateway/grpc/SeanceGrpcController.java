package com.cinema.gateway.grpc;

import com.cinema.seance.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class SeanceGrpcController extends SeanceServiceGrpc.SeanceServiceImplBase {

    private String url = "localhost";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 7082).usePlaintext().build();
    private SeanceServiceGrpc.SeanceServiceBlockingStub stub = SeanceServiceGrpc.newBlockingStub(channel);

    @Override
    public void all(AllSeancesRequest request, StreamObserver<AllSeancesResponse> responseObserver) {
        AllSeancesRequest seancesRequest = AllSeancesRequest.newBuilder().build();
        AllSeancesResponse response = stub.all(seancesRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(SeanceRequest request, StreamObserver<SeanceResponse> responseObserver) {
        SeanceRequest seance = SeanceRequest.newBuilder().
                setSeanceDate(request.getSeanceDate()).
                setFilm(request.getFilm()).
                setHall(request.getHall()).
                setPrice(request.getPrice()).
                build();
        SeanceResponse response = stub.add(seance);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(SeanceByIdRequest request, StreamObserver<SeanceResponse> responseObserver) {
        SeanceByIdRequest hall = SeanceByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        SeanceResponse response = stub.byId(hall);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(SeanceByIdRequest request, StreamObserver<DeleteSeanceResponse> responseObserver) {
        SeanceByIdRequest hall = SeanceByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteSeanceResponse response = stub.delete(hall);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
