package api.service;

public interface CrudServiceApi<REQ, RESP> {
    RESP add(REQ req);

    void delete(Long id);

    RESP update(REQ req);

    RESP findById(Long id);
}
