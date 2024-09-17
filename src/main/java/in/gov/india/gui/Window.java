package in.gov.india.gui;

import in.gov.india.PajeetScroller;
import in.gov.india.events.EventGameClosed;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private final PajeetScroller pajeetScroller;
    private long window;

    private final int width = 1200;
    private final int height = 600;

    public Window(PajeetScroller pajeetScroller) {
        this.pajeetScroller = pajeetScroller;
        init();
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

        window = glfwCreateWindow(width, height, "PajeetScroller", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glOrtho(0, width, height, 0, -1, 1);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            pajeetScroller.getTextureManager().getTexture("img.png").drawQuad(3, 3);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}
