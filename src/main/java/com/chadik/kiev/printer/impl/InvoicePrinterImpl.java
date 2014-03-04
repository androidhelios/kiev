package com.chadik.kiev.printer.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import antlr.debug.NewLineEvent;

import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.printer.IInvoicePrinter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

@Component
public class InvoicePrinterImpl implements IInvoicePrinter {

	private Document pdfDocument;
	private PdfPTable pdfTable;
	private OutputStream pdfFile;

	private Invoice invoice;
	private List<OrderItem> orderItems;

	@Override
	public void initInvoicePrinter() {
		try {

			invoice = getInvoice();
			orderItems = invoice.getOrderItems();

			String fileName = createFileName(invoice);

			pdfFile = new FileOutputStream(new File("pdfs/"+ fileName));
			pdfDocument = new Document(PageSize.A4);
			PdfWriter.getInstance(pdfDocument, pdfFile);
			pdfDocument.open();
			
			pdfDocument.addTitle("kiev - " + fileName);

			BaseFont baseFont = BaseFont.createFont(
					"c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			Font font = new Font(baseFont, 8);

			int headerwidths[] = { 5, 10, 10, 10, 10, 10, 10, 10, 10 };
			String orderItemColumnNames[] = getTableOrderItemColumnNames();

			pdfTable = new PdfPTable(orderItemColumnNames.length);
			pdfTable.setWidths(headerwidths);
			pdfTable.setWidthPercentage(100);

			for (int i = 0; i < orderItemColumnNames.length; i++) {
				pdfTable.addCell(new Phrase(orderItemColumnNames[i], font));
			}

			pdfTable.setHeaderRows(1);

			int i = 0;

			for (OrderItem orderItem : orderItems) {
				pdfTable.addCell(new Phrase(String.valueOf(++i), font));
				pdfTable.addCell(new Phrase(orderItem.getProduct()
						.getProductName(), font));
				pdfTable.addCell(new Phrase(orderItem.getProduct()
						.getProductMeasurement(), font));
				pdfTable.addCell(new Phrase(orderItem.getOrderItemQuantity(),
						font));
				pdfTable.addCell(new Phrase(orderItem.getProduct()
						.getProductPrice(), font));
				pdfTable.addCell(new Phrase(orderItem
						.getOrderItemQuantityPriceWithoutTax(), font));
				pdfTable.addCell(new Phrase(orderItem.getProduct()
						.getProductTax(), font));
				pdfTable.addCell(new Phrase(
						orderItem.getOrderItemQuantityTax(), font));
				pdfTable.addCell(new Phrase(orderItem
						.getOrderItemQuantityPrice(), font));
			}

			pdfDocument.add(pdfTable);
			
			pdfDocument.add(new Phrase("\n\n\n\n\n\n"));
			
			LineSeparator lineSeparator = new LineSeparator();
			
			pdfDocument.add(new Chunk(lineSeparator));

			pdfDocument.close();
			pdfFile.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String[] getTableOrderItemColumnNames() {
		return new String[] { "Реден Бр.", "Назив", "Мерна единица",
				"Количина", "Цена без данок", "Износ без данок", "ДДВ",
				"Износ на ДДВ", "Вкупен износ со ДДВ" };
	}

	@Override
	public Invoice getInvoice() {
		return invoice;
	}

	@Override
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String createFileName(Invoice invoice) {
		String folder = "pdfs/";
		String invoiceNumber = invoice.getInvoiceNumber();
		String invoiceCustomerName = "-"
				+ invoice.getCustomer().getCustomerName();
		String invoiceDate = "-" + invoice.getInvoiceDate();
		String extension = ".pdf";
		String invoiceFileName = invoiceNumber + invoiceCustomerName
				+ invoiceDate + extension;

		return invoiceFileName;
	}

}
