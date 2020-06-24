package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Strain;

import java.util.List;

public interface StrainService {

    List<Strain> findAll();

    Strain save(Strain strain);

    void delete(long id);

    Strain findByStrainById(long id);
}
