package uz.ml.delivering_rest.mapper.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.ml.delivering_rest.dto.transactions.TransactionsCreateDTO;
import uz.ml.delivering_rest.dto.transactions.TransactionsGetDTO;
import uz.ml.delivering_rest.entity.entity.Transactions;
import uz.ml.delivering_rest.mapper.GenericMapper;

@Component
@Mapper(componentModel = "spring")
public interface TransactionsMapper extends GenericMapper<TransactionsCreateDTO, TransactionsGetDTO, Transactions> {
}
