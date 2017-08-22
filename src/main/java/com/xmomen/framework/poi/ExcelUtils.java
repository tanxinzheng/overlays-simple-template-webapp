package com.xmomen.framework.poi;

import com.google.common.collect.Maps;
import com.xmomen.framework.exception.BusinessException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.jeecgframework.poi.excel.view.MiniAbstractExcelView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tanxinzheng on 17/7/30.
 */
public class ExcelUtils extends MiniAbstractExcelView {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportUtil.class);

    public static final ExcelUtils EXCEL_UTILS = new ExcelUtils();

    public static ExcelUtils getInstance(){
        return EXCEL_UTILS;
    }

    /**
     * 导出
     * @param response
     * @param clazz
     * @param data
     * @param filename
     * @param exportParams
     * @param <T>
     */
    public static <T> void export(HttpServletRequest request, HttpServletResponse response,
                                  Class clazz, List<T> data, String filename, ExportParams exportParams){
        String datetime = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
        Map<String, Object> modelMap = Maps.newHashMap();
        modelMap.put(NormalExcelConstants.FILE_NAME,  filename + "_" + datetime);
        modelMap.put(NormalExcelConstants.PARAMS, exportParams);
        modelMap.put(NormalExcelConstants.CLASS, clazz);
        modelMap.put(NormalExcelConstants.DATA_LIST, data);
        try {
            getInstance().renderMergedOutputModel(modelMap, request, response);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 导出
     * @param response
     * @param clazz
     * @param data
     * @param filename
     * @param <T>
     */
    public static <T> void export(HttpServletRequest request, HttpServletResponse response,
                                  Class clazz, List<T> data, String filename){
        export(request, response, clazz, data, filename, new ExportParams());
    }

    /**
     * 导出Excel View
     * @param clazz
     * @param data
     * @param filename
     * @param <T>
     * @return
     */
    public static <T> ModelAndView export(ModelMap modelMap, Class clazz, List<T> data, String filename){
        return export(modelMap, clazz, data, filename, new ExportParams());
    }

    /**
     * 导出Excel View
     * @param clazz
     * @param data
     * @param filename
     * @param exportParams
     * @param <T>
     * @return
     */
    public static <T> ModelAndView export(ModelMap modelMap, Class clazz, List<T> data, String filename, ExportParams exportParams){
        String datetime = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
        modelMap.put(NormalExcelConstants.FILE_NAME,  filename + "_" + datetime);
        modelMap.put(NormalExcelConstants.PARAMS, exportParams);
        modelMap.put(NormalExcelConstants.CLASS, clazz);
        modelMap.put(NormalExcelConstants.DATA_LIST, data);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }

    /**
     * Excel转换为实体对象
     * @param multipartFile
     * @param model
     * @param <T>
     * @return
     */
    public static <T> List<T> transform(MultipartFile multipartFile, Class<T> model){
        return transform(multipartFile, model, new ImportParams());
    }

    /**
     * Excel转换为实体对象
     * @param multipartFile
     * @param model
     * @param importParams
     * @param <T>
     * @return
     */
    public static <T> List<T> transform(MultipartFile multipartFile, Class<T> model, ImportParams importParams){
        try {
            if(multipartFile.isEmpty()){
                throw new BusinessException("文件不能空");
            }
            if(!(multipartFile.getOriginalFilename().endsWith(".xls")
                    || multipartFile.getOriginalFilename().endsWith(".xlsx"))){
                throw new BusinessException("请选择正确格式的Excel文件上传，文件后缀（.xls）或（.xlsx)");
            }
            return transform(multipartFile.getInputStream(), model, importParams);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new BusinessException("Excel导入失败，错误信息：" + e.getMessage());
        }
    }

    /**
     * Excel转换为实体对象
     * @param inputStream
     * @param model
     * @param <T>
     * @return
     */
    public static <T> List<T> transform(InputStream inputStream, Class<T> model){
        return transform(inputStream, model, new ImportParams());
    }

    /**
     * Excel转换为实体对象
     * @param file
     * @param model
     * @param <T>
     * @return
     */
    public static <T> List<T> transform(File file, Class<T> model){
        return transform(file, model, new ImportParams());
    }

    /**
     * Excel转换为实体对象
     * @param file
     * @param model
     * @param importParams
     * @param <T>
     * @return
     */
    public static <T> List<T> transform(File file, Class<T> model, ImportParams importParams){
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new BusinessException("Excel导入失败，错误信息：未找到指定文件");
        }
        return transform(in, model, importParams);
    }

    /**
     * Excel转换为实体对象
     * @param inputStream
     * @param model
     * @param importParams
     * @param <T>
     * @return
     */
    public static <T> List<T> transform(InputStream inputStream, Class<T> model, ImportParams importParams){
        try {
            return ExcelImportUtil.importExcel(inputStream, model, importParams);
        } catch (Exception e) {
            throw new BusinessException("Excel模板不正确，导入失败");
        }
    }


    /**
     * Subclasses must implement this method to actually render the view.
     * <p>The first step will be preparing the request: In the JSP case,
     * this would mean setting model objects as request attributes.
     * The second step will be the actual rendering of the view,
     * for example including the JSP via a RequestDispatcher.
     *
     * @param model    combined output Map (never {@code null}),
     *                 with dynamic values taking precedence over static attributes
     * @param request  current HTTP request
     * @param response current HTTP response
     * @throws Exception if rendering failed
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件";
        Workbook workbook = null;
        if (model.containsKey(NormalExcelConstants.MAP_LIST)) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) model
                    .get(NormalExcelConstants.MAP_LIST);
            if (list.size() == 0) {
                throw new RuntimeException("MAP_LIST IS NULL");
            }
            workbook = ExcelExportUtil.exportExcel(
                    (ExportParams) list.get(0).get(NormalExcelConstants.PARAMS), (Class<?>) list.get(0)
                            .get(NormalExcelConstants.CLASS),
                    (Collection<?>) list.get(0).get(NormalExcelConstants.DATA_LIST));
            for (int i = 1; i < list.size(); i++) {
                new ExcelExportServer().createSheet(workbook,
                        (ExportParams) list.get(i).get(NormalExcelConstants.PARAMS), (Class<?>) list
                                .get(i).get(NormalExcelConstants.CLASS),
                        (Collection<?>) list.get(i).get(NormalExcelConstants.DATA_LIST));
            }
        } else {
            workbook = ExcelExportUtil.exportExcel(
                    (ExportParams) model.get(NormalExcelConstants.PARAMS),
                    (Class<?>) model.get(NormalExcelConstants.CLASS),
                    (Collection<?>) model.get(NormalExcelConstants.DATA_LIST));
        }
        if (model.containsKey(NormalExcelConstants.FILE_NAME)) {
            codedFileName = (String) model.get(NormalExcelConstants.FILE_NAME);
        }
        if (workbook instanceof HSSFWorkbook) {
            codedFileName += HSSF;
        } else {
            codedFileName += XSSF;
        }
        if (isIE(request)) {
            codedFileName = java.net.URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
}
