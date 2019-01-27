package br.com.deordines.dataanalysis.stubs;

import br.com.deordines.dataanalysis.dto.Client;

import java.util.Arrays;
import java.util.List;

public final class ClientStub {

    public static Client client1() {
        return new Client("12345678000101", "Client 1", "Business Area 1");
    }

    public static Client client2() {
        return new Client("12345678000102", "Client 2", "Business Area 2");
    }

    public static List<Client> clients() {
        return Arrays.asList(client1(), client2());
    }
}
