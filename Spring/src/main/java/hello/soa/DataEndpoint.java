package hello.soa;

import io.spring.guides.gs_producing_web_service.GetDataRequest;
import io.spring.guides.gs_producing_web_service.GetDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * https://spring.io/guides/gs/producing-web-service/
 */

@Endpoint
public class DataEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private DataRepository countryRepository;

    @Autowired
    public DataEndpoint(DataRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDataRequest")
    @ResponsePayload
    public GetDataResponse getData(@RequestPayload GetDataRequest request) {
        GetDataResponse response = new GetDataResponse();
        response.setData(countryRepository.findData(request.getTitel()));

        return response;
    }
}
