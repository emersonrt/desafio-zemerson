package com.emersonrt.desafio.zemerson.services.partner;

import java.math.BigInteger;
import java.util.function.Function;

/**
 *
 * @author emerson
 */

public class PartnerConversorFactory {

    public static Function<Object[], PartnerDto> createDtoConversor() {
        return (source) -> PartnerDto.builder()
                .id( ((BigInteger) source[0]).longValue() )
                .tradingName((String) source[1])
                .ownerName((String) source[2])
                .document((String) source[3])
                .coverageArea((String) source[4])
                .address((String) source[5])
                .build();
                
    }

}
