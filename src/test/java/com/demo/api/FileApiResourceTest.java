package com.demo.api;

import com.demo.APIDemoApplication;
import com.demo.APIDemoConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileApiResourceTest {
  @ClassRule
  public static final DropwizardAppRule<APIDemoConfiguration> RULE = new DropwizardAppRule<>(
    APIDemoApplication.class, ResourceHelpers.resourceFilePath("config.yml"));
  final static String TEST = "test";

  // This test is passing
  @Test
  public void jersey2ClientShouldNotQuoteBody() throws com.demo.clients.jersey2.ApiException {
    com.demo.clients.jersey2.api.DefaultApi jerseyApi = new com.demo.clients.jersey2.api.DefaultApi();

    com.demo.clients.jersey2.model.FileUploadResult result = jerseyApi.uploadFile(TEST);

    assertEquals(TEST, result.getResult());
  }

  // This test is failing - Call succeeds, but result is `"test"` instead of `test`
  @Test
  public void nativeClientShouldNotQuoteBody() throws com.demo.clients.nativejava.ApiException {
    com.demo.clients.nativejava.api.DefaultApi nativeApi = new com.demo.clients.nativejava.api.DefaultApi();

    com.demo.clients.nativejava.model.FileUploadResult result = nativeApi.uploadFile(TEST);

    assertEquals(TEST, result.getResult());
  }

  // This test is passing
  @Test
  public void jersey2ClientShouldSetContentType() throws com.demo.clients.jersey2.ApiException {
    com.demo.clients.jersey2.api.DefaultApi jerseyApi = new com.demo.clients.jersey2.api.DefaultApi();

    com.demo.clients.jersey2.model.FileUploadResult result = jerseyApi.uploadFileWithoutConsumes(TEST);
  }

  // This test is failing - Content-Type is set to 'application/json', causing status 415
  @Test
  public void nativeClientShouldSetContentType() throws com.demo.clients.nativejava.ApiException {
    com.demo.clients.nativejava.api.DefaultApi nativeApi = new com.demo.clients.nativejava.api.DefaultApi();

    com.demo.clients.nativejava.model.FileUploadResult result = nativeApi.uploadFileWithoutConsumes(TEST);
  }
}
