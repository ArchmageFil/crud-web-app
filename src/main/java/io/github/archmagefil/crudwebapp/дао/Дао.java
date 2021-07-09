package io.github.archmagefil.crudwebapp.дао;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Дао {
    String создатьБД();

    String очиститьБд();

    String удалитьБД();
}
