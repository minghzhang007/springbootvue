package com.lewis.sprinbootvue.biz.utils.excel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

public abstract class AbstractExportExcelTemplate {

    private HttpServletResponse response;

    public AbstractExportExcelTemplate(HttpServletResponse response) {
        this.response = response;

    }

    /**
     * 导出Excel
     */
    public void exportExcel() {
        ExportExcel ex = new ExportExcel(getTitle(), getRowNames(), getDataList());
        try {
            OutputStream output = response.getOutputStream();
            String fileName = URLEncoder.encode(getFileName(), "utf-8");
            response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + fileName + ".xls");
            response.setContentType("application/msexcel");
            ex.export(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出excel文件的文件名
     *
     * @return
     */
    protected abstract String getFileName();

    /**
     * 到导出的数据对象集合
     *
     * @return
     */
    protected abstract List<Object[]> getDataList();

    /**
     * 要导出的excel表头列名字
     *
     * @return
     */
    protected abstract String[] getRowNames();

    /**
     * 要导出的excel的工作簿的标题
     *
     * @return
     */
    protected abstract String getTitle();
}
