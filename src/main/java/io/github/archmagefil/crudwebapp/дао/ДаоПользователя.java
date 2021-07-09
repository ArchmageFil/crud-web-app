package io.github.archmagefil.crudwebapp.дао;

import io.github.archmagefil.crudwebapp.модель.Пользователь;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean
public interface ДаоПользователя extends Дао {
    void создать(Пользователь пользователь);
    void обновить(Пользователь пользователь);
    void удалить(long ид);
    List<Пользователь> получитьВсех();
    List<Пользователь> найтиПользователя(long ид);
    List<Пользователь> найтиПользователя(String элПочта);
    List<Пользователь> найтиПользователя(String имя, String фамилия);
}
