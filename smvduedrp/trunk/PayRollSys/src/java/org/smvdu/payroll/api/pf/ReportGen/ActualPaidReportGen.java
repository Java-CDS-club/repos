/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf.ReportGen;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.user.OrgLogoDB;




/**
 *
 * @author smvdu
 */
@ManagedBean
@RequestScoped

public class ActualPaidReportGen {

    /** Creates a new instance of AnnualTaxReportGen */
    public ActualPaidReportGen() {
    }

    public String actualpaidReport()
    {
        try
        {
            return "ActualPaidTax.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public String actualPaidReportAsHtml()
    {
        try
        {
            return "ActualPaidReportAsHtml.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
 public void actualPaidReportAsPDF()
    {

  try
        {
            java.sql.Connection cn = new CommonDB().getConnection();
            UserInfo ub = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("title", "Annual Tax Report");
         //   map.put("year","" + ub.getCurrentYear());
            map.put("org_code",""+ub.getUserOrgCode());
            
              //Image img = new OrgLogoDB().loadLogoImage();
        //    map.put("org_logo",img);
            FacesContext facesContext = FacesContext.getCurrentInstance();
             String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path+File.separator+"JasperFile/actualpaid.jasper", map, cn);
            //InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("JasperFile/salaryslip.jasper");
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
            JRAbstractExporter exporter1 = new JRPdfExporter();
            Map<JRExporterParameter, Object> parameterExport = new HashMap<JRExporterParameter, Object>();
            parameterExport.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
            parameterExport.put(JRPdfExporterParameter.OUTPUT_STREAM, servletOutputStream);
            response.setContentType("application/pdf");
            exporter1.setParameters(parameterExport);
            exporter1.exportReport();
            servletOutputStream.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        
        }
}
}
