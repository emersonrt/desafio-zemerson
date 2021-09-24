package com.emersonrt.desafio.zemerson.services.partner;

import com.emersonrt.desafio.zemerson.utils.FormatUtils;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.inject.Inject;

import static java.math.BigInteger.*;

/**
 *
 * @author emerson
 */

@Stateless
public class PartnerEjbService implements PartnerLocalService {

    @Inject
    private PartnerDao dao;
        
    @Override
    public Long create(PartnerDto dto) throws Exception {
        
        try {
            
            validateData(dto);
            return dao.create(dto).longValue();
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public PartnerDto get(Long id) throws Exception {

        Object[] obj = dao.find(valueOf(id));

        if (obj == null) {
            throw new Exception("No partner found.");
        }

        return PartnerConversorFactory
                .createDtoConversor()
                .apply(obj);
    }
    
    @Override
    public PartnerDto getClosestPartner(String longitude, String latitude) throws Exception{
        
        validateLongLat(longitude, latitude);
        Object[] obj = dao.findClosestPartner(longitude, latitude);
        
        if (obj == null) {
            throw new Exception("No partner found for your area.");
        }
        
        return PartnerConversorFactory
                .createDtoConversor()
                .apply(obj);
    }
    
    private void validateLongLat(String longitude, String latitude) throws Exception {        
        if (!FormatUtils.isValidLongitude(longitude) || !FormatUtils.isValidLatitude(latitude)) {
            throw new Exception("Invalid Coordinate!");
        }
    }
    
    private void validateData(PartnerDto dto) throws Exception {
        
        if (dto.getTradingName().isBlank()) {
            throw new Exception("Trading Name is obligatory!");
        }
        
        if (dto.getOwnerName().isBlank()) {
            throw new Exception("Owner is obligatory!");
        }
        
        if (dto.getDocument().isBlank()) {
            throw new Exception("Document is obligatory!");
        }
        
        if (dto.getCoverageArea().isBlank()) {
            throw new Exception("Coverage Area is obligatory!");
        }
        
        if (dto.getAddress().isBlank()) {
            throw new Exception("Address is obligatory!");
        }
        
    }

}
