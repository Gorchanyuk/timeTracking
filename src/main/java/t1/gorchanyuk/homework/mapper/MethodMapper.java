package t1.gorchanyuk.homework.mapper;

import org.aspectj.lang.Signature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import t1.gorchanyuk.homework.dto.MethodDto;
import t1.gorchanyuk.homework.entity.Method;

@Mapper
public interface MethodMapper {

    @Mapping(target = "name", expression = "java(signature.toString())")
    @Mapping(target = "declaredType", expression = "java(signature.getDeclaringType().getSimpleName())")
    @Mapping(target = "group", expression = "java(signature.getDeclaringType().getPackage().getName().split(\"^.*\\\\.\")[1])")
    MethodDto getMethodDtoFromSignature(Signature signature);

    Method dtoToEntity(MethodDto dto);
}
