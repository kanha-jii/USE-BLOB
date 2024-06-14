package com.example.useblob;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;

public class BlobUse {
    final String account = "storageaccountzampl852f";
    final String accountKey = "zmuUJCRaIqd9/CPZcFX0DxcQrGCU8johImVJt6EMFUf2DyM4UyGDqAmNvEucq3dF0JLxJHHCU5MT4SIvBAi8QQ==";
    final String containerName = "pdfcorner";

    StorageSharedKeyCredential sharedKeyCredential = new StorageSharedKeyCredential(account, accountKey);
    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;"
            + "AccountName=[pdfcorner];"
            + "AccountKey=[zmuUJCRaIqd9/CPZcFX0DxcQrGCU8johImVJt6EMFUf2DyM4UyGDqAmNvEucq3dF0JLxJHHCU5MT4SIvBAi8QQ==]";
//        public final BlobServiceClient blobServiceClient = new BlobServiceClient("https://${account}.blob.core.windows.net",sharedKeyCredential);
    public final BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(storageConnectionString).buildClient();

    BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

    public BlobUse() {
        if (!containerClient.exists()) {
            containerClient.create();
            System.out.println(containerClient.getAccountName());
        }
        System.out.println(sharedKeyCredential.getAccountName());
    }
}
