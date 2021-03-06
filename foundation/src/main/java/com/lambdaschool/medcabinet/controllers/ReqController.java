package com.lambdaschool.medcabinet.controllers;

import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.models.StrainEffects;
import com.lambdaschool.medcabinet.models.User;
import com.lambdaschool.medcabinet.services.StrainService;
import com.lambdaschool.medcabinet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/req")
public class ReqController {

    @Autowired
    private StrainService strainService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "new", produces = {"application/json"})
    public ResponseEntity<?> newReq()
    {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String reqUrl = "https://bestmedcab1.herokuapp.com/dummy_data";

        ParameterizedTypeReference<List<Strain>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<Strain>> responseEntity = restTemplate.exchange(reqUrl, HttpMethod.GET, null, responseType);
        List<Strain> ourStrains = responseEntity.getBody();
        List<Strain> withIds = new ArrayList<>();
        if(ourStrains != null) {
            for (Strain strain : ourStrains) {
                if (strainService.findByName(strain.getStrain()).getStrain().equals(strain.getStrain()))
                {
                    Strain newStrain = strainService.findByName(strain.getStrain());
                    withIds.add(newStrain);
                } else {
                    strain = strainService.save(strain);
                    withIds.add(strain);
                }
            }
        }

        return new ResponseEntity<>(withIds, HttpStatus.OK);



    }
    //testing
    @PostMapping(value = "new", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> newCustomReq(@RequestBody StrainEffects strainEffects)
    {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String reqUrl = "https://bestmedcab1.herokuapp.com/recommender";

        ParameterizedTypeReference<List<Strain>> responseType = new ParameterizedTypeReference<>() {
        };

        HttpEntity<StrainEffects> requestEntity = new HttpEntity<>(strainEffects);

        ResponseEntity<List<Strain>> responseEntity = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, responseType);
        List<Strain> ourStrains = responseEntity.getBody();
        List<Strain> withIds = new ArrayList<>();
        if(ourStrains != null) {
            for (Strain strain : ourStrains) {
                if (strainService.findByName(strain.getStrain()).getStrain().equals(strain.getStrain()))
                {
                    Strain newStrain = strainService.findByName(strain.getStrain());
                    withIds.add(newStrain);
                } else {
                    strain = strainService.save(strain);
                    withIds.add(strain);
                }
            }
        }

        return new ResponseEntity<>(withIds, HttpStatus.OK);
    }
}
