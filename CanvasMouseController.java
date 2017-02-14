import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created by trold on 2/8/17.
 */
public class CanvasMouseController extends MouseAdapter {
	Model model;
	DrawCanvas canvas;
	Point2D lastMousePosition;
	private static boolean draggingLine;

	public CanvasMouseController(DrawCanvas canvas, Model model) {
		this.model = model;
		this.canvas = canvas;
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addMouseWheelListener(this);
		draggingLine = false;
	};


	/**
	 * {@inheritDoc}
	 *
	 * @param e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		lastMousePosition = e.getPoint();
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point2D mouseInModel = canvas.toModelCoords(lastMousePosition);
			model.add(new Line2D.Double(mouseInModel, mouseInModel));
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {	}

	/**
	 * {@inheritDoc}
	 *
	 * @param e
	 *
	 * @since 1.6
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Point2D currentMousePosition = e.getPoint();
		if (draggingLine) {
			Point2D mouseInModel = canvas.toModelCoords(currentMousePosition);
			Line2D lastLine = model.removeLast();
			lastLine.setLine(lastLine.getP1(), mouseInModel);
			model.add(lastLine);
		}
		else {
			double dx = currentMousePosition.getX() - lastMousePosition.getX();
			double dy = currentMousePosition.getY() - lastMousePosition.getY();
			canvas.pan(dx, dy);
			lastMousePosition = currentMousePosition;
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param e
	 *
	 * @since 1.6
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double factor = Math.pow(0.9, e.getWheelRotation());
		Point2D currentMousePosition = e.getPoint();
		double dx = currentMousePosition.getX();
		double dy = currentMousePosition.getY();
		canvas.pan(-dx, -dy);
		canvas.zoom(factor);
		canvas.pan(dx, dy);
	}

	public static void isDrawing(){
		draggingLine = true;
	}

	public static void isPanning() {
		draggingLine = false;
	}
}
