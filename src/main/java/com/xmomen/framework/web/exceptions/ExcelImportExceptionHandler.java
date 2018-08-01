package com.xmomen.framework.web.exceptions;

import com.xmomen.framework.poi.ExcelImportResultModel;
import com.xmomen.framework.poi.ExcelImportValidFailException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tanxinzheng on 16/12/10.
 */
@ControllerAdvice
public class ExcelImportExceptionHandler extends AbstractRestExceptionHandler {

    /**
     * Excel导入校验失败
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({ExcelImportValidFailException.class})
    public final ResponseEntity<ExcelImportResultModel> handleException(ExcelImportValidFailException ex, HttpServletRequest request) throws IOException {
        ExcelImportResultModel restError = new ExcelImportResultModel(ex, request);
        restError.setStatus(HttpStatus.BAD_REQUEST.value());
        restError.setMessage("导入Excel数据校验失败");
        HttpHeaders headers = new HttpHeaders();
        ExcelImportResult excelImportResult = ex.getExcelImportResult();
        Workbook workbook = excelImportResult.getWorkbook();
        if(workbook != null){
            String uuid = DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
            String fileName = uuid + "_校验结果.xls";
            String encoderFileName = URLEncoder.encode(fileName, "UTF-8");
            ServletContext servletContext = request.getServletContext();
            String savepath = servletContext.getRealPath("/WEB-INF/temps");
            File file = new File(savepath, fileName);
            FileOutputStream os = new FileOutputStream(file);
            workbook.write(os);
            os.flush();
            os.close();
            restError.setValidResultUrl("/download/temps?file=" + encoderFileName);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    file.deleteOnExit();
                }
            }, 20000);
        }
        return new ResponseEntity<ExcelImportResultModel>(restError, headers, HttpStatus.BAD_REQUEST);
    }
}
