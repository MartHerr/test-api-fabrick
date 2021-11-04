package it.test.spring.testfabrick.controller;

import it.test.spring.testfabrick.model.Balance;
import it.test.spring.testfabrick.model.Transiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class ControllerApi {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/balance")
    public Object getBalance(@RequestParam("accountId") String accountId) throws MalformedURLException {

        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        url = url.replace("{accountId}", accountId);

        HttpHeaders headers = new HttpHeaders();
        //headers.add("Auth-Schema","S2S");
        //headers.add("Api-Key" ,"FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        headers.set("Auth-Schema","S2S");
        headers.set("Api-Key" ,"FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");

        HttpEntity<String> entity = new HttpEntity<String>("",headers);

        ResponseEntity<Balance> response = restTemplate.exchange("url", HttpMethod.GET ,entity , Balance.class );
        return response.getBody().getPayload().toString();
    }

    @GetMapping("/Transiction")
    public Object getTransiction(@RequestParam("accountId") String accountId, @RequestParam("fromAccountingDate") String fromAccountingDate, @RequestParam("toAccountingDate") String toAccountingDate) {

        List<Object> WireTransfer = new ArrayList<>();

        String url ="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts?accountId={accountId}&fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
        url = url.replace("{accountId}", accountId);
        url = url.replace("{fromAccountingDate}", fromAccountingDate);
        url = url.replace("{toAccountingDate}", toAccountingDate);

        HttpHeaders headers = new HttpHeaders();        //--->codice ripetuto, sarebbe meglio usare una funzione
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<Transiction> response = restTemplate.exchange(url, HttpMethod.GET, entity, Transiction.class);

        return response.getBody().getPayload();

    }

    /*
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
    }*/
}
