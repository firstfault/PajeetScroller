package in.gov.india.gui;

import in.gov.india.PajeetScroller;
import in.gov.india.events.*;
import in.gov.india.gui.audio.AudioManager;
import in.gov.india.gui.misc.MouseButton;
import in.gov.india.gui.render.Renderer;
import in.gov.india.gui.screen.GuiScreen;
import in.gov.india.gui.screen.impl.GuiMainMenu;
import in.gov.india.util.Stopwatch;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.opengl.GL;

import javax.vecmath.Vector2f;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private final PajeetScroller pajeetScroller;
    private final AudioManager audioManager = new AudioManager();
    private ScreenResolution resolution = new ScreenResolution(1200, 600);
    private Vector2f mousePosition = new Vector2f(0, 0);
    private GuiScreen screen;
    private long window;
    private long vg;
    private Renderer renderer;
    private Stopwatch tick = new Stopwatch();

    public Window(PajeetScroller pajeetScroller) {
        this.pajeetScroller = pajeetScroller;
        this.pajeetScroller.getEventBus().register(this);
        init();
        this.setScreen(new GuiMainMenu());
    }

    public void setScreen(GuiScreen screen) {
        if (this.screen == screen) {
            return;
        }
        if (this.screen != null) {
            this.screen.close(this);
            pajeetScroller.getEventBus().unregister(this.screen);
        }
        screen.initializeGui(this);
        this.screen = screen;
        pajeetScroller.getEventBus().register(screen);
    }

    public void begin() {
        loop();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
        audioManager.cleanup();
        pajeetScroller.getEventBus().post(new EventWindowClosed());
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(this.resolution.getWidthI(), this.resolution.getHeightI(), "PajeetScroller", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        glfwSetFramebufferSizeCallback(window, (window, newWidth, newHeight) -> {
            this.resolution = new ScreenResolution(newWidth, newHeight);
            glViewport(0, 0, newWidth, newHeight);
            screen.initializeGui(this);
        });
        glfwSetCursorPosCallback(window, (window, x, y) -> {
            this.mousePosition = new Vector2f((float) x, (float) y);
        });
        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            if (action == GLFW_PRESS || action == GLFW_RELEASE) {
                this.pajeetScroller.getEventBus().post(new EventMouseButton(this.mousePosition, MouseButton.fromCode(button), action == GLFW_PRESS));
            }
        });
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (action == GLFW_PRESS || action == GLFW_RELEASE) {
                this.pajeetScroller.getEventBus().post(new EventKey(action == GLFW_PRESS, key));
            }
        });
        glfwSetWindowFocusCallback(window, ((window, focused) -> {
            this.pajeetScroller.getEventBus().post(new EventWindowFocus(focused));
        }));

        audioManager.init();

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        vg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS);
        this.renderer = new Renderer(this);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, this.resolution.getWidthI(), this.resolution.getHeightI(), 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();

            nvgBeginFrame(vg, this.resolution.getWidth(), this.resolution.getHeight(), 2);
            nvgShapeAntiAlias(vg, false);

            if (tick.hasPassed(50L)) {
                pajeetScroller.getEventBus().post(new EventGameTick());
                tick.reset();
            }

            this.screen.drawGui(this);

            nvgEndFrame(vg);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public long getNanoVGContext() {
        return vg;
    }

    public long getPointer() {
        return window;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Vector2f getMousePosition() {
        return mousePosition;
    }

    public PajeetScroller getPajeetScroller() {
        return pajeetScroller;
    }

    public ScreenResolution getResolution() {
        return resolution;
    }
}
