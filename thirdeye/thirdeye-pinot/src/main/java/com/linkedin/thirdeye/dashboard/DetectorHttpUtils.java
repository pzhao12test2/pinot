package com.linkedin.thirdeye.dashboard;

import com.linkedin.thirdeye.anomaly.utils.AbstractResourceHttpUtils;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Utility classes for calling detector endpoints to execute/schedule jobs
 */
public class DetectorHttpUtils extends AbstractResourceHttpUtils {

  private static String ANOMALY_JOBS_ENDPOINT = "/api/anomaly-jobs/";
  private static String EMAIL_JOBS_ENDPOINT = "/api/email-jobs/";
  private static String ADHOC = "/ad-hoc";

  public DetectorHttpUtils(String detectorHost, int detectorPort) {
    super(new HttpHost(detectorHost, detectorPort));
  }

  public String enableAnomalyFunction(String id) throws ClientProtocolException, IOException {
    HttpPost req = new HttpPost(ANOMALY_JOBS_ENDPOINT + id);
    return callJobEndpoint(req);
  }

  public String disableAnomalyFunction(String id) throws ClientProtocolException, IOException {
    HttpDelete req = new HttpDelete(ANOMALY_JOBS_ENDPOINT + id);
    return callJobEndpoint(req);
  }

  public String runAdhocAnomalyFunction(String id, String start, String end)
      throws ClientProtocolException, IOException {
    HttpPost req = new HttpPost(ANOMALY_JOBS_ENDPOINT + id + ADHOC + "?start=" + start + "&end=" + end);
    return callJobEndpoint(req);
  }

  public String enableEmailConfiguration(String id) throws ClientProtocolException, IOException {
    HttpPost req = new HttpPost(EMAIL_JOBS_ENDPOINT + id);
    return callJobEndpoint(req);
  }

  public String disableEmailConfiguration(String id) throws ClientProtocolException, IOException {
    HttpDelete req = new HttpDelete(EMAIL_JOBS_ENDPOINT + id);
    return callJobEndpoint(req);
  }

  public String runAdhocEmailConfiguration(String id)
      throws ClientProtocolException, IOException {
    HttpPost req = new HttpPost(EMAIL_JOBS_ENDPOINT + id + ADHOC);
    return callJobEndpoint(req);
  }
}
