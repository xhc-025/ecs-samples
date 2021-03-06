/*
 * Copyright 2013 EMC Corporation. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.emc.vipr.s3.sample;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class _06_ReadObjectWithMetadata {

	public static void main(String[] args) throws Exception {
    	// create the AWS S3 Client
        AmazonS3 s3 = AWSS3Factory.getS3Client();

    	// retrieve the object key from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();

        //GetObjectMetadataRequest gom = new GetObjectMetadataRequest(b, k, v)
        try {
            GetObjectMetadataRequest gom = new GetObjectMetadataRequest(AWSS3Factory.S3_BUCKET, key);
            s3.getObjectMetadata(gom);
        }
        catch(com.amazonaws.services.s3.model.AmazonS3Exception e) {
            System.out.println("What happened: " + e.getMessage());
        }

        // read the specified object from the demo bucket
        S3Object object = s3.getObject(AWSS3Factory.S3_BUCKET, key);

        // get the metadata for the object
        ObjectMetadata metadata = object.getObjectMetadata();
        
        // print out the object key/value and metadata for validation
    	System.out.println( String.format("Metadata for [%s/%s]",
    			AWSS3Factory.S3_BUCKET, key));

        Map<String,String> metadataList = metadata.getUserMetadata();
        //String metaVal = metadataList.get("metakey1");
        //System.out.println(String.format("    %s = %s", "metakey1", metaVal));

        for (Map.Entry<String, String> entry : metadataList.entrySet())
        {
        	System.out.println(String.format("    %s = %s", entry.getKey(), entry.getValue()));
        }

    }
}
