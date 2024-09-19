package in.gov.india.game.impl.fp;

import in.gov.india.PajeetScroller;
import in.gov.india.game.Game;
import in.gov.india.game.impl.fp.entity.Pajeet;
import in.gov.india.game.impl.fp.entity.TrainManager;
import in.gov.india.gui.ScreenResolution;
import in.gov.india.gui.render.Renderer;
import in.gov.india.util.ColorUtil;

import java.awt.*;

import static in.gov.india.game.impl.fp.entity.Pajeet.PAJEET_SIZE;
import static in.gov.india.game.impl.fp.entity.TrainManager.*;

public class FlappyPajeet extends Game {
    private final TrainManager trainManager;
    private final Pajeet pajeet;
    private int score;
    public static int BASE_SCALE = 1200;

    public FlappyPajeet(PajeetScroller pajeetScroller) {
        super("FlappyPajeet", pajeetScroller);
        this.trainManager = new TrainManager(this);
        this.pajeet = new Pajeet(this);
        this.score = 0;
    }

    @Override
    public void draw() {
        ScreenResolution sr = getPajeetScroller().getWindow().getResolution();

        getPajeetScroller().getTextureManager().getTexture("india.jpg").drawQuad(sr);
        pajeet.draw();
        trainManager.draw();

        Renderer renderer = getPajeetScroller().getWindow().getRenderer();
        //slop
        renderer.drawRect(5,sr.getHeight() - 50, 150, 150, ColorUtil.changeAlpha(new Color(164, 108, 74).getRGB(), 255));
        renderer.getSatisfyRegular().get(30.F).drawString("Rupees: " + score, 10, sr.getHeight() - 40, ColorUtil.generateWhiteColor(240));
    }

    @Override
    protected void tick() {
        pajeet.tick();
        trainManager.tick();

        if (trainManager.hasCollided(pajeet)) {
            resetGame();
        }

        if (!trainManager.trains.isEmpty() && trainManager.trains.getFirst().x + TRAIN_WIDTH < PAJEET_SIZE * 2 && !trainManager.trains.getFirst().passed) {
            score++;
            trainManager.trains.getFirst().passed = true;
        }
    }

    private void resetGame() {
        pajeet.reset();
        trainManager.initializeTrains();
        score = 0;
    }
}