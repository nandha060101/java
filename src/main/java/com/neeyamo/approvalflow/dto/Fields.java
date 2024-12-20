
package com.neeyamo.approvalflow.dto;

import org.springframework.stereotype.Component;

@Component
public class Fields {

    private String typeCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

}
