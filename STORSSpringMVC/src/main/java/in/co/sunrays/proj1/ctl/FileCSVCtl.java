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
public class FileCSVCtl extends BaseCtl {

	/**
	 * Display CSV File
	 * 
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping(value = "/FileCSV/display", method = RequestMethod.GET)
	public void doDisplay(HttpServletResponse response) throws IOException {
		System.out.println("Display Method");
		InputStream is = new FileInputStream(new File(
				"/home/ncs02/Documents/orrisa_user_loc.csv"));
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition",
				"attachment;filename=orrisa_user_loc.csv");
		IOUtils.copy(is, response.getOutputStream());
		response.flushBuffer();
	}
}
