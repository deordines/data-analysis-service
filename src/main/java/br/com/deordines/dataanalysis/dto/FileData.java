package br.com.deordines.dataanalysis.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class FileData {

    private List<Salesman> salesmans;
    private List<Client> clients;
    private List<Sale> sales;

    public FileData() {
    }

    public FileData(List<Salesman> salesmans, List<Client> clients, List<Sale> sales) {
        this.salesmans = salesmans;
        this.clients = clients;
        this.sales = sales;
    }

    public List<Salesman> getSalesmans() {
        return salesmans;
    }

    public void setSalesmans(List<Salesman> salesmans) {
        this.salesmans = salesmans;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public static final class Builder {
        private List<Salesman> salesmans;
        private List<Client> clients;
        private List<Sale> sales;

        private Builder() {
            salesmans = new ArrayList<>();
            clients = new ArrayList<>();
            sales = new ArrayList<>();
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder salesman(Salesman salesman) {
            salesmans.add(salesman);
            return this;
        }

        public Builder client(Client client) {
            clients.add(client);
            return this;
        }

        public Builder sale(Sale sale) {
            sales.add(sale);
            return this;
        }

        public FileData build() {
            FileData fileData = new FileData();
            fileData.setSalesmans(salesmans);
            fileData.setClients(clients);
            fileData.setSales(sales);
            return fileData;
        }
    }
}
