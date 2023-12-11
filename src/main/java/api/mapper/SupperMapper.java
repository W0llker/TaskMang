package api.mapper;

public interface SupperMapper<E, REQ, RESP> {
    E createEntity(REQ req);

    RESP createResponse(E client);
}
