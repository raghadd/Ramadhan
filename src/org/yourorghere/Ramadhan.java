package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;

/**
 * Ramadhan.java <BR>
 * Raghad Alafeef - 1508190
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Ramadhan implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Ramadhan());
        frame.add(canvas);
        frame.setSize(590, 630);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1, 1, 1, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        Texture tex; // create object
        gl.glEnable(GL.GL_TEXTURE_2D); //activate texture mapping for 2D
        try {
            //load texture
            tex = TextureIO.newTexture(new File("ra.png"), true);
            tex.bind();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // SKY
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glTranslatef(0, 0, -8.0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0.282f, 0.27f, 0.498f);
        gl.glVertex2d(-5, 4); // top left
        gl.glVertex2d(5, 4); // top right
        gl.glColor3f(0.368f, 0.647f, 0.568f);
        gl.glVertex2d(5, -9);  // bottom right
        gl.glVertex2d(-5, -9); // bottom left
        gl.glEnd();

        // TEXTURE
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glColor4f(1, 1, 1, 1);
        gl.glTranslatef(0.2f, 0.2f, 4);
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0, 1);
        gl.glVertex2f(0, 0);
        gl.glTexCoord2f(1, 1);
        gl.glVertex2f(1, 0);
        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(1, 1);
        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(0, 1);
        gl.glEnd();

        // MOON
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glTranslatef(-3.2f, 3.7f, -11.0f);

        // moon glow 1
        float numPoints = 30;
        float Radius = 1.7f;
        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
        gl.glColor4f(1, 0.996f, 0.909f, 0.05f);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            //2.0*PI==360
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y);
        }
        gl.glEnd();

        // moon glow 2
        numPoints = 30;
        Radius = 1.4f;
        gl.glColor4f(1, 0.996f, 0.909f, 0.1f);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            //2.0*PI==360
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y);
        }
        gl.glEnd();

        // moon glow 3
        numPoints = 30;
        Radius = 1.2f;
        gl.glColor4f(1, 0.996f, 0.909f, 0.1f);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            //2.0*PI==360
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y);
        }
        gl.glEnd();

        // moon glow 4
        numPoints = 30;
        Radius = 1.08f;
        gl.glColor4f(1, 0.996f, 0.909f, 0.1f);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            //2.0*PI==360
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y);
        }
        gl.glEnd();

        // moon
        numPoints = 30;
        Radius = 1f;
        gl.glColor3f(1, 0.996f, 0.909f);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            //2.0*PI==360
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y);
        }
        gl.glEnd();

        // STARS
        gl.glTranslatef(0, 0, 0);
        gl.glPointSize(3);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2i(4, 1);
        gl.glEnd();

        gl.glPointSize(2);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2i(0, -4);
        gl.glEnd();

        gl.glPointSize(1);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(3.4f, -4f);
        gl.glEnd();

        gl.glPointSize(2);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(4, -3f);
        gl.glEnd();

        gl.glPointSize(3);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(4.7f, -4f);
        gl.glEnd();

        gl.glPointSize(2);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(2, -4.8f);
        gl.glEnd();

        gl.glPointSize(1);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2i(2, -2);
        gl.glEnd();

        gl.glPointSize(3);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2i(5, -2);
        gl.glEnd();

        gl.glPointSize(3);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2i(7, 0);
        gl.glEnd();

        gl.glPointSize(1);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(7.8f, 1.3f);
        gl.glEnd();

        gl.glPointSize(2);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(8.5f, -1.9f);
        gl.glEnd();

        gl.glPointSize(2);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(-2.5f, -1.9f);
        gl.glEnd();

        gl.glPointSize(2);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(-2.8f, -1f);
        gl.glEnd();

        gl.glPointSize(1);
        gl.glBegin(gl.GL_POINTS);
        gl.glColor3f(1, 0.235f, 0.482f);
        gl.glVertex2f(8.8f, -2.3f);
        gl.glEnd();

        // BUILDINGS
        gl.glTranslatef(1, -8.7f, 5);

        // to be edited
        gl.glColor3f(0.074f, 0.074f, 0.105f); // darker
        gl.glRectd(3.25, 0.3, 3.5, 4);

        // head translate
        gl.glTranslatef(4.5f, 2.6f, -10);
        gl.glBegin(gl.GL_POLYGON);
        gl.glVertex2f(0,0.4f);
        gl.glVertex2f(-0.25f,0.6f);
        gl.glVertex2f(0.25f,1); // peak
        gl.glVertex2f(0.75f, 0.6f);
        gl.glVertex2f(0.5f, 0.4f);
        gl.glEnd();
        
        gl.glLineWidth(3);
        gl.glBegin(gl.GL_LINES);
        gl.glVertex2f(-0.1f,-0.1f);
        gl.glVertex2f(0.6f, -0.1f);
        
        gl.glVertex2f(-0.1f,0.3f);
        gl.glVertex2f(0.6f, 0.3f);
        gl.glEnd();
        
        // Window
        gl.glColor3f(1, 0.896f, 0.909f);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2f(0,0f);
        gl.glVertex2f(0,0.25f);
        gl.glVertex2f(0.25f,0.25f);
        gl.glVertex2f(0.25f,0);
        gl.glEnd();
        
        numPoints = 15;
        Radius = 1f;
        gl.glColor4f(1, 0.996f, 0.909f, 0.2f);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            //2.0*PI==360
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y);
        }
        gl.glEnd();
        
        
        gl.glTranslatef(-0.2f, -4f, 0);
        numPoints = 25;
        Radius = 2f;
        gl.glColor3f(0.074f, 0.074f, 0.105f);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            //2.0*PI==360
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y);
        }
        gl.glEnd();
        
        gl.glTranslatef(0.2f, 4f, 0);
        
        
        // reverse after head translate
        gl.glTranslatef(-4.5f, -2.6f, 10);
        
        // left
        //gl.glColor3f(0.074f, 0.074f, 0.105f); // darker
        //gl.glRectd(-3, 0.3, 1.5, 2.4);

        gl.glColor3f(0.094f, 0.09f, 0.168f);  // dark mid
        gl.glRectd(-3, 0.3, 0.4, 3.4);

        gl.glColor3f(0.109f, 0.105f, 0.192f); //light mid
        gl.glRectd(-3, 0.3, -0.1, 4.4);

        gl.glColor3f(0.125f, 0.121f, 0.215f);  // light
        gl.glRectd(-3f, 0.3f, -0.8, 5);

        // middle \ far buildings  ????? ?????
        gl.glColor3f(0.109f, 0.137f, 0.149f);
        gl.glRectd(2, 0.3, 5, 1.6);

        // right
        //gl.glColor3f(0.094f, 0.09f, 0.168f); // dark mid
        //gl.glRectd(3.4, 0.3, 4.5, 2.4);

        gl.glColor3f(0.109f, 0.105f, 0.192f); //light mid
        //gl.glColor3f(0.615f, 0.992f, 0.509f);
        gl.glRectd(4.1, 0.3, 5.5, 3.4);

        gl.glColor3f(0.125f, 0.121f, 0.215f);  // light
        gl.glRectd(5, 0.3, 6.5, 4);

        //  ???? ??????? ???????
        
        //gl.glColor3f(1, 0.496f, 0);  orange
        gl.glColor3f(1, 0.996f, 0.909f);  // white
        gl.glPointSize(3);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2f(0.5f, 3.2f);
        gl.glVertex2f(0.8f, 3f);
        gl.glVertex2f(1.2f, 2.8f);
        gl.glVertex2f(1.9f, 2.67f);
        gl.glVertex2f(2.5f, 2.6f);
        //gl.glVertex2f(2.87f, 2.7f);
        gl.glVertex2f(3f, 2.67f);
        gl.glVertex2f(3.4f, 2.7f);
        gl.glVertex2f(3.6f, 2.75f);
        gl.glVertex2f(3.9f, 2.85f);
        gl.glEnd();
        
        
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
