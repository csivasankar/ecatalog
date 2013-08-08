package org.cisco.catalog.util;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.util.Streams;
import org.cisco.catalog.domain.BlobData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {

	protected static final Logger log = LoggerFactory.getLogger(ImageUtil.class);

	public static void createThumbnail(String filename, int thumbWidth,
			int thumbHeight, String outFilename, String ext)
			throws InterruptedException, FileNotFoundException, IOException {
		// load image from filename
		Image image = Toolkit.getDefaultToolkit().getImage(filename);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		mediaTracker.waitForID(0);
		// use this to test for errors at this point:

		// determine thumbnail size from WIDTH and HEIGHT
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}

		// draw original image to thumbnail image object and
		// scale it to the new size on-the-fly
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

		ImageIO.write(thumbImage, ext, new File(outFilename));
	}
	
	/*public static void main(String[] a) throws FileNotFoundException, InterruptedException, IOException{
		ImageUtil iu = new ImageUtil();
		iu.createThumbnail("d:/koala.jpg", 200, 200, "d:/korala_thumb.jpg");
	}*/
	
	public static void loadImage(org.cisco.catalog.domain.Image img, String uploadPath) {
		if (img.getBlobData() != null) {
			File file = null;
			try {
				file = new File(uploadPath + img.getImagePath());
				createImage(file, img.getBlobData(), file.getAbsolutePath(), true, 0, 0, img.getExt());
				File thumbPath = new File(uploadPath + img.getThumbImagePath());
				createImage(file, img.getBlobData(), thumbPath.getAbsolutePath(), false, 175, 130, img.getExt());
				File largeImagePath = new File(uploadPath + img.getLargeImagePath());
				createImage(file, img.getBlobData(), largeImagePath.getAbsolutePath(), false, 550, 415, img.getExt());
			} catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			if(log.isDebugEnabled()){
				log.debug("BLOB is not available for " + img.getImagePath());
			}
		}
	}

	private static void createImage(File file, BlobData blobData, String path, boolean original, int width, int height, String ext)
			throws IOException, InterruptedException, SQLException {
		File currentFile = new File(path);
		if (!currentFile.exists()) {
			if (original) {
				if (blobData != null) {
					File filePath = new File(file.getAbsolutePath());
					String makeDir = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
					new File(makeDir).mkdirs();
					FileOutputStream fos = new FileOutputStream(filePath, false);
				    Streams.copy(blobData.getContent().getBinaryStream(), fos, true);
				    fos.close();
				    if(log.isDebugEnabled()){
						log.debug("Image is created from BLOB for " + file.getPath());
					}
				}
			} else {
				if (file.exists()) {
					createThumbnail(file.getAbsolutePath(), width, height, path, ext);
					if(log.isDebugEnabled()){
						log.debug("Thumb Image created for " + currentFile.getAbsolutePath());
					}
				} else {
					if (log.isDebugEnabled()) {
						log.debug("Original file doesn't exist : " + file.getPath());
					}
				}
			}
		}
	}
}
