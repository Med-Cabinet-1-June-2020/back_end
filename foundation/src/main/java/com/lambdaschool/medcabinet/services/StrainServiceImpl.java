package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.exceptions.ResourceNotFoundException;
import com.lambdaschool.medcabinet.handlers.HelperFunctions;
import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.repository.StrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "strainService")
public class StrainServiceImpl implements StrainService {

    @Autowired
    private StrainRepository strainRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helper;

    @Override
    public Strain save(Strain strain) {

            Strain newStrain = new Strain();

            newStrain.setStrain(strain.getStrain());
            newStrain.setRace(strain.getRace());
            newStrain.setFlavors(strain.getFlavors());
            newStrain.setPositive(strain.getPositive());
            newStrain.setNegative(strain.getNegative());
            newStrain.setMedical(strain.getMedical());
            newStrain.setType(strain.getType());
            newStrain.setRating(strain.getRating());
            newStrain.setDescription(strain.getDescription());
            return strainRepository.save(newStrain);

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Strain> findAll() {
        List<Strain> strains = new ArrayList<>();

        strainRepository.findAll().iterator().forEachRemaining(strains::add);
        return strains;
    }

    @Override
    public Strain findByStrainById(long id) {
        return strainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Strain " + id + " Not Found"));
    }
}
