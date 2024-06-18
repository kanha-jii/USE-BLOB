package com.example.useblob;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;

import java.util.Base64;


public class MainActivity2 extends AppCompatActivity {
    ActivityResultLauncher<Intent> pdfLauncher;
    Uri pdfUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final String storageConnectionString = "DefaultEndpointsProtocol=https;"
                + "AccountName=storageaccountzampl852f;"
                + "AccountKey=zmuUJCRaIqd9/CPZcFX0DxcQrGCU8johImVJt6EMFUf2DyM4UyGDqAmNvEucq3dF0JLxJHHCU5MT4SIvBAi8QQ==";


        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint("https://storageaccountzampl852f.blob.core.windows.net")
                .credential(new StorageSharedKeyCredential("storageaccountzampl852f","zmuUJCRaIqd9/CPZcFX0DxcQrGCU8johImVJt6EMFUf2DyM4UyGDqAmNvEucq3dF0JLxJHHCU5MT4SIvBAi8QQ=="))
                .buildClient();
        blobServiceClient.listBlobContainers().forEach(action -> {
            System.out.println(action.getName());
        });

        BlobContainerClient blobContainerClient= blobServiceClient.getBlobContainerClient("pdfcorner");
        System.out.println(blobContainerClient.exists());

        blobContainerClient.listBlobs().forEach(action -> {
            System.out.println(action.getName());
        });

        BlobClient blobClient= blobContainerClient.getBlobClient("historypdf");
//        blobClient.upload(BinaryData.fromString("hello jii"));
        BinaryData binaryData=blobClient.downloadContent();
        System.out.println(binaryData.toString());

        pdfLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode()==RESULT_OK) {
                    pdfUri = o.getData().getData();
                }
            }
        });


        Button bt = findViewById(R.id.button);
        bt.setOnClickListener(v -> loadPdf());
    }
    void loadPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        pdfLauncher.launch(intent);
    }
}