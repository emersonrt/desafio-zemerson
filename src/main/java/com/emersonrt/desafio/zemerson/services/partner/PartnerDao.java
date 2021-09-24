package com.emersonrt.desafio.zemerson.services.partner;

import com.emersonrt.desafio.zemerson.utils.cruddao.CrudDao;
import java.math.BigInteger;

/**
 *
 * @author emerson
 */

public interface PartnerDao extends CrudDao<Partner> {

    BigInteger create(PartnerDto dto);
    
    Object[] find(BigInteger idPartner);
    
    Object[] findClosestPartner(String longitude, String latitude);
    
}
