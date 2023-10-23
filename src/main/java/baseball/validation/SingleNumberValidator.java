package baseball.validation;

import baseball.constant.ErrorMessage;
import baseball.constant.Number;

public class SingleNumberValidator implements Validator {

    private static final String ONLY_NATURE_NUMBER = "^[1-9]+";

    @Override
    public boolean support(Class<?> clazz) {
        return Integer.class.isAssignableFrom(clazz) || String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target) {
        if (target instanceof String) {
            validateType((String) target);
        }
        if (target instanceof Integer) {
            validateRange((Integer) target);
        }
    }

    private void validateType(String target) {
        if (!target.matches(ONLY_NATURE_NUMBER)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TYPE);
        }
    }

    private void validateRange(Integer target) {
        if (target < Number.MIN || Number.MAX < target) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE);
        }
    }
}
