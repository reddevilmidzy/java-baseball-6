package baseball.service;

import baseball.validation.GameNumberValidator;
import baseball.validation.SingleNumberValidator;
import baseball.validation.Validator;

import java.util.HashSet;
import java.util.Set;

public class ValidatorFactory {
    private static final Set<Validator> validators = new HashSet<>();

    private static void init() {
        addValidator(new SingleNumberValidator());
        addValidator(new GameNumberValidator());
    }

    private static void addValidator(Validator validator) {
        validators.add(validator);
    }

    public static void clear() {
        validators.clear();
    }

    public static Validator getValidator(Class<?> target) {
        if (validators.isEmpty()) {
            init();
        }
        return validators.stream()
                .filter(validator -> validator.support(target))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        //TODO: 적절한 예외 처리
    }
}
