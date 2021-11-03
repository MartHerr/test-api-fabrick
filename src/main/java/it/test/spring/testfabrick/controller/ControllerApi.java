package it.test.spring.testfabrick.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.MalformedURLException;


@RestController
public class ControllerApi {

    /*@Autowired
    private RestTemplate restTemplate;*/
    
    
    @GetMapping("/account")
    public String getAccount() throws MalformedURLException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema","S2S");
        headers.add("Api-Key" ,"FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate result = new RestTemplate();
        ResponseEntity<String> response = result.exchange("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/balance/", HttpMethod.GET ,entity , String.class );
        return response.getBody();
    }

    @GetMapping("/transiction")
    public String getTransiction() throws MalformedURLException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema","S2S");
        headers.add("Api-Key" ,"FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate result = new RestTemplate();
        ResponseEntity<String> response = result.exchange("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/transactions?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01", HttpMethod.GET ,entity , String.class );
        return response.getBody();
    }
    @PostMapping("/money-transfer")
    public String getMoneyTransfer() throws MalformedURLException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema","S2S");
        headers.add("Api-Key" ,"FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate result = new RestTemplate();
        ResponseEntity<String> response = result.exchange("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/payments/money-transfers", HttpMethod.GET ,entity , String.class );
        //manca la condizione del body e quidni convertire il payload in in byte
        return response.getBody();
    }


    /*@GetMapping("/balance")
    private String getHelloClient(){
        String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/balance/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri + "?Auth-Schema=" + authSchema, String.class);
        return result;
    }
    */
}
