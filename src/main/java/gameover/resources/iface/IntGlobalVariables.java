package gameover.resources.iface;

import java.io.File;
import java.io.IOException;

public interface IntGlobalVariables {
	
	int getElementosPagina();
	
	void setElementosPagina(int elementosPagina);
			
	int getPaginaActual();

	void setPaginaActual(int paginaActual);
	
	int getTotalElementos();

	void setTotalElementos(int totalElementos);
	
	int getNumeroPaginas();

	void setNumeroPaginas();
	
	int getElementoInicial();
	
	String getRutaImagenes();
	
	int getNumElementosCarrousel();

	void setNumElementosCarrousel(int numElementosCarrousel);

	int getNumElementosVideos();
	
	void setNumElementosVideos(int numElementosVideos);
	
	int getNumElementosEstrenos();
	
	void setNumElementosEstrenos(int numElementosEstrenos);
	
	public int getNumElementosNoticias();

	public void setNumElementosNoticias(int numElementosNoticias);

	public int getNumElementosNovedades();

	public void setNumElementosNovedades(int numElementosNovedades);
	
	String sanearCadena(String cadena);
	
	boolean isValidImageFile(String contentType);
	
	File resizeImage (File originalFile, int newWidth, int newHeight, boolean aspectRatio, String destinationPath, String newImgName) throws IOException ;
	
	File cropImage (File originalFile, String destionPath, String newImgName) throws IOException ;
}