package t1.gorchanyuk.timetracking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MethodDto {

    private String name;            //Наименование метода

    private String declaredType;    //Класс к которому относится метод

    private String group;           //Группа к которой относится метод (изначально - Имя пакета в котором лежит класс)
}
