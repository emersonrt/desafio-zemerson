package com.emersonrt.desafio.zemerson.services.partner;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author emerson
 */

@Builder
@Data
public class PartnerDto {

    private final Long id;
    private final String tradingName;
    private final String ownerName;
    private final String document;
    private final String coverageArea;
    private final String address;

}
