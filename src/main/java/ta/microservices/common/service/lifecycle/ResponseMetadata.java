package ta.microservices.common.service.lifecycle;

import javax.ws.rs.core.Response.Status;

public class ResponseMetadata {

    private final Status responseStatus;

    public ResponseMetadata(Status responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Status getResponseStatus() {
        return responseStatus;
    }

}
