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

    public static final class Builder {
        private Integer clientsAmount;
        private Integer salesmansAmount;
        private Long idSaleMoreExpensive;
        private String worstSalesman;

        private Builder() {
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder clientsAmount(Integer clientsAmount) {
            this.clientsAmount = clientsAmount;
            return this;
        }

        public Builder salesmansAmount(Integer salesmansAmount) {
            this.salesmansAmount = salesmansAmount;
            return this;
        }

        public Builder idSaleMoreExpensive(Long idSaleMoreExpensive) {
            this.idSaleMoreExpensive = idSaleMoreExpensive;
            return this;
        }

        public Builder worstSalesman(String worstSalesman) {
            this.worstSalesman = worstSalesman;
            return this;
        }

        public ReportData build() {
            ReportData reportData = new ReportData();
            reportData.setClientsAmount(clientsAmount);
            reportData.setSalesmansAmount(salesmansAmount);
            reportData.setIdSaleMoreExpensive(idSaleMoreExpensive);
            reportData.setWorstSalesman(worstSalesman);
            return reportData;
        }
    }
}
