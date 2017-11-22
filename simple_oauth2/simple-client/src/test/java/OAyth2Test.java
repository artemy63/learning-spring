import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class OAyth2Test {

    private final String authServerUrl = "http://localhost:8080/oauth/token";
    private final String clientId = "client#1";
    private final String clientSecret = "client-secret";
    final String resourceServerUrl = "http://localhost:9000/";
    private final RestTemplate template = new RestTemplate();

    @Test
    public void withoutToken() throws Exception {
        ResponseEntity<String> result = template.exchange(resourceServerUrl, HttpMethod.GET, null, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("Hello", result.getBody());

        try {
            template.exchange(resourceServerUrl + "/secured", HttpMethod.GET, null, String.class);
            Assert.fail();
        } catch (HttpStatusCodeException e) {
            Assert.assertTrue(e.getResponseBodyAsString().contains("Full authentication is required to access this resource"));
        }

        try {
            template.exchange(resourceServerUrl + "/secured2", HttpMethod.GET, null, String.class);
            Assert.fail();
        } catch (HttpStatusCodeException e) {
            Assert.assertTrue(e.getResponseBodyAsString().contains("Full authentication is required to access this resource"));
        }
    }

    @Test
    public void withUserToken() throws Exception {
        String token = getToken("user", "pass");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> result = template.exchange(resourceServerUrl, HttpMethod.GET, entity, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("Hello", result.getBody());

        try {
            template.exchange(resourceServerUrl + "/secured", HttpMethod.GET, entity, String.class);
            Assert.fail();
        } catch (HttpStatusCodeException e) {
            System.out.println(e.getResponseBodyAsString());
            Assert.assertTrue(e.getResponseBodyAsString().contains("Access is denied"));
        }

        result = template.exchange(resourceServerUrl + "/secured2", HttpMethod.GET, entity, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("Secured2", result.getBody());
    }

    @Test
    public void withAdminToken() throws Exception {
        String token = getToken("admin", "pass");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> result = template.exchange(resourceServerUrl, HttpMethod.GET, entity, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("Hello", result.getBody());

        result = template.exchange(resourceServerUrl + "/secured", HttpMethod.GET, entity, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("Secured", result.getBody());

        try {
            template.exchange(resourceServerUrl + "/secured2", HttpMethod.GET, entity, String.class);
            Assert.fail();
        } catch (HttpStatusCodeException e) {
            System.out.println(e.getResponseBodyAsString());
            Assert.assertTrue(e.getResponseBodyAsString().contains("Access is denied"));
        }
    }

    private String getToken(String username, String password) throws IOException {


        String plainCreds = String.format("%s:%s", clientId, clientSecret);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> exchange = template.exchange(authServerUrl, HttpMethod.POST, entity, String.class);

        Map<String, String> resultMap = new ObjectMapper().readValue(exchange.getBody(), new TypeReference<Map<String, String>>() {
        });
        return resultMap.get("access_token");
    }
}
