package com.demo.api;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

public class FileApiResource implements FileApi {
  @Context
  HttpServletResponse response;

  /**
   * This is used by {@link FileApiResourceTest} to demonstrate that the native client is surrounding the body with double quotes.
   * @param body to echo in result field
   * @return a result which echoes the body
   */
  // FIXME: native client uses 'Content-Type: application/json', don't 415
  @Consumes({ MediaType.APPLICATION_JSON, "text/csv" })
  @POST
  @Path("/withConsumes")
  @Override
  public FileUploadResult uploadFile(String body) {
    System.out.println(body);

    response.setStatus(201);

    return new FileUploadResult().result(body);
  }

  /**
   * This is used by {@link FileApiResourceTest} to demonstrate that the native client is sending the wrong Content-Type header,
   * making the @Consumes annotation on the uploadFile API necessary.
   * @param body to echo in result field
   * @return a result which echoes the body
   */
  @Override
  public FileUploadResult uploadFileWithoutConsumes(String body) {
    return uploadFile(body);
  }

  /**
   * Demonstrates the request/response the native client will produce
   * @return the data the uploadFile API receives
   * @throws com.demo.clients.nativejava.ApiException
   */
  @Produces(MediaType.APPLICATION_JSON)
  @GET
  @Path("/native")
  public com.demo.clients.nativejava.model.FileUploadResult testNativeClient() throws com.demo.clients.nativejava.ApiException {
    com.demo.clients.nativejava.api.DefaultApi nativeApi = new com.demo.clients.nativejava.api.DefaultApi();

    return nativeApi.uploadFile("test");
  }

  /**
   * Demonstrates the request/response the jersey2 client will produce
   * @return the data the uploadFile API receives
   * @throws com.demo.clients.jersey2.ApiException
   */
  @Produces(MediaType.APPLICATION_JSON)
  @GET
  @Path("/jersey2")
  public com.demo.clients.jersey2.model.FileUploadResult testJerseyClient() throws com.demo.clients.jersey2.ApiException {
    com.demo.clients.jersey2.api.DefaultApi jerseyApi = new com.demo.clients.jersey2.api.DefaultApi();

    return jerseyApi.uploadFile("test");
  }
}
