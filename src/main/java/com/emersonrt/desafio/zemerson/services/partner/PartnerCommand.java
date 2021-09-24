package com.emersonrt.desafio.zemerson.services.partner;

import java.util.Map;
import lombok.Data;

/**
 *
 * @author emerson
 */

@Data
public class PartnerCommand {

    private Long id;
    private String tradingName;
    private String ownerName;
    private String document;
    private Map<String, Object> coverageArea;
    private Map<String, Object> address;
    
}
