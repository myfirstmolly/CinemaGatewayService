package com.cinema.gateway.grpc;

import com.cinema.film.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class FilmGrpcController extends FilmServiceGrpc.FilmServiceImplBase {

    private String url = "cinema-films";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 7081).usePlaintext().build();;
    private FilmServiceGrpc.FilmServiceBlockingStub stub = FilmServiceGrpc.newBlockingStub(channel);

    @Override
    public void all(AllFilmsRequest request, StreamObserver<AllFilmsResponse> responseObserver) {
        AllFilmsRequest allFilmsRequest = AllFilmsRequest.newBuilder().build();
        AllFilmsResponse response = stub.all(allFilmsRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(FilmRequest request, StreamObserver<FilmResponse> responseObserver) {
        FilmRequest film = FilmRequest.newBuilder().
                setName(request.getName()).
                setDirector(request.getDirector()).
                setYear(request.getYear()).
                setGenre(request.getGenre()).
                build();
        FilmResponse response = stub.add(film);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(FilmByIdRequest request, StreamObserver<FilmResponse> responseObserver) {
        FilmByIdRequest filmRequest = FilmByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        FilmResponse response = stub.byId(filmRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(FilmByIdRequest request, StreamObserver<DeleteFilmResponse> responseObserver) {
        FilmByIdRequest filmRequest = FilmByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteFilmResponse response = stub.delete(filmRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
