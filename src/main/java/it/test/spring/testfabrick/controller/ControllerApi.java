package it.test.spring.testfabrick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ControllerApi {

    @Autowired
    private RestTemplate restTemplate;

    private static String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts";

    @Value("${api.key}")
    private String apiKey;

    @Value("${Auth-Schema}")
    private String authSchema;

    @GetMapping("/allAccounts")
    public List<Object> getAccounts(){
        Object[] accounts = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(accounts);

    }
    @GetMapping("/balance")
    private String getHelloClient(){
        String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/balance";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri + "?Auth-Schema=" + authSchema, String.class);
        return result;
    }
}
