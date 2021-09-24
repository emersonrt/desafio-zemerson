package com.emersonrt.desafio.zemerson.services.partner;

import javax.ejb.Local;

/**
 *
 * @author emerson
 */

@Local
public interface PartnerLocalService {

    Long create(PartnerDto dto) throws Exception;
    
    PartnerDto get(Long id) throws Exception;
    
    PartnerDto getClosestPartner(String longitude, String latitude) throws Exception;
    
}
