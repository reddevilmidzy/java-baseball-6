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
        play();
    }

    private void play() {
        List<Integer> computer = RandomNumbersGenerator.generate();
        //TODO: 디버깅 용 출력문 지우기
        System.out.println("computer = " + computer);

        while (true) {
            String guessNumber = inputView.readGuessNumber();
            List<Integer> player = Converter.convertList(guessNumber);
            this.calculator = new ScoreCalculator(computer, player);
            int strike = calculator.calculateStrike();
            int ball = calculator.calculateBall();
            outputView.printResult(strike, ball);
            if (strike == 3) {
                outputView.printGameEndMessage();
                break;
            }
        }
        String gameCommand = inputView.readGameCommand();
        if (gameCommand.equals("1")) {
            play();
            return;
        }
        if (gameCommand.equals("2")) {
            return;
        }
    }
}
