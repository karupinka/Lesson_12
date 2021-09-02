import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);
    Triangle triangle;

    static Stream<Arguments> provideNegativeNumbersAndNull() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(0, 1, 1),
                Arguments.of(1, 0, 1),
                Arguments.of(1, 1, 0),
                Arguments.of(-1, 10, 10),
                Arguments.of(10, -2, 10),
                Arguments.of(100, 10, -100),
                Arguments.of(-1, -1, -100)
        );
    }

    static Stream<Arguments> provideNotNegativeNumbers() {
        return Stream.of(
                Arguments.of(3, 4, 5, 6.0)
        );
    }

    static Stream<Arguments> provideBigNumbers() {
        return Stream.of(
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE)
        );
    }

    static Stream<Arguments> provideNotTriangleNumbers() {
        return Stream.of(
                Arguments.of(1, 1, 10),
                Arguments.of(50, 13, 10)
        );
    }

    @BeforeEach
    public void setUp() {
        triangle = new Triangle();
    }

    @AfterEach
    public void tearDown() {
        triangle = null;
    }

    @DisplayName("Проверка позивного валидного значения")
    @ParameterizedTest
    @MethodSource("provideNotNegativeNumbers")
    public void testCheckValidNumbersCount(int a, int b, int c, double result) {
        logger.info(String.format("exp - %f, actual - %f", triangle.countArea(a, b, c), result));
        assertEquals(result, triangle.countArea(a, b, c));
    }

    @DisplayName("Проверка негативых значений и нулей")
    @ParameterizedTest
    @MethodSource("provideNegativeNumbersAndNull")
    public void testCheckNegativeNumbersCount(int a, int b, int c) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { triangle.countArea(a, b, c); });
        String message = exception.getMessage();
        assertEquals("Triangle contains negative number or null", message);
    }

    @DisplayName("Проверка больших значений")
    @ParameterizedTest
    @MethodSource("provideBigNumbers")
    public void testCheckBigNumbersCount(int a, int b, int c) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { triangle.countArea(a, b, c); });
        String message = exception.getMessage();
        assertEquals("The triangle not exists", message);
    }

    @DisplayName("Проверка несуществующих треугольников")
    @ParameterizedTest
    @MethodSource("provideNotTriangleNumbers")
    public void testCheckNotExistsTriangleCount(int a, int b, int c) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { triangle.countArea(a, b, c); });
        String message = exception.getMessage();
        assertEquals("The triangle not exists", message);
    }
}
