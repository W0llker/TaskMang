package application.entity;

import api.dto.task.TaskPriority;
import api.dto.task.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "seq_task", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Client author;
    @ManyToOne
    @JoinColumn(name = "executor_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Client executor;
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "feedback")
    private List<FeedBack> feedBack;
}
