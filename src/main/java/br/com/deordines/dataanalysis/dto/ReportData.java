package br.com.deordines.dataanalysis.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReportData {

    private Integer clientsAmount;
    private Integer salesmansAmount;
    private Long idSaleMoreExpensive;
    private String worstSalesman;

    public ReportData() {
    }

    public ReportData(Integer clientsAmount, Integer salesmansAmount, Long idSaleMoreExpensive, String worstSalesman) {
        this.clientsAmount = clientsAmount;
        this.salesmansAmount = salesmansAmount;
        this.idSaleMoreExpensive = idSaleMoreExpensive;
        this.worstSalesman = worstSalesman;
    }

    public Integer getClientsAmount() {
        return clientsAmount;
    }

    public void setClientsAmount(Integer clientsAmount) {
        this.clientsAmount = clientsAmount;
    }

    public Integer getSalesmansAmount() {
        return salesmansAmount;
    }

    public void setSalesmansAmount(Integer salesmansAmount) {
        this.salesmansAmount = salesmansAmount;
    }

    public Long getIdSaleMoreExpensive() {
        return idSaleMoreExpensive;
    }

    public void setIdSaleMoreExpensive(Long idSaleMoreExpensive) {
        this.idSaleMoreExpensive = idSaleMoreExpensive;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public void setWorstSalesman(String worstSalesman) {
        this.worstSalesman = worstSalesman;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
