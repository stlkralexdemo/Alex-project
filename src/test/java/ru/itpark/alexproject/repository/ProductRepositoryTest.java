package ru.itpark.alexproject.repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itpark.alexproject.entity.ProductEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.itpark.alexproject.entity.ProductType.MTB;
import static ru.itpark.alexproject.entity.ProductType.ROAD;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void findByName() {
        em.persist(new ProductEntity(
                0,
                "Jamis XENITH PRO",
                154464,
                "Шоссейные велогонки и тренировки с друзьями могут стать интереснее, а результаты – улучшиться, если под вами качественный и легкий велосипед, оснащенный превосходным оборудованием. Геометрия каждого размера велосипеда скрупулезно рассчитана, чтобы спортсмены любого роста чувствовали себя удобно, как в режиме гонки, так и на тренировках. Жесткая карбоновая рама идеально передает ваши усилия колесам благодаря ассиметричным нижним перьям, а также эффективно гасит вибрации. Компоненты Shimano Ultegra обеспечивают точное, молниеносное переключение и не подведут вас во время ответственных соревнований. Колеса American Classic крепятся к раме при помощи жестких сквозных осей, что улучшает точность управления и эффективность. Тормоза Shimano Ultegra обладают большой мощностью и отличной модуляцией, а для тех, кто катается по горным серпантинам наиболее важно, что они никогда не перегреваются благодаря технологии Ice Tech. Также в раме предусмотрена внутренняя проводка тросов и гидролиний, совместимая с электрической трансмиссией Shimano Di2",
                null,
                54,
               0,
                ROAD,
                null));

        var actual = repository
                .findAllByNameContainsIgnoreCaseOrderByPriceDesc("PRO")
                .size();

        assertEquals(1, actual);
    }
}
