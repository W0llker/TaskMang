package api.controller;

import api.service.CrudServiceApi;

public class CrudControllerApi<REQ,RESP> implements ControllerApi<REQ,RESP> {
    private CrudServiceApi<REQ,RESP> serviceApi;

    public CrudControllerApi(CrudServiceApi<REQ, RESP> serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public RESP save(REQ req) {
        return serviceApi.add(req);
    }

    @Override
    public RESP findById(Long id) {
        return serviceApi.findById(id);
    }

    @Override
    public void delete(Long id) {
        serviceApi.delete(id);
    }

    @Override
    public RESP update(REQ req) {
        return serviceApi.update(req);
    }
}
