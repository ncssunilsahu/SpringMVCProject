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
public class FileExcelCtl extends BaseCtl {

	/**
	 * Display Excel File
	 * 
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping(value = "/FileExcel/display", method = RequestMethod.GET)
	public void doDisplay(HttpServletResponse response) throws IOException {
		System.out.println("Display Method");
		InputStream is = new FileInputStream(new File(
				"/home/ncs02/Downloads/Srikakulam.xls"));
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment;filename=Srikakulam.xls");
		IOUtils.copy(is, response.getOutputStream());
		response.flushBuffer();
	}
}
