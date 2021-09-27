package com.emersonrt.desafio.zemerson.services.partner;

import com.emersonrt.desafio.zemerson.utils.RegexUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;

import static java.math.BigInteger.valueOf;

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
        validateData(dto);
        return dao.create(dto).longValue();
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
    
    private void validateLongLat(String longitude, String latitude) throws IllegalArgumentException {
        if (!RegexUtils.isValidLongitude(longitude) || !RegexUtils.isValidLatitude(latitude)) {
            throw new IllegalArgumentException("Invalid Coordinate!");
        }
    }
    
    private void validateData(PartnerDto dto) throws IllegalArgumentException {
        if (StringUtils.isEmpty(dto.getTradingName())) {
            throw new IllegalArgumentException("Trading Name is obligatory!");
        }
        if (StringUtils.isEmpty(dto.getOwnerName())) {
            throw new IllegalArgumentException("Owner is obligatory!");
        }
        if (StringUtils.isEmpty(dto.getDocument())) {
            throw new IllegalArgumentException("Document is obligatory!");
        }
        if (StringUtils.isEmpty(dto.getCoverageArea())) {
            throw new IllegalArgumentException("Coverage Area is obligatory!");
        }
        if (StringUtils.isEmpty(dto.getAddress())) {
            throw new IllegalArgumentException("Address is obligatory!");
        }
        if (dao.isNotUnique("partner", "partner_document", dto.getDocument())) {
            throw new IllegalArgumentException("Non unique Document!");
        }
    }

}
