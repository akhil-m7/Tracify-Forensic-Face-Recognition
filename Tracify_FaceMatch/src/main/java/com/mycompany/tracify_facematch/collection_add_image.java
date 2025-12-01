package com.mycompany.thirdeye_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import java.util.List;

public class collection_add_image {
    public static final String collectionId = "Records";
    public static final String bucket = "tracifybuckets";
    public static final String photo = "f1-001-01.jpg";

    public static void main(String[] args) {
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
                .withRegion("us-east-1") // Ensure correct region
                .build();

        Image image = new Image()
                .withS3Object(new S3Object()
                .withBucket(bucket)
                .withName(photo));

        IndexFacesRequest indexFacesRequest = new IndexFacesRequest()
                .withImage(image)
                .withCollectionId(collectionId)
                .withExternalImageId(photo) // Assign external ID
                .withMaxFaces(5) // Limit number of faces indexed
                .withQualityFilter(QualityFilter.AUTO); // Filter low-quality images

        IndexFacesResult indexFacesResult = rekognitionClient.indexFaces(indexFacesRequest);

        System.out.println("Results for " + photo);
        List<FaceRecord> faceRecords = indexFacesResult.getFaceRecords();
        if (faceRecords.isEmpty()) {
            System.out.println("No faces detected in the image.");
        } else {
            for (FaceRecord faceRecord : faceRecords) {
                System.out.println("  Face ID: " + faceRecord.getFace().getFaceId());
                System.out.println("  Confidence: " + faceRecord.getFace().getConfidence());
            }
        }
    }
}