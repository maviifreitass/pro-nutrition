/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.exporter;

import jakarta.faces.FacesException;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import org.primefaces.component.api.DynamicColumn;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.export.DataTableExporter;
import org.primefaces.component.export.ExportConfiguration;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.Constants;
import org.primefaces.util.EscapeUtils;

/**
 *
 * @author maria
 */
public class DietViewExportPDF extends DataTableExporter {

    private StringBuilder builder = new StringBuilder();

    @Override
    protected void preExport(FacesContext context, ExportConfiguration config) throws IOException {
        ExternalContext externalContext = context.getExternalContext();
        configureResponse(externalContext, config.getOutputFileName());

        if (config.getPreProcessor() != null) {
            config.getPreProcessor().invoke(context.getELContext(), new Object[]{builder});
        }
    }

    @Override
    protected void doExport(FacesContext context, DataTable table, ExportConfiguration config, int index) throws IOException {
        builder.append("" + table.getId() + "\n");

        if (config.isPageOnly()) {
            exportPageOnly(context, table, builder);
        } else if (config.isSelectionOnly()) {
            exportSelectionOnly(context, table, builder);
        } else {
            exportAll(context, table, builder);
        }

        builder.append("" + table.getId() + "");

        table.setRowIndex(-1);
    }

    @Override
    protected void postExport(FacesContext context, ExportConfiguration config) throws IOException {
        if (config.getPostProcessor() != null) {
            config.getPostProcessor().invoke(context.getELContext(), new Object[]{builder});
        }

        OutputStream os = context.getExternalContext().getResponseOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, config.getEncodingType());
        PrintWriter writer = new PrintWriter(osw);
        writer.write(builder.toString());
        writer.flush();
        writer.close();
        builder.setLength(0);
    }

    @Override
    protected void preRowExport(DataTable table, Object document) {
        ((StringBuilder) document).append("\t" + table.getVar() + "\n");
    }

    @Override
    protected void postRowExport(DataTable table, Object document) {
        ((StringBuilder) document).append("\t" + table.getVar() + "\n");
    }

    @Override
    protected void exportCells(DataTable table, Object document) {
        StringBuilder builder = (StringBuilder) document;
        for (UIColumn col : table.getColumns()) {
            if (col instanceof DynamicColumn) {
                ((DynamicColumn) col).applyStatelessModel();
            }

            if (col.isRendered() && col.isExportable()) {
                String columnTag = getColumnTag(col);
                addColumnValue(builder, col.getChildren(), columnTag, col);
            }
        }
    }

    protected String getColumnTag(UIColumn column) {
        String headerText = (column.getExportHeaderValue() != null) ? column.getExportHeaderValue() : column.getHeaderText();
        UIComponent facet = column.getFacet("header");
        String columnTag;

        if (headerText != null) {
            columnTag = headerText.toLowerCase();
        } else if (facet != null) {
            columnTag = exportValue(FacesContext.getCurrentInstance(), facet).toLowerCase();
        } else {
            throw new FacesException("No suitable xml tag found for " + column);
        }

        return EscapeUtils.forXmlTag(columnTag);
    }

    protected void addColumnValue(StringBuilder builder, List<UIComponent> components, String tag, UIColumn column) {
        FacesContext context = FacesContext.getCurrentInstance();

        builder.append("\t\t" + tag + "");

        if (column.getExportFunction() != null) {
            builder.append(EscapeUtils.forXml(exportColumnByFunction(context, column)));
        } else {
            for (UIComponent component : components) {
                if (component.isRendered()) {
                    String value = exportValue(context, component);
                    if (value != null) {
                        builder.append(EscapeUtils.forXml(value));
                    }
                }
            }
        }

        builder.append("" + tag + "\n");
    }

    protected void configureResponse(ExternalContext externalContext, String filename) {
        externalContext.setResponseContentType("text/html");
        externalContext.setResponseHeader("Expires", "0");
        externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        externalContext.setResponseHeader("Pragma", "public");
        externalContext.setResponseHeader("Content-disposition", ComponentUtils.createContentDisposition("attachment", filename + ".txt"));
        externalContext.addResponseCookie(Constants.DOWNLOAD_COOKIE, "true", Collections.<String, Object>emptyMap());
    }

    // em desenvolvimento
    @Override
    public String getContentType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getFileExtension() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
