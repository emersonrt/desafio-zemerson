package com.emersonrt.desafio.zemerson.services.partner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartnerEjbServiceTest {

    private PartnerLocalService service;

    @BeforeEach
    void setUp() throws Exception {
        service = new PartnerEjbService();
    }

    PartnerDto.PartnerDtoBuilder perfectObject() {
        return PartnerDto.builder()
                .tradingName("Adega da Cerveja - Pinheiros")
                .ownerName("Zé da Silva")
                .document("1432132123891/0001")
                .coverageArea("{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-38.56586,-3.85041],[-38.49599,-3.87361],[-38.45033,-3.90358]," +
                        "[-38.42304,-3.90273],[-38.37892,-3.88971],[-38.35566,-3.8844],[-38.39557,-3.82497],[-38.41531,-3.80133],[-38.42771,-3.76754]," +
                        "[-38.44251,-3.75054],[-38.45672,-3.75024],[-38.46562,-3.74746],[-38.46525,-3.74657],[-38.46616,-3.74458],[-38.46507,-3.74083]," +
                        "[-38.47256,-3.73743],[-38.47844,-3.72759],[-38.49002,-3.72476],[-38.49573,-3.72254],[-38.51226,-3.71384],[-38.51736,-3.74292]," +
                        "[-38.52517,-3.7681],[-38.53095,-3.78294],[-38.53415,-3.79124],[-38.5412,-3.79573],[-38.55148,-3.80326],[-38.55796,-3.82]," +
                        "[-38.5656,-3.84839],[-38.56586,-3.85041]]]]}")
                .address("{\"type\":\"Point\",\"coordinates\":[-38.495586,-3.809936]}");
    }

    @Test
    void createNoTradingName() {
        PartnerDto dto = perfectObject()
                .tradingName(null)
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.create(dto));
    }

    @Test
    void createNoOwnername() {
        PartnerDto dto = perfectObject()
                .ownerName(null)
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.create(dto));
    }

    @Test
    void createNoDocument() {
        PartnerDto dto = perfectObject()
                .document(null)
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.create(dto));
    }

    @Test
    void createNoCoverageArea() {
        PartnerDto dto = perfectObject()
                .coverageArea(null)
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.create(dto));
    }

    @Test
    void createNoDocumentAddress() {
        PartnerDto dto = perfectObject()
                .address(null)
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.create(dto));
    }

    @Test
    void getClosestPartnerInvalidCoordinate() {
        String longitude = "181.785741";
        String latitude = "-95.785741";
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.getClosestPartner(longitude, latitude));
    }

}