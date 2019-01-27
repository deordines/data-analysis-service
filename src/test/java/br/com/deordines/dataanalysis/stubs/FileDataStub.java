package br.com.deordines.dataanalysis.stubs;

import br.com.deordines.dataanalysis.dto.FileData;

public final class FileDataStub {

    public static FileData fileData() {
        return new FileData(
                SalesmanStub.salesmans(),
                ClientStub.clients(),
                SaleStub.sales()
        );
    }
}
