package baseball.controller;

import baseball.service.ScoreCalculator;
import baseball.util.Converter;
import baseball.util.RandomNumbersGenerator;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.List;

public class BaseballController {

    private ScoreCalculator calculator;

    private final InputView inputView;
    private final OutputView outputView;

    public BaseballController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        List<Integer> computer = RandomNumbersGenerator.generate();
        System.out.println("computer = " + computer);
        String guessNumber = inputView.readGuessNumber();
        //TODO: 디버깅 용 출력문 지우기
        List<Integer> player = Converter.convertList(guessNumber);

        this.calculator = new ScoreCalculator(computer, player);
        int strike = calculator.calculateStrike();
        int ball = calculator.calculateBall();
        outputView.printResult(strike, ball);
    }
}
