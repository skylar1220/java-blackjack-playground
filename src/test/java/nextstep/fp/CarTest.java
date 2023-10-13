package nextstep.fp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// 다음 테스트 코드에서 MoveStrategy에 대한 익명 클래스로 구현하고 있는데 람다를 적용해 구현한다.
public class CarTest {
//    @Test
//    public void 이동() {
//        Car car = new Car("pobi", 0);
//        Car actual = car.move(new MoveStrategy() {
//            @Override
//            public boolean isMovable() {
//                return true;
//            }
//        });
//        assertThat(actual).isEqualTo(new Car("pobi", 1));
//    }
//
//    @Test
//    public void 정지() {
//        Car car = new Car("pobi", 0);
//        Car actual = car.move(new MoveStrategy() {
//            @Override
//            public boolean isMovable() {
//                return false;
//            }
//        });
//        assertThat(actual).isEqualTo(new Car("pobi", 0));
//    }

    @Test
    public void 이동() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(() -> true);
        assertThat(actual).isEqualTo(new Car("pobi", 1));
    }

    @Test
    public void 정지() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(() -> false);
        assertThat(actual).isEqualTo(new Car("pobi", 0));
    }

}
