package com.emersonrt.desafio.zemerson.services.partner;

import com.emersonrt.desafio.zemerson.utils.Controller;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

/**
 *
 * @author emerson
 */

@Slf4j
@Path("partner")
@RequestScoped
public class PartnerController extends Controller {
    
    @Inject
    private PartnerLocalService service;

    public PartnerController() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PartnerCommand command) {
        
        PartnerDto dto = PartnerDto.builder()
                .tradingName(command.getTradingName())
                .ownerName(command.getOwnerName())
                .document(command.getDocument())
                .coverageArea(new JSONObject(command.getCoverageArea()).toString())
                .address(new JSONObject(command.getAddress()).toString())
                .build();
        
        try {
            addData(service.create(dto));
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        return build();

    }

    @GET
    @Path("{partner}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("partner") Long idPartner) {
        
        try {
            PartnerDto partner= service.get(idPartner);
            addData(partner);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        return build();

    }
    
    @GET
    @Path("long={longitude}/lat={latitude}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClosestPartner(@PathParam("longitude") String longitude, @PathParam("latitude") String latitude) {
        
        try {
            PartnerDto partner= service.getClosestPartner(longitude, latitude);
            addData(partner);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        return build();

    }

}
