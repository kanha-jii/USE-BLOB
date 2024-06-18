package com.example.useblob;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.common.StorageSharedKeyCredential;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int READ_REQUEST_CODE = 42;

    private static final String accountName = "storageaccountzampl852f";
    private static final String accountKey = "zmuUJCRaIqd9/CPZcFX0DxcQrGCU8johImVJt6EMFUf2DyM4UyGDqAmNvEucq3dF0JLxJHHCU5MT4SIvBAi8QQ==";
    private static final String containerName = "pdfcorner";

    ActivityResultLauncher<Intent> pdfLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button uploadButton = findViewById(R.id.check);


        pdfLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
//                uploadPdfToBlob(o.getData().getData());
            }
        });

        loadPdf();



//        BlobUse blobUse = new BlobUse();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkBlobServiceConnection();
            }
        });
    }
    private void loadPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        pdfLauncher.launch(intent);
    }

//    private void uploadPdfToBlob(Uri uri) {
//        new Thread(() -> {
//            try {
//                // Create a BlobServiceClient
//
//                StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);
//                BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
//                        .endpoint(String.format("https://%s.blob.core.windows.net", accountName))
//                        .credential(credential)
//                        .buildClient();
//
//                // Get a reference to the container
//                BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
//
//                // Create the container if it does not exist
//                if (!containerClient.exists()) {
//                    containerClient.create();
//                }
//
//                // Get the file name from the URI
//                String fileName = DocumentsContract.getDocumentId(uri).split(":")[1];
//
//                // Get a reference to a blob
//                BlobClient blobClient = containerClient.getBlobClient(fileName);
//
//                // Open the file as an InputStream
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//
//                // Upload the file
//                blobClient.upload(inputStream, inputStream.available());
//                runOnUiThread(() -> Toast.makeText(MainActivity.this, "File uploaded successfully.", Toast.LENGTH_SHORT).show());
//
//            } catch (Exception e) {
//                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to upload file: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//            }
//        }).start();
//    }
//
//
//    private void checkBlobServiceConnection() {
//        new Thread(() -> {
//            try {
//                // Create a BlobServiceClient
//                StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);
//                BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
//                        .endpoint(String.format("https://%s.blob.core.windows.net", accountName))
//                        .credential(credential)
//                        .buildClient();
//
//                // Attempt to list the containers
//                PagedIterable<?> containers = blobServiceClient.listBlobContainers();
//
//                // If the above operation is successful, the connection is established
//                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Connection to Blob Service established successfully.", Toast.LENGTH_SHORT).show());
//
//            } catch (Exception e) {
//                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to connect to Blob Service: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//            }
//        }).start();
//    }
}