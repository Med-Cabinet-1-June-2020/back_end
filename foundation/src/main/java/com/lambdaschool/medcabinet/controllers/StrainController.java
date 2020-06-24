package com.lambdaschool.medcabinet.controllers;

import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.services.StrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/strains")
public class StrainController {

    @Autowired
    private StrainService strainService;

    @GetMapping(value = "/all",
    produces = {"application/json"})
    public ResponseEntity<?> listAllStrains()
    {
        List<Strain> strains = strainService.findAll();
        return new ResponseEntity<>(strains, HttpStatus.OK);
    }

    @PostMapping(value = "strain",
    consumes = {"application/json"})
    public ResponseEntity<?> addStrain(@Valid @RequestBody Strain newstrain) throws URISyntaxException
    {
        newstrain.setStrainid(0);
        newstrain = strainService.save(newstrain);

        return new ResponseEntity<>(newstrain, HttpStatus.CREATED);
    }
}
