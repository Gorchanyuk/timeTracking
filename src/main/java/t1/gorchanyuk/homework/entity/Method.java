package t1.gorchanyuk.homework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "methods")
public class Method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;            //Наименование метода

    @Column(name = "declared_type", nullable = false)
    private String declaredType;    //Класс которому принадлежит метод


    @Column(name = "group_method", nullable = false)
    private String group;           //Группа к которой относится метод, (пакет в котором лежит класс)

    @OneToMany(mappedBy = "methodId")
    private List<ExecutionTime> executionTimes; //Список записей о времени выполнения для этого метода
}
