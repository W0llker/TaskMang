package api.service;

import api.mapper.SupperMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public class CrudService<E, REQ, RESP> implements CrudServiceApi<REQ, RESP> {
    private JpaRepository<E, Long> repository;
    private SupperMapper<E, REQ, RESP> supperMapper;

    public CrudService(JpaRepository<E, Long> repository, SupperMapper<E, REQ, RESP> supperMapper) {
        this.repository = repository;
        this.supperMapper = supperMapper;
    }

    @Override
    public RESP add(REQ req) {
        return supperMapper.createResponse(repository.save(supperMapper.createEntity(req)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RESP update(REQ req) {
        return supperMapper.createResponse(repository.save(supperMapper.createEntity(req)));
    }

    @Override
    public RESP findById(Long id) {
        return supperMapper.createResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Не найдет данный Объект с id=" + id)));
    }
}
