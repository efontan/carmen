package com.despegar.hackaton.carmen.domain.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.hackaton.carmen.domain.exception.InternalServerErrorException;
import com.despegar.library.rest.HttpMethod;
import com.despegar.library.rest.HttpResponse;
import com.despegar.library.rest.HttpStatus;
import com.despegar.library.rest.RestConnector;
import com.despegar.library.rest.builder.HttpRequestBuilder;
import com.despegar.library.rest.utils.TypeReference;
import com.google.common.collect.Maps;

public abstract class AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestClient.class);

    private static final String MIME_TYPE = "application/json; charset=utf-8";

    public abstract RestConnector getRestConnector();

    protected <T> T doGet(RestConnector restConnector, String uri, TypeReference<T> type, Object... arguments) {
        Map<String, Object> params = Maps.newHashMap();
        String url = this.buildUrl(uri, params, arguments);
        HttpRequestBuilder httpRequestBuilder = restConnector.get(url).accept(MIME_TYPE);
        return this.executeAndvalidateResponse(type, null, url, httpRequestBuilder);
    }

    protected <T> T doGet(String uri, TypeReference<T> type, Object... arguments) {
        Map<String, Object> params = Maps.newHashMap();
        return this.doGetWithParams(uri, type, params, arguments);
    }

    protected <T> T doGetWithParams(String uri, TypeReference<T> type, Map<String, Object> params, Object... arguments) {
        String url = this.buildUrl(uri, params, arguments);
        HttpRequestBuilder httpRequestBuilder = this.getRestConnector().get(url).accept(MIME_TYPE);
        return this.executeAndvalidateResponse(type, null, url, httpRequestBuilder);
    }

    protected <T> T doGet(String uri, Class<T> clazz, Object... arguments) {
        Map<String, Object> params = Maps.newHashMap();
        String url = this.buildUrl(uri, params, arguments);
        HttpRequestBuilder httpRequestBuilder = this.getRestConnector().get(url).accept(MIME_TYPE);
        return this.executeAndvalidateResponse(null, clazz, url, httpRequestBuilder);
    }


    protected <T> T doPostWithParams(String uri, Object body, TypeReference<T> type, Map<String, Object> params,
        Object... arguments) {
        return this.doCall(this.getRestConnector(), uri, body, type, HttpMethod.POST, params, arguments);
    }

    protected <T> T doPost(String uri, Object body, TypeReference<T> type, Object... arguments) {
        Map<String, Object> params = Maps.newHashMap();
        return this.doCall(this.getRestConnector(), uri, body, type, HttpMethod.POST, params, arguments);
    }

    protected <T> T doCall(RestConnector restConnector, String uri, Object body, TypeReference<T> type, HttpMethod method,
        Map<String, Object> params, Object... arguments) {
        String url = this.buildUrl(uri, params, arguments);
        HttpRequestBuilder httpRequestBuilder = null;
        if (method.equals(HttpMethod.POST)) {
            httpRequestBuilder = restConnector.post(url).withBody(body).accept(MIME_TYPE).asContentType(MIME_TYPE);
        } else {
            httpRequestBuilder = restConnector.put(url).withBody(body).accept(MIME_TYPE).asContentType(MIME_TYPE);
        }
        return this.executeAndvalidateResponse(type, null, url, httpRequestBuilder);
    }

    protected <T> T doPut(String uri, Object body, TypeReference<T> type, Object... arguments) {
        Map<String, Object> params = Maps.newHashMap();
        return this.doCall(this.getRestConnector(), uri, body, type, HttpMethod.PUT, params, arguments);
    }

    private <T> T executeAndvalidateResponse(TypeReference<T> type, Class<T> clazz, String url,
        HttpRequestBuilder httpRequestBuilder) {
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpRequestBuilder.execute();
        } catch (IOException e) {
            LOGGER.error("ERROR  in execute service : {}  ", url, e);
            throw new RuntimeException("ERROR  in execute service : " + url, e);
        }

        if (httpResponse.getStatus() == HttpStatus.NOT_FOUND) {
            return null;
        }

        if (httpResponse.getStatus() == HttpStatus.OK) {
            try {
                if (type != null) {
                    return httpResponse.getBodyAs(type);
                } else {
                    return httpResponse.getBodyAs(clazz);
                }

            } catch (Exception e) {
                LOGGER.error("ERROR serialize object in call service : {}  ", url, e);
                throw new RuntimeException("ERROR serialize object call service : " + url, e);
            }
        }

        String errorBody = httpResponse.getBody();
        LOGGER.error("ERROR call service : {}  - Http status code: {}  - body : {} ", url, httpResponse.getStatus(),
            errorBody);
        throw new InternalServerErrorException("ERROR call service  : " + url + "- Http status code: "
            + httpResponse.getStatus() + " -  body : " + errorBody);
    }

    protected String buildUrl(String contextPath, Map<String, Object> params, Object... urlVariables) {
        String urlTemplate;
        StringBuilder builder = new StringBuilder();
        builder.append(contextPath);
        if (urlVariables.length > 0) {
            for (Object valiable : urlVariables) {
                builder.append("/");
                builder.append(valiable.toString());
            }

        }
        Iterator<String> paramNames = params.keySet().iterator();
        if (paramNames.hasNext()) {
            builder.append("?");
            while (paramNames.hasNext()) {
                String key = paramNames.next();
                Object value = params.get(key);
                builder.append(key);
                builder.append("=");
                builder.append(value);
                if (paramNames.hasNext()) {
                    builder.append("&");
                }
            }

        }
        urlTemplate = builder.toString();
        return urlTemplate;
    }
}
