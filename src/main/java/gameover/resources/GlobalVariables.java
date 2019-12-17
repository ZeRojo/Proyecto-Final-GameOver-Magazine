package gameover.resources;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import gameover.resources.iface.IntGlobalVariables;

@Service("globalVariables")
@Scope("prototype")
public class GlobalVariables implements IntGlobalVariables {

	@Autowired
	ServletContext context; 
	
	@Value("${paginacion.elementos.pagina}")
	private int elementosPagina;
	private int paginaActual=1;
	private int totalElementos;
	private int numeroPaginas;
	@Value("${imagenes.ruta}")
	private String rutaImagenes;
	@Value("${elementos.carousel}")
	private int numElementosCarrousel;
	@Value("${elementos.videos}")
	private int numElementosVideos;
	@Value("${elementos.estrenos}")
	private int numElementosEstrenos;
	@Value("${elementos.noticias}")
	private int numElementosNoticias;
	@Value("${elementos.novedades}")
	private int numElementosNovedades;
		
	public GlobalVariables() {}
	
	@Override
	public int getElementosPagina() {
		return elementosPagina;
	}
	
	@Override
	public void setElementosPagina(int elementosPagina) {
		this.elementosPagina = elementosPagina;
	}
	
	@Override
	public int getPaginaActual() {
		return paginaActual;
	}

	@Override
	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	@Override
	public int getTotalElementos() {
		return totalElementos;
	}
	
	@Override
	public void setTotalElementos(int totalElementos) {
		this.totalElementos=totalElementos;
		setNumeroPaginas();
	}
	
	@Override
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	
	@Override
	public void setNumeroPaginas() {
		numeroPaginas = (int) (Math.ceil((float)totalElementos/ elementosPagina));
	}
	
	@Override
	public int getElementoInicial() {
		return elementosPagina*(paginaActual-1);
	}
	
	@Override
	public String getRutaImagenes() {
		return rutaImagenes;
	}

	@Override
	public int getNumElementosCarrousel() {
		return numElementosCarrousel;
	}

	@Override
	public void setNumElementosCarrousel(int numElementosCarrousel) {
		this.numElementosCarrousel = numElementosCarrousel;
	}

	@Override
	public int getNumElementosVideos() {
		return numElementosVideos;
	}

	@Override
	public void setNumElementosVideos(int numElementosVideos) {
		this.numElementosVideos = numElementosVideos;
	}
	
	@Override
	public int getNumElementosEstrenos() {
		return numElementosEstrenos;
	}

	@Override
	public void setNumElementosEstrenos(int numElementosEstrenos) {
		this.numElementosEstrenos = numElementosEstrenos;
	}

	@Override
	public int getNumElementosNoticias() {
		return numElementosNoticias;
	}

	@Override
	public void setNumElementosNoticias(int numElementosNoticias) {
		this.numElementosNoticias = numElementosNoticias;
	}

	@Override
	public int getNumElementosNovedades() {
		return numElementosNovedades;
	}

	@Override
	public void setNumElementosNovedades(int numElementosNovedades) {
		this.numElementosNovedades = numElementosNovedades;
	}

	@Override
	public String sanearCadena(String cadena) {
		String tempCadena = (Normalizer.normalize(cadena, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")).toLowerCase();
		tempCadena = tempCadena.replaceAll("[^a-zA-Z ]", "");
		tempCadena = tempCadena.trim().replaceAll("\\s{2,}", " ");
		tempCadena = tempCadena.replaceAll("\\s","-");
		return tempCadena;
	}
		
	@Override
	public boolean isValidImageFile(String contentType) {
		if (!(contentType.equals("image/pjpeg") || contentType.equals("image/jpeg") || contentType.equals("image/png")
				|| contentType.equals("image/gif") || contentType.equals("image/bmp")
				|| contentType.equals("image/x-png") || contentType.equals("image/x-icon"))) {
			return false;
		}
		return true;
	}
	
	@Override
	public File resizeImage (File originalFile, int newWidth, int newHeight, boolean aspectRatio, String destinationPath, String newImgName) throws IOException {
		BufferedImage originalImage = ImageIO.read(originalFile);
		if (newWidth<originalImage.getWidth()) {
			String formatName = (originalFile.getName()).substring((originalFile.getName()).lastIndexOf(".") + 1);
			if (aspectRatio) {
				newHeight = (newWidth*originalImage.getHeight())/originalImage.getWidth();
			}
			BufferedImage resizedImage = new BufferedImage(newWidth,newHeight,originalImage.getType());
			Graphics2D g2d = resizedImage.createGraphics();
	        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
	        g2d.dispose();
	        File destinationFile = new File(destinationPath+newImgName+"."+formatName);
	        ImageIO.write(resizedImage, formatName, destinationFile);
	        return destinationFile;
		} else return originalFile;
	}
	
	@Override
	public File cropImage (File originalFile, String destionPath, String newImgName) throws IOException {
		BufferedImage originalImage = ImageIO.read(originalFile);
		if (originalImage.getWidth()/originalImage.getHeight()<3.5) {
			String formatName = (originalFile.getName()).substring((originalFile.getName()).lastIndexOf(".") + 1);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, formatName, baos);
			byte[] res=baos.toByteArray();
			InputStream in = new ByteArrayInputStream(res);
	        BufferedImage bufferedImage = ImageIO.read(in);
	        int newHeight=(int) (bufferedImage.getWidth()/3.5);
	        int	yPosition=(int) ((bufferedImage.getHeight()-newHeight)/2);
	        BufferedImage croppedImage = bufferedImage.getSubimage(0, yPosition, bufferedImage.getWidth(), newHeight);
	        File destinationFile = new File(destionPath+newImgName+"."+formatName);
	        ImageIO.write(croppedImage, formatName, destinationFile);
	        return destinationFile;
		} else {
			return originalFile;
		}
    }
}