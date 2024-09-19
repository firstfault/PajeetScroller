package in.gov.india.game.impl.fp.entity;

import in.gov.india.game.Game;
import in.gov.india.gui.textures.Texture;
import in.gov.india.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

import static in.gov.india.game.impl.fp.entity.Pajeet.PAJEET_SIZE;

public class TrainManager {
    private final Game game;

    public final Texture trainTexture;
    public int spawnOffset;
    public final List<Train> trains = new ArrayList<>();

    public static final int TRAIN_WIDTH = 80;
    private static final int TRAIN_GAP = 200;
    private static final int TRAIN_SPACING = 300;
    private static final int TRAIN_SCROLL_SPEED = 15;

    public TrainManager(Game game) {
        this.game = game;
        this.trainTexture = game.getPajeetScroller().getTextureManager().getTexture("train.jpg");
        this.spawnOffset = game.getPajeetScroller().getWindow().getResolution().getWidthI();
        initializeTrains();
    }

    public void addTrain(float x) {
        float minGapY = 100;
        float maxGapY = game.getPajeetScroller().getWindow().getResolution().getHeight() - TRAIN_GAP;
        float gapY = minGapY + MathUtil.getRandom().nextFloat() * (maxGapY - minGapY);
        trains.add(new Train(x, gapY));
    }

    public void initializeTrains() {
        trains.clear();
        float x = spawnOffset;
        while (x - spawnOffset < TRAIN_SPACING * 2) {
            addTrain(x);
            x += TRAIN_SPACING;
        }
    }

    public void draw() {
        for (Train train : trains) {
            this.drawTrainQuad(train.x, 0, TRAIN_WIDTH, train.gapY);
            this.drawTrainQuad(train.x, train.gapY + TRAIN_GAP, TRAIN_WIDTH,
                    game.getPajeetScroller().getWindow().getResolution().getHeight() - (train.gapY + TRAIN_GAP));
        }
    }

    private void drawTrainQuad(float x, float y, int w, float h) {
        this.trainTexture.drawQuad(x, y, w, h);
    }

    public void tick() {
        for (Train train : trains) {
            train.x -= TRAIN_SCROLL_SPEED;
        }

        while (!trains.isEmpty() && trains.getFirst().x < -TRAIN_WIDTH) {
            trains.removeFirst();
        }

        if (trains.isEmpty() || spawnOffset - trains.getLast().x > TRAIN_SPACING) {
            float newTrainX = trains.isEmpty() ? spawnOffset : trains.getLast().x + TRAIN_SPACING;
            addTrain(newTrainX);
        }
    }

    public boolean hasCollided(Pajeet pajeetEntity) {
        int pajeetRadius = PAJEET_SIZE / 2;
        if (trains.isEmpty())
            return false;

        Train firstTrain = trains.getFirst();

        if (pajeetEntity.getY() - pajeetRadius < 0 || pajeetEntity.getY() + pajeetRadius > game.getPajeetScroller().getWindow().getResolution().getHeight()) {
            return true;
        }

        if (PAJEET_SIZE * 2 + pajeetRadius > firstTrain.x && PAJEET_SIZE * 2 - pajeetRadius < firstTrain.x + TRAIN_WIDTH) {
            return pajeetEntity.getY() - pajeetRadius < firstTrain.gapY || pajeetEntity.getY() + pajeetRadius > firstTrain.gapY + TRAIN_GAP;
        }
        return false;
    }
}
