package jdotest.dto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import jdotest.dto.enums.HttpRequestSourceType;
import jdotest.dto.enums.HttpRequestType;

public class AuditHttpRequestsMapBase {

    private final long ServiceInstanceId;
    private final String url;
    private final URI uri;
    private final String body;
    private final HttpRequestType requestType;
    private final HttpRequestSourceType requestSourceType;

    private AuditHttpRequestsMapBase(long ServiceInstanceId, URI uri, String url, String body, HttpRequestType requestType, HttpRequestSourceType requestSourceType) {
        this.ServiceInstanceId = ServiceInstanceId;
        this.uri = uri;
        this.url = url;
        this.body = body;
        this.requestType = requestType;
        this.requestSourceType = requestSourceType;
    }

    public AuditHttpRequestsMapBase(long ServiceInstanceId, URI uri, String body, HttpRequestType requestType, HttpRequestSourceType requestSourceType) {
        this(ServiceInstanceId, uri, uri == null ? null : uri.toString(), body, requestType, requestSourceType);
    }

    public AuditHttpRequestsMapBase(long ServiceInstanceId, String url, String body, HttpRequestType requestType, HttpRequestSourceType requestSourceType) {
        this(ServiceInstanceId, toURI(url), url, body, requestType, requestSourceType);
    }

    public long getServiceInstanceId() {
        return ServiceInstanceId;
    }

    public URI getURI() {
        return uri;
    }

    public String getURL() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public HttpRequestType getRequestType() {
        return requestType;
    }

    public HttpRequestSourceType getRequestSourceType() {
        return requestSourceType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.ServiceInstanceId ^ (this.ServiceInstanceId >>> 32));
        hash = 97 * hash + Objects.hashCode(this.url);
        hash = 97 * hash + Objects.hashCode(this.body);
        hash = 97 * hash + Objects.hashCode(this.requestType);
        hash = 97 * hash + Objects.hashCode(this.requestSourceType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuditHttpRequestsMapBase other = (AuditHttpRequestsMapBase) obj;
        if (this.ServiceInstanceId != other.ServiceInstanceId) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (this.requestType != other.requestType) {
            return false;
        }
        return this.requestSourceType == other.requestSourceType;
    }

    private static URI toURI(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
        }
        return uri;
    }
}
