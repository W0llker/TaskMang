package application.repository;

import application.entity.Task;
import application.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Page<Task> findByAuthor(Client author, Pageable pageable);
    Page<Task> findByExecutor(Client executor, Pageable pageable);
}
