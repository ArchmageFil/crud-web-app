package io.github.archmagefil.crudwebapp.модель;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Пользователь {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long ид;
    String имя;
    String фамилия;
    int возраст;
    @Column(unique = true, nullable = false)
    String элПочта;

    public static class СоздатьПользователя {
        Пользователь п;

        public СоздатьПользователя(){
            п = new Пользователь();
        }
        public СоздатьПользователя имя(String имя) {
            п.имя = имя;
            return this;
        }

        public СоздатьПользователя фамилия(String фамилия) {
            п.фамилия = фамилия;
            return this;
        }

        public СоздатьПользователя элПочта(String элПочта) {
            п.элПочта = элПочта;
            return this;
        }

        public СоздатьПользователя возраст(int возраст) {
            п.возраст = возраст;
            return this;
        }
        public Пользователь создать(){
            return п;
        }
    }
}
