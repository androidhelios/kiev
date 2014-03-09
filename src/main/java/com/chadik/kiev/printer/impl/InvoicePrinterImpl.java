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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.Pfm2afm;
import com.itextpdf.text.pdf.draw.LineSeparator;

@Component
public class InvoicePrinterImpl implements IInvoicePrinter {

	private Document pdfDocument;
	private OutputStream pdfFile;

	private Invoice invoice;
	private List<OrderItem> orderItems;

	@Override
	public void initInvoicePrinter() {
		try {
			
			invoice = getInvoice();
			orderItems = invoice.getOrderItems();
			
			int headerwidths[] = { 5, 10, 10, 10, 10, 10, 10, 10, 10 };
			String orderItemColumnNames[] = getTableOrderItemColumnNames();
			
			LineSeparator lineSeparator = new LineSeparator();

			String fileName = createFileName(invoice);
			
			BaseFont baseFont = BaseFont.createFont(
					"c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			
			Font font = new Font(baseFont, 8);

			pdfFile = new FileOutputStream(new File("pdfs/"+ fileName));
			pdfDocument = new Document(PageSize.A4);
			PdfWriter.getInstance(pdfDocument, pdfFile);
			
			pdfDocument.open();
			
			pdfDocument.addTitle("ПСРФ - " + fileName);
			
			PdfPTable tableSupplierInfoText = new PdfPTable(1);
			tableSupplierInfoText.setWidthPercentage(100);
			
			PdfPCell cellSupplierInfoText = new PdfPCell(new Phrase("Продавач / име и дреса / телефон", font));
			cellSupplierInfoText.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoText.setBorder(Rectangle.NO_BORDER);
			cellSupplierInfoText.setPaddingBottom(5);
			tableSupplierInfoText.addCell(cellSupplierInfoText);
			
			pdfDocument.add(tableSupplierInfoText);
			
			PdfPTable tableSupplierInfoContent = new PdfPTable(1);
			tableSupplierInfoContent.setWidthPercentage(100);
			
			PdfPCell cellSupplierInfoTextContent = new PdfPCell(new Phrase(invoice.getSupplier().getSupplierUserName() + " - " + invoice.getSupplier().getSupplierAddress() + " - " + invoice.getSupplier().getSupplierPhoneNumber(), font));
			cellSupplierInfoTextContent.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoTextContent.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
			cellSupplierInfoTextContent.setPaddingBottom(5);
			tableSupplierInfoContent.addCell(cellSupplierInfoTextContent);			
			
			pdfDocument.add(tableSupplierInfoContent);
			
			PdfPTable tableSupplierInfoAccountText = new PdfPTable(3);
			tableSupplierInfoAccountText.setWidthPercentage(100);
						
			PdfPCell cellSupplierInfoAccountTextBankAccountNumber = new PdfPCell(new Phrase("Жиро сметка:", font));
			cellSupplierInfoAccountTextBankAccountNumber.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankAccountNumber.setBorder(Rectangle.NO_BORDER);
			cellSupplierInfoAccountTextBankAccountNumber.setPaddingBottom(5);
			cellSupplierInfoAccountTextBankAccountNumber.setPaddingTop(10);
			tableSupplierInfoAccountText.addCell(cellSupplierInfoAccountTextBankAccountNumber);
			
			PdfPCell cellSupplierInfoAccountTextRegistryNumber = new PdfPCell(new Phrase("Број за ДДВ:", font));
			cellSupplierInfoAccountTextRegistryNumber.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextRegistryNumber.setBorder(Rectangle.NO_BORDER);
			cellSupplierInfoAccountTextRegistryNumber.setPaddingBottom(5);
			cellSupplierInfoAccountTextRegistryNumber.setPaddingTop(10);
			tableSupplierInfoAccountText.addCell(cellSupplierInfoAccountTextRegistryNumber);
			
			PdfPCell cellSupplierInfoAccountTextBankName = new PdfPCell(new Phrase("Депонент на:", font));
			cellSupplierInfoAccountTextBankName.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankName.setBorder(Rectangle.NO_BORDER);
			cellSupplierInfoAccountTextBankName.setPaddingBottom(5);
			cellSupplierInfoAccountTextBankName.setPaddingTop(10);
			tableSupplierInfoAccountText.addCell(cellSupplierInfoAccountTextBankName);
			
			PdfPCell cellSupplierInfoAccountTextBankAccountNumberValue = new PdfPCell(new Phrase(invoice.getSupplier().getSupplierRegistryNumber(), font));
			cellSupplierInfoAccountTextBankAccountNumberValue.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankAccountNumberValue.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
			cellSupplierInfoAccountTextBankAccountNumberValue.setPaddingBottom(5);
			tableSupplierInfoAccountText.addCell(cellSupplierInfoAccountTextBankAccountNumberValue);
			
			PdfPCell cellSupplierInfoAccountTextRegistryNumberValue = new PdfPCell(new Phrase(invoice.getSupplier().getSupplierBankAccount(), font));
			cellSupplierInfoAccountTextRegistryNumberValue.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextRegistryNumberValue.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
			cellSupplierInfoAccountTextRegistryNumberValue.setPaddingBottom(5);
			tableSupplierInfoAccountText.addCell(cellSupplierInfoAccountTextRegistryNumberValue);
			
			PdfPCell cellSupplierInfoAccountTextBankNameValue = new PdfPCell(new Phrase(invoice.getSupplier().getSupplierBankName(), font));
			cellSupplierInfoAccountTextBankNameValue.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankNameValue.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
			cellSupplierInfoAccountTextBankNameValue.setPaddingBottom(5);
			tableSupplierInfoAccountText.addCell(cellSupplierInfoAccountTextBankNameValue);
			
			pdfDocument.add(tableSupplierInfoAccountText);

			pdfDocument.add(new Chunk(lineSeparator));
			
			PdfPTable tableCustomerInfoText = new PdfPTable(1);
			tableCustomerInfoText.setWidthPercentage(100);
			
			PdfPCell cellCustomerInfoText = new PdfPCell(new Phrase("Купувач / име и адреса /", font));
			cellCustomerInfoText.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellCustomerInfoText.setBorder(Rectangle.NO_BORDER);
			cellCustomerInfoText.setPaddingBottom(5);
			tableCustomerInfoText.addCell(cellCustomerInfoText);
			
			pdfDocument.add(tableCustomerInfoText);
			
			PdfPTable tableCustomerInvoceInfoContent = new PdfPTable(2);
			tableCustomerInvoceInfoContent.setWidthPercentage(100);
			
			PdfPCell cellCustomerInfoContentCustomerName = new PdfPCell(new Phrase(invoice.getCustomer().getCustomerName() + "\n" + invoice.getCustomer().getCustomerAddress(), font));
			cellCustomerInfoContentCustomerName.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cellCustomerInfoContentCustomerName.setVerticalAlignment(Element.ALIGN_CENTER);
			cellCustomerInfoContentCustomerName.setUseAscender(true);
			cellCustomerInfoContentCustomerName.setFixedHeight(70);
//			cellCustomerInfoContentCustomerName.setBorder(Rectangle.NO_BORDER);			
//			cellCustomerInfoContentCustomerName.setPaddingBottom(5);
			tableCustomerInvoceInfoContent.addCell(cellCustomerInfoContentCustomerName);
			
			PdfPCell cellInvoceInfoContentInvoiceNumber = new PdfPCell(new Phrase("Фактура:______________________", font));
			cellInvoceInfoContentInvoiceNumber.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentInvoiceNumber.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellInvoceInfoContentInvoiceNumber.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentInvoiceNumber.setFixedHeight(70);
//			cellInvoceInfoContentInvoiceNumber.setBorder(Rectangle.NO_BORDER);			
//			cellInvoceInfoContentInvoiceNumber.setPaddingBottom(5);
			tableCustomerInvoceInfoContent.addCell(cellInvoceInfoContentInvoiceNumber);
			
			pdfDocument.add(tableCustomerInvoceInfoContent);
			
			pdfDocument.add(new Phrase("\n"));

			PdfPTable orderItemTable = new PdfPTable(orderItemColumnNames.length);
			orderItemTable.setWidths(headerwidths);
			orderItemTable.setWidthPercentage(100);

			for (int i = 0; i < orderItemColumnNames.length; i++) {
				orderItemTable.addCell(new Phrase(orderItemColumnNames[i], font));
			}

			orderItemTable.setHeaderRows(1);

			int i = 0;

			for (OrderItem orderItem : orderItems) {
				orderItemTable.addCell(new Phrase(String.valueOf(++i), font));
				orderItemTable.addCell(new Phrase(orderItem.getProduct()
						.getProductName(), font));
				orderItemTable.addCell(new Phrase(orderItem.getProduct()
						.getProductMeasurement(), font));
				orderItemTable.addCell(new Phrase(orderItem.getOrderItemQuantity(),
						font));
				orderItemTable.addCell(new Phrase(orderItem.getProduct()
						.getProductPrice(), font));
				orderItemTable.addCell(new Phrase(orderItem
						.getOrderItemQuantityPriceWithoutTax(), font));
				orderItemTable.addCell(new Phrase(orderItem.getProduct()
						.getProductTax(), font));
				orderItemTable.addCell(new Phrase(
						orderItem.getOrderItemQuantityTax(), font));
				orderItemTable.addCell(new Phrase(orderItem
						.getOrderItemQuantityPrice(), font));
			}

			pdfDocument.add(orderItemTable);
			
//			pdfDocument.add(new Phrase("\n\n\n\n\n\n"));

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
