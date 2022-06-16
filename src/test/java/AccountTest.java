import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String firstAndLastName;
    private final boolean expected;
    Account account;

    public AccountTest(String firstAndLastName, boolean expected) {
        this.firstAndLastName = firstAndLastName;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] dataForTest() {
        return new Object[][] {
                {"Тимоти Шаламе", true},
                {"Бенедикт Камбербэтч", true}, // 19 символов (граничное значение)
                {"Т Ш", true}, // 3 символа (граничное значение)
                {"Ти", false}, // 2 символа (граничное значение)
                {"ТимотейШевроле", false}, // нет пробела
                {" ТимотиШаламе", false}, // пробел в начале
                {"ТимотиШаламе ", false}, // пробел в конце
                {"Шонн Уильям Скотт", false}, // 2 пробела
                {"Арнольд Шварценеггер", false}, // 20 символов (граничное значение)
        };
    }

    @Test
    @DisplayName("Проверка строки для печати")
    public void checkNameToEmbossWithParams() {
        account = createNewAccount(firstAndLastName);
        boolean actual = runCheckNameToEmboss();
        checkNameToEmbossResult(actual);
    }

    @Step("Создание объекта аккаунт")
    public Account createNewAccount(String firstAndLastName){
        return new Account(firstAndLastName);
    }

    @Step("Запуск метода checkNameToEmboss")
    public boolean runCheckNameToEmboss(){
        return account.checkNameToEmboss();
    }

    @Step("Проверка равенства expected и actual")
    public void checkNameToEmbossResult(boolean actual){
        assertEquals(expected, actual);
    }
}