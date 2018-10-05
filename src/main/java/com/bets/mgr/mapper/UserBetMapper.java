package com.bets.mgr.mapper;

import com.bets.mgr.entity.UserBet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

// Esto permite poder hacer Autowired en el servicio
@Mapper(componentModel = "spring")
public interface UserBetMapper {

    @Mappings({
            @Mapping(source = "entity.id", target = "user"),
            @Mapping(source = "entity.id", target = "match")
    })
    UserBetDto toDto(UserBet entity);

    List<UserBetDto> listToDto(List<UserBet> userBets);

    @Mappings({
            @Mapping(source = "user", target = "user.id"),
            @Mapping(source = "match", target = "match.id")
    })
    UserBet toEntity(UserBetDto dto);
}
