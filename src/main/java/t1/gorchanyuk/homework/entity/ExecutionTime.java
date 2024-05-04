package t1.gorchanyuk.homework.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "execution_times")
public class ExecutionTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "method_id", nullable = false)
    private Method methodId;            //Метод, к которому относится объект

    @Column(name = "execution_time")
    private Long executionTime;         //Дата создания записи о работе

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;    //Время создания записи

    @Column(name = "is_successfully", nullable = false)
    private boolean successfully;       // Успешное завершение (true - если без ошибок)

    @Column(name = "is_async", nullable = false)
    private boolean async;              //true - если @TrackAsyncTime, false - если @TrackTime
}
