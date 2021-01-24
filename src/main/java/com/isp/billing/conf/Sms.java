package com.isp.billing.conf;

/**
 * Created by Toufiq on 7/27/2019.
 */
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.json.JSONException;
import org.json.JSONObject;


import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Sms {
//    JSONObject object = null;
//    final String userName = "TKGAdmin";
//    final String pass = "TkgSms@98765";
//    //	final String url = "gpcmp.grameenphone.com";
//    final String url = "https://gpcmp.grameenphone.com/gpcmpapi/messageplatform/controller.home?";
//
//    public JSONObject postSms(String phnNo, String msg, String cli)
//            throws JSONException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
//            // @override
//            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//                return true;
//            }
//        };
//
//        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
//
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient);
//
//        RestTemplate rt = new RestTemplate(requestFactory);
//        // String
//        // nUrl=userName+"&"+pass+"&"+1+"&"+"01730784737"+"&"+"880"+"&"+"SFLL"+1+"&"+"Test
//        // Sms"+"&"+0;
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//        map.add("username", userName);
//        map.add("password", pass);
//        map.add("apicode", "1");
//        map.add("msisdn", phnNo);
//        map.add("countrycode", "880");
//        map.add("cli", cli);
//        map.add("messagetype", "1");
//        map.add("message", msg);
//        map.add("messageid", "0");
//        try {
//
//            String out = rt.postForObject(url, map, String.class);
//
//            System.out.println(out);
//
//            String responseString = "{" + out + "}";
//
//            this.object = new JSONObject(responseString.replace(",", ":"));
//
//
//
//            // List<NameValuePair> arguments = new ArrayList(9);
//            // arguments.add(new BasicNameValuePair("username", "TKGAdmin"));
//            // arguments.add(new BasicNameValuePair("password", "TkgSms@98765"));
//            // arguments.add(new BasicNameValuePair("apicode", "1"));
//            // arguments.add(new BasicNameValuePair("msisdn", phnNo));
//            // arguments.add(new BasicNameValuePair("countrycode", "880"));
//            // arguments.add(new BasicNameValuePair("cli", cli));
//            // arguments.add(new BasicNameValuePair("messagetype", "1"));
//            // arguments.add(new BasicNameValuePair("message", msg));
//            // arguments.add(new BasicNameValuePair("messageid", "0"));
//
//            return this.object;
//
//        } catch (HttpClientErrorException e) {
//            // TODO: handle exception
//            return this.object =new JSONObject("{400:null}");
//        }
//
//    }

}

