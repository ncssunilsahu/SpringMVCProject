package in.co.sunrays.proj1.ctl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FilePDFCtl extends BaseCtl {

	/**
	 * Display PDF File
	 * 
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping(value = "/FilePDF/display", method = RequestMethod.GET)
	public void doDisplay(HttpServletResponse response) throws IOException {
		System.out.println("Display Method");
		InputStream is = new FileInputStream(new File(
				"/home/ncs02/Downloads/jar/aug_hn_2014.pdf"));
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment;filename=aug_hn_2014.pdf");
		IOUtils.copy(is, response.getOutputStream());
		response.flushBuffer();
	}

}
