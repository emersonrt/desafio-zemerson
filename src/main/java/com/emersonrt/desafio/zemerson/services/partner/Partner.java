
package com.emersonrt.desafio.zemerson.services.partner;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author emerson
 * 
 * NOTA: o hibernate foi pouco usado nesta entidade por falta de mapeamento do PostGis, extensão do postgres usada para trabalhar com GeoJson
 */

@Entity
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partner")
    private Long idPartner;
    @NotNull
    @Column(name = "trading_name")
    private String tradingName;
    @NotNull
    @Column(name = "owner_name")
    private String ownerName;
    @NotNull
    @Column(name = "partner_document")
    private String document;

    public Long getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(Long idPartner) {
        this.idPartner = idPartner;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idPartner);
        hash = 83 * hash + Objects.hashCode(this.tradingName);
        hash = 83 * hash + Objects.hashCode(this.ownerName);
        hash = 83 * hash + Objects.hashCode(this.document);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Partner other = (Partner) obj;
        if (!Objects.equals(this.tradingName, other.tradingName)) {
            return false;
        }
        if (!Objects.equals(this.ownerName, other.ownerName)) {
            return false;
        }
        if (!Objects.equals(this.document, other.document)) {
            return false;
        }
        if (!Objects.equals(this.idPartner, other.idPartner)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Partner{" + "id=" + idPartner + ", tradingName=" + tradingName + ", ownerName=" + ownerName + ", document=" + document + '}';
    }

}
