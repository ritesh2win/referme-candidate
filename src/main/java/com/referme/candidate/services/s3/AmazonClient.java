package com.referme.candidate.services.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.referme.candidate.model.DownloadedFile;

@Service
public class AmazonClient {

	private AmazonS3 s3client;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;

	@Value("${amazonProperties.bucketName}")
	private String bucketName;

	@Value("${amazonProperties.accessKey}")
	private String accessKey;

	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	@Value("${amazonProperties.regionName}")
	private String regionName;

	/**
	 * This post construct will initialize AWSCredentials and build s3Client with
	 * specified region.
	 */
	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey + "7OYXJBRVKBW",
				this.secretKey + "fouHhVFVcMUlYPbn8mDUGI906R6L7K7");
		this.s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(regionName))
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

	}

	/**
	 * This is basic utility method to download a file from s3 bucket using s3
	 * bucket uri.
	 * 
	 * @param uri
	 * @return {@link DownloadedFile}
	 * @throws AmazonServiceException
	 * @throws SdkClientException
	 * @throws IOException
	 */
	public DownloadedFile downloadFileFromS3Url(String uri)
			throws AmazonServiceException, SdkClientException, IOException {
		AmazonS3URI s3URI = new AmazonS3URI(uri);
		return downloadFile(s3URI.getBucket(), s3URI.getKey());
	}

	/**
	 * This is basic utility method to download a file from s3 bucket
	 * 
	 * @param bucketName : s3 bucket name
	 * @param keyName    : file key to download
	 * @return {@link DownloadedFile}
	 * @throws AmazonServiceException
	 * @throws SdkClientException
	 * @throws IOException
	 */
	public DownloadedFile downloadFile(String bucketName, String keyName)
			throws AmazonServiceException, SdkClientException, IOException {
		if (StringUtils.isBlank(bucketName))
			bucketName = this.bucketName;
		DownloadedFile file = new DownloadedFile();
		S3Object obj = s3client.getObject(bucketName, keyName);
		file.setFileName(getFileName(obj.getKey()));
		file.setContent(IOUtils.toByteArray(obj.getObjectContent()));
		return file;
	}

	/**
	 * To get file name from s3 obj key.
	 * 
	 * @param key
	 * @return
	 */
	private String getFileName(String key) {
		try {
			if (key != null)
				return key.split("-")[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This is basic utility method to upload a file to s3 bucket.
	 * 
	 * @param multipartFile
	 * @return
	 */
	public String uploadFile(MultipartFile multipartFile) {

		String fileUrl = "";
		try {
			File file = convertMultiPartToFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
			uploadFileTos3bucket(fileName, file);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	/**
	 * This is basic utility method to delete a file from s3 bucket.
	 * 
	 * @param fileUrl
	 * @return
	 */
	public String deleteFileFromS3Bucket(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
		return "Successfully deleted";
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}