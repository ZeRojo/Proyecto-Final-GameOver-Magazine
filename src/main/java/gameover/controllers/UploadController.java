package gameover.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import gameover.resources.iface.IntGlobalVariables;

@Controller
@RequestMapping("/file")
public class UploadController {

	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
    @Qualifier("com.cloudinary.cloud_name")
    String mCloudName;

    @Autowired
    @Qualifier("com.cloudinary.api_key")
    String mApiKey;

    @Autowired
    @Qualifier("com.cloudinary.api_secret")
    String mApiSecret;

	@SuppressWarnings("rawtypes")
	@PostMapping("/uploadAvatar")
	public ResponseEntity<Object> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("ruta") String ruta) throws IOException {
		if (!file.getOriginalFilename().isEmpty()) {
			if (variables.isValidImageFile(file.getContentType())) {
				Cloudinary c=new Cloudinary("cloudinary://"+mApiKey+":"+mApiSecret+"@"+mCloudName);
				try {
					File originalFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
					file.transferTo(originalFile);	
					File resizedFile = variables.resizeImage(originalFile, 80, 80, false, System.getProperty("java.io.tmpdir"), "resizedimage");
			        Map params = ObjectUtils.asMap("folder", "gameover"+ruta);
			        Map uploadResult = c.uploader().upload(resizedFile, params );
			        System.out.println("==============>>"+uploadResult.get("url"));
			        String resultado=uploadResult.get("url").toString();
			        FileUtils.deleteQuietly(originalFile);
			        FileUtils.deleteQuietly(resizedFile);
			        return new ResponseEntity<>(resultado,HttpStatus.OK);
			    } catch (IOException e) {
			        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			    }
			} else {
				return new ResponseEntity<>("No es un archivo de imágen válido.",HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Por favor seleccione un archivo.",HttpStatus.BAD_REQUEST);
		}	
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/uploadImgPrincipal")
	public ResponseEntity<Object> uploadImgPrincipal(@RequestParam("file") MultipartFile file,@RequestParam("idarticulo") String idarticulo) {
		if (!file.getOriginalFilename().isEmpty()) {
			if (variables.isValidImageFile(file.getContentType())) {
				Cloudinary c=new Cloudinary("cloudinary://"+mApiKey+":"+mApiSecret+"@"+mCloudName);
				try {
					File originalFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
					file.transferTo(originalFile);		
					File resizedImage=variables.resizeImage(originalFile, 1900, 0, true, System.getProperty("java.io.tmpdir"), "resizedimage");
					File croppedImage=variables.cropImage(resizedImage, System.getProperty("java.io.tmpdir"), "croppedImage");
					Map params = ObjectUtils.asMap("folder", "gameover/articulos/articulo_"+idarticulo+"/");
			        Map uploadResult = c.uploader().upload(croppedImage, params );
			        System.out.println("==============>>"+uploadResult.get("url"));
			        String resultado=uploadResult.get("url").toString();
			        FileUtils.deleteQuietly(originalFile);
			        FileUtils.deleteQuietly(resizedImage);
			        FileUtils.deleteQuietly(croppedImage);		
					return new ResponseEntity<>(resultado,HttpStatus.OK);
			    } catch (IOException e) {
			        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			    }
			} else {
				return new ResponseEntity<>("No es un archivo de imágen válido.",HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Por favor seleccione un archivo.",HttpStatus.BAD_REQUEST);
		}	
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/uploadImagen")
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("idarticulo") String idarticulo) throws IOException {
		if (!file.getOriginalFilename().isEmpty()) {
			if (variables.isValidImageFile(file.getContentType())) {
				Cloudinary c=new Cloudinary("cloudinary://"+mApiKey+":"+mApiSecret+"@"+mCloudName);
				try {
					File originalFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
					file.transferTo(originalFile);
					Map params = ObjectUtils.asMap("folder", "gameover/articulos/articulo_"+idarticulo+"/");
			        Map uploadResult = c.uploader().upload(originalFile, params );
			        System.out.println("==============>>"+uploadResult.get("url"));
			        String resultado=uploadResult.get("url").toString();
			        FileUtils.deleteQuietly(originalFile);		
					return new ResponseEntity<>(resultado,HttpStatus.OK);			
				} catch (IOException e) {
			        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			    }
			} else {
				return new ResponseEntity<>("No es un archivo de imágen válido.",HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Por favor seleccione un archivo.",HttpStatus.BAD_REQUEST);
		}	
	}
}