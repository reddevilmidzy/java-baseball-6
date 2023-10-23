package baseball.controller;

import baseball.constant.Hint;
import baseball.model.Command;
import baseball.model.GameNumbers;
import baseball.model.Score;
import baseball.service.ScoreCalculator;
import baseball.service.ValidatorFactory;
import baseball.util.RandomNumbersGenerator;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.Map;

public class BaseballController {

    private final ScoreCalculator calculator;
    private final InputView inputView;
    private final OutputView outputView;

    public BaseballController(ScoreCalculator calculator, InputView inputView, OutputView outputView) {
        this.calculator = calculator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        play();
        ValidatorFactory.clear();
    }

    private void play() {
        while (true) {
            GameNumbers answer = new GameNumbers(RandomNumbersGenerator.generate());
            //TODO: 디버깅 용 출력문 지우기
            System.out.println("computer = " + answer);
            guess(answer);
            if (isRestart()) {
                continue;
            }
            break;
        }
    }

    private Boolean isRestart() {
        return Command.isRestart(readGameCommand());
    }

    private void guess(GameNumbers answer) {
        while (true) {
            GameNumbers guess = new GameNumbers(inputView.readGuessNumber());
            Score score = calculateScore(answer, guess);
            outputView.printScore(score);
            if (isCorrect(score)) {
                break;
            }
        }
        outputView.printGameEndMessage();
    }

    private Score calculateScore(GameNumbers answer, GameNumbers guess) {
        int strike = calculator.calculateStrike(answer, guess);
        int ball = calculator.calculateBall(answer, guess);
        return new Score(Map.of(Hint.STRIKE, strike, Hint.BALL, ball));
    }

    private Command readGameCommand() {
        String value = inputView.readGameCommand();
        return Command.fromValue(value);
    }

    private Boolean isCorrect(Score score) {
        if (score == null) {
            return false;
        }
        return score.isStrikeOut();
    }
}
