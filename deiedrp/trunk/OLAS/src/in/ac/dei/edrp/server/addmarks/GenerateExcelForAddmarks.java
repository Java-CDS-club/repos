package in.ac.dei.edrp.server.addmarks;

import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.ExportService2;
import in.ac.dei.edrp.server.ReportingFunction;

import jxl.Workbook;

import jxl.format.Colour;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GenerateExcelForAddmarks extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * This method returns generated formated xls file
     * File updated
     * @param request
     * @param response
     */
    //Updated by devendra May 5th
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        try {
            String university_id = request.getParameter("param");
            String entity_type = request.getParameter("param1");
            String entity_name = request.getParameter("param2");
            String program_name = request.getParameter("param3");
            /* Get Excel Data 
             *Updated by Devendra May 5th
             * */
            ByteArrayOutputStream bytes = generateExcelReport(university_id,
                    entity_type, entity_name, program_name);

            /* Initialize Http Response Headers */
            response.setHeader("Content-disposition",
                "attachment; filename=Final_merit_upload" + entity_type + "_" +
                entity_name + "_" + program_name +".xls");
            response.setContentType("application/vnd.ms-excel");

            /* Write data on response output stream */
            if (bytes != null) {
                response.getOutputStream().write(bytes.toByteArray());
            }
        } catch (WriteException e) {
            response.setContentType("text/plain");
            response.getWriter().print("An error as occured");
        } // catch ends
    } // doget ends

    //updated by Devendra May 5th
    public ByteArrayOutputStream generateExcelReport(String university_id,
        String entity_type, String entity_id, String program_id)
        throws IOException, WriteException {
        /* Stream containing excel data */
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ReportingFunction reportFunction = new ReportingFunction();

        /* Create Excel WorkBook and Sheet */
        WritableWorkbook workBook = Workbook.createWorkbook(outputStream);
        WritableSheet sheet = workBook.createSheet("Add Marks", 0);

        /* Generates Headers Cells */
        WritableFont headerFont = new WritableFont(WritableFont.TAHOMA, 8,
                WritableFont.BOLD);
        WritableCellFormat headerCellFormat = new WritableCellFormat(headerFont);
        headerCellFormat.setBackground(Colour.PALE_BLUE);

        /**
         * method updated
         */
        reportFunction.getEntity_description(university_id, entity_type);

        /**
         * method updated
         */
        String entity_name = reportFunction.getEntity_Name(entity_id,
                university_id);

        /**
         * method updated
         */
        String program_name = reportFunction.getProgram_Name(entity_id,
                program_id);

        sheet.addCell(new Label(5, 1,
                "DayalBagh Education Institute- Agra(282005)", headerCellFormat));
        sheet.addCell(new Label(5, 2,
                "List of Students for generating call list", headerCellFormat));
        sheet.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
        sheet.addCell(new Label(6, 3, entity_name, headerCellFormat));
        sheet.addCell(new Label(5, 4, "Program Name", headerCellFormat));
        sheet.addCell(new Label(6, 4, program_name, headerCellFormat));

        ReportInfoGetter export2 = new ReportInfoGetter();
        export2.setProgram_id(program_id);
        export2.setEntity_id(entity_id);
        export2.setOffered_by(entity_id);

        ExportService2 exportService2 = new ExportService2();

        /**
         * method updated
         */
        String[] headings = exportService2.getHeadLines(export2);

        sheet.addCell(new Label(1, 7, "Registration Number", headerCellFormat));
        sheet.addCell(new Label(2, 7, "Test Number", headerCellFormat));

        int i = 3;

        for (String s : headings) {
            sheet.addCell(new Label(i, 7, s+" Marks", headerCellFormat));
            sheet.addCell(new Label(++i, 7,
                    "Present(P)/Absent(A)" + " in " + s, headerCellFormat));
            i++;
        }

        /* Write & Close Excel WorkBook */
        workBook.write();
        workBook.close();

        return outputStream;
    }
} // class ends
