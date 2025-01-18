package Prod.CustQr2024.controller;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import org.springframework.web.bind.annotation.RestController;

@RestController
class RotateMethods {

	public static BufferedImage flipVertically(BufferedImage originalImage) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -originalImage.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(originalImage, null);
	}

	public static BufferedImage flipHorizontally(BufferedImage originalImage) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-originalImage.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(originalImage, null);
	}

	public static BufferedImage rotate90Clockwise(BufferedImage originalImage) {
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		BufferedImage rotatedImage = new BufferedImage(height, width, originalImage.getType());
		Graphics2D g2d = rotatedImage.createGraphics();
		g2d.translate(height, 0);
		g2d.rotate(Math.toRadians(90));
		g2d.drawImage(originalImage, 0, 0, null);
		g2d.dispose();

		return rotatedImage;
	}

	public static BufferedImage rotate180(BufferedImage originalImage) {
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		BufferedImage rotatedImage = new BufferedImage(width, height, originalImage.getType());
		Graphics2D g2d = rotatedImage.createGraphics();
		g2d.translate(width, height);
		g2d.rotate(Math.toRadians(180));
		g2d.drawImage(originalImage, 0, 0, null);
		g2d.dispose();

		return rotatedImage;
	}

	public static BufferedImage rotate90Anticlockwise(BufferedImage originalImage) {
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		BufferedImage rotatedImage = new BufferedImage(height, width, originalImage.getType());
		Graphics2D g2d = rotatedImage.createGraphics();
		g2d.translate(0, width);
		g2d.rotate(Math.toRadians(-90));
		g2d.drawImage(originalImage, 0, 0, null);
		g2d.dispose();

		return rotatedImage;
	}

	public static BufferedImage rotateToDegreeClockwise(BufferedImage originalImage, double degrees) {
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		double radians = Math.toRadians(degrees);
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);

		int newWidth = (int) Math.floor(width * cos + height * sin);
		int newHeight = (int) Math.floor(height * cos + width * sin);

		BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
		Graphics2D g2d = rotatedImage.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - width) / 2, (newHeight - height) / 2);
		at.rotate(radians, width / 2, height / 2);
		g2d.setTransform(at);
		g2d.drawImage(originalImage, 0, 0, null);
		g2d.dispose();

		return rotatedImage;
	}

	public static BufferedImage rotateToDegreeAnticlockwise(BufferedImage originalImage, double degrees) {
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		double radians = Math.toRadians(degrees);
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);

		int newWidth = (int) Math.floor(width * cos + height * sin);
		int newHeight = (int) Math.floor(height * cos + width * sin);

		BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
		Graphics2D g2d = rotatedImage.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - width) / 2, (newHeight - height) / 2);
		at.rotate(-radians, width / 2, height / 2);
		g2d.setTransform(at);
		g2d.drawImage(originalImage, 0, 0, null);
		g2d.dispose();

		return rotatedImage;
	}

}
