package com.example.markmypark;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class WorkerService {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveWorker(Worker worker) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("workers").document(worker.workerID).set(worker);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Worker> getAllWorkers() throws ExecutionException, InterruptedException {
        List<Worker> workerList = List.of(new Worker[]{new Worker("",0,null)});
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("workers").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            workerList.add(document.toObject(Worker.class));
        }
        return workerList;

    }
    public Worker getWorker(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = dbFirestore.collection("workers").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Worker worker = new Worker("",0,null);
        if (document.exists()) {
            worker=document.toObject(Worker.class);
        } else {
            System.out.println("No such document!");
        }
        return worker;
    }
}
