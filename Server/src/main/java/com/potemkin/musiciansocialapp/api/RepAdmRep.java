package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.RepAdm;

public interface RepAdmRep extends CrudRepository<RepAdm,Integer>{

    public RepAdm findRepAdmByRepAdmId(Integer repAdmId);

}
