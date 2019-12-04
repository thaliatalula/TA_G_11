package com.apap.tugasakhir.siruangan.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BukuDetailResp {
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private BukuDetail result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BukuDetail getResult() {
        return result;
    }

    public void setResult(BukuDetail result) {
        this.result = result;
    }
}
