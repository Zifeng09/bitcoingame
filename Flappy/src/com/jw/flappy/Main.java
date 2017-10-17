package com.jw.flappy;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.jw.flappy.input.Input;


public class Main implements Runnable{
	
	private int width = 1280;
	private int height = 720;
	
	private Thread thread;
	private boolean running = false;
	private long window;
	
	public void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public void init( ) {
		if(glfwInit()!= true) {
			//TODO
			return;
			
		}
		glfwWindowHint(GLFW_RESIZABLE,GL_TRUE);
		window = glfwCreateWindow(width,height,"Flappy",NULL,NULL);
		
		if (window == NULL) {
			//TODO
			return;
		}
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window,(vidmode.width()-width)/2, (vidmode.height() - height)/2);
		
		glfwSetKeyCallback(window,new Input());
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		GL.createCapabilities();

		
		glClearColor(1.0f,1.0f,1.0f,1.0f);
		glEnable(GL_DEPTH_TEST);
		System.out.println("OpenGL: "+glGetString(GL_VERSION));
		
		
	}
	
	public void run() {
		init();
		while (running) {
			update();
			render();
			
			if(glfwWindowShouldClose(window) == true) {
				running = false;
			}
		}
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
	
	public void update() {
		glfwPollEvents();
		if(Input.keys[GLFW_KEY_SPACE]) {
			System.out.println("FLAPYCOIN!");
		}
	}
	
	public void render( ) {
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(window);
		
	}

}
