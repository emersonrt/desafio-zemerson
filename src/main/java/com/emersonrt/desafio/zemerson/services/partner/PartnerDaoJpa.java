package com.emersonrt.desafio.zemerson.services.partner;

import com.emersonrt.desafio.zemerson.utils.cruddao.CrudDaoJpa;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */

@Stateless
public class PartnerDaoJpa extends CrudDaoJpa<Partner> implements PartnerDao {

    public PartnerDaoJpa() {
        super(Partner.class);
    }

    @Override
    public BigInteger create(PartnerDto dto) {

        String sql = "INSERT INTO partner (trading_name, owner_name, partner_document, coverage_area, address) \n"
                        + "VALUES (:tradingName, :ownerName, :document, st_geomfromgeojson(:coverageArea\\:\\:JSON), st_geomfromgeojson(:address\\:\\:JSON)) \n"
                        + "RETURNING id_partner";

        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("tradingName", dto.getTradingName());
        q.setParameter("ownerName", dto.getOwnerName());
        q.setParameter("document", dto.getDocument());
        q.setParameter("coverageArea", dto.getCoverageArea());
        q.setParameter("address", dto.getAddress());

        return (BigInteger) q.getSingleResult();
    }
    
    @Override
    public Object[] find(BigInteger idPartner) {
        
        try {

            String sql = "SELECT id_partner, trading_name, owner_name, partner_document, \n"
                            + "         st_asgeojson(coverage_area) AS coverage_area, \n"
                            + "         st_asgeojson(address) AS address \n"
                            + "FROM partner p \n"
                            + "WHERE id_partner = :idPartner";

            Query q = getEntityManager().createNativeQuery(sql);
            q.setParameter("idPartner", idPartner);

            return (Object[]) q.getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
        
    }
    
    @Override
    public Object[] findClosestPartner(String longitude, String latitude) {
        
        try {
            String sql = "WITH param_point AS ( \n"
                            + "	SELECT ST_GeomFromText('POINT(' || :longitude || ' ' || :latitude || ')') AS search_coordinates \n"
                            + "), \n"
                            + "covered_area_partners AS ( \n"
                            + "	SELECT p.*, st_distance(p.address, pp.search_coordinates) AS distance \n"
                            + "	FROM partner p, param_point pp \n"
                            + "	WHERE st_covers(p.coverage_area, pp.search_coordinates) \n"
                            + ") \n"
                            + "SELECT id_partner, trading_name, owner_name, partner_document, \n"
                            + "	st_asgeojson(coverage_area) AS coverage_area, \n"
                            + "	st_asgeojson(address) AS address \n"
                            + "FROM covered_area_partners cap \n"
                            + "ORDER BY distance \n"
                            + "LIMIT 1";

            Query q = getEntityManager().createNativeQuery(sql);
            q.setParameter("longitude", longitude);
            q.setParameter("latitude", latitude);            

            return (Object[]) q.getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
        
    }
    
}
