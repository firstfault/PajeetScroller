package in.gov.india.gui;

import in.gov.india.PajeetScroller;
import in.gov.india.events.EventGameClosed;
import in.gov.india.gui.screen.GuiScreen;
import in.gov.india.gui.screen.impl.GuiMainMenu;
import in.gov.india.gui.textures.Texture;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private final PajeetScroller pajeetScroller;
    private long window;

    private ScreenResolution resolution = new ScreenResolution(1200, 600);
    private GuiScreen screen;

    public Window(PajeetScroller pajeetScroller) {
        this.pajeetScroller = pajeetScroller;
        init();
        this.setScreen(new GuiMainMenu(pajeetScroller));
    }

    public void setScreen(GuiScreen screen) {
        screen.initializeGui(this.resolution);
        this.screen = screen;
    }

    public void begin() {
        loop();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
        pajeetScroller.getEventBus().post(new EventGameClosed());
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
            screen.initializeGui(this.resolution);
        });

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, this.resolution.getWidthI(), this.resolution.getHeightI(), 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();

            this.screen.drawGui(resolution);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public ScreenResolution getResolution() {
        return resolution;
    }
}
