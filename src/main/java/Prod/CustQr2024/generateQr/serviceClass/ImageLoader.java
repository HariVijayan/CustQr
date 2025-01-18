package Prod.CustQr2024.generateQr.serviceClass;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ImageLoader {

	private final ResourceLoader resourceLoader;

	@Autowired
	public ImageLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public BufferedImage loadImage(String imageName) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:/static/Images/" + imageName);
		InputStream inputStream = resource.getInputStream();
		return ImageIO.read(inputStream);
	}
}
