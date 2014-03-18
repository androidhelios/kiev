package com.chadik.kiev.printer.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.itextpdf.text.Paragraph;
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

			int numberOfRows = 25;

			int headerwidths[] = { 5, 10, 10, 10, 10, 10, 10, 10, 10 };
			String orderItemColumnNames[] = getTableOrderItemColumnNames();

			String fileName = createFileName(invoice);

			BaseFont baseFont = BaseFont.createFont(
					"c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);

			Font fontNormal = new Font(baseFont, 8);
			Font fontInfo = new Font(baseFont, 8, Font.ITALIC);
			Font fontInfoText = new Font(baseFont, 10, Font.BOLD);
			Font fontTableHeader = new Font(baseFont, 8, Font.BOLD);
			Font fontTableText = new Font(baseFont, 8);
			Font fontSerialNumber = new Font(baseFont, 10, Font.BOLDITALIC);
			Font fontInvoiceNumber = new Font(baseFont, 14, Font.BOLDITALIC);

			pdfFile = new FileOutputStream(new File("pdf/" + fileName));
			pdfDocument = new Document(PageSize.A4);
			PdfWriter.getInstance(pdfDocument, pdfFile);

			pdfDocument.open();

			pdfDocument.addTitle("ПСРФ - " + fileName);

			PdfPTable tableSupplierInfoText = new PdfPTable(1);
			tableSupplierInfoText.setWidthPercentage(100);

			PdfPCell cellSupplierInfoText = new PdfPCell(new Phrase(
					"Продавач / име и дреса / телефон", fontInfo));
			cellSupplierInfoText.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoText.setBorder(Rectangle.NO_BORDER);
			cellSupplierInfoText.setPaddingBottom(5);
			tableSupplierInfoText.addCell(cellSupplierInfoText);

			pdfDocument.add(tableSupplierInfoText);

			PdfPTable tableSupplierInfoContent = new PdfPTable(1);
			tableSupplierInfoContent.setWidthPercentage(100);

			PdfPCell cellSupplierInfoTextContent = new PdfPCell(new Phrase(
					invoice.getSupplier().getSupplierName() + " - "
							+ invoice.getSupplier().getSupplierAddress()
							+ " - "
							+ invoice.getSupplier().getSupplierPhoneNumber(),
					fontInfoText));
			cellSupplierInfoTextContent
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoTextContent.setBorder(Rectangle.BOTTOM
					| Rectangle.LEFT);
			cellSupplierInfoTextContent.setPaddingBottom(5);
			tableSupplierInfoContent.addCell(cellSupplierInfoTextContent);

			pdfDocument.add(tableSupplierInfoContent);

			PdfPTable tableSupplierInfoAccountText = new PdfPTable(3);
			tableSupplierInfoAccountText.setWidthPercentage(100);

			PdfPCell cellSupplierInfoAccountTextBankAccountNumber = new PdfPCell(
					new Phrase("Жиро сметка:", fontInfo));
			cellSupplierInfoAccountTextBankAccountNumber
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankAccountNumber
					.setBorder(Rectangle.NO_BORDER);
			cellSupplierInfoAccountTextBankAccountNumber.setPaddingBottom(5);
			cellSupplierInfoAccountTextBankAccountNumber.setPaddingTop(10);
			tableSupplierInfoAccountText
					.addCell(cellSupplierInfoAccountTextBankAccountNumber);

			PdfPCell cellSupplierInfoAccountTextRegistryNumber = new PdfPCell(
					new Phrase("Број за ДДВ:", fontInfo));
			cellSupplierInfoAccountTextRegistryNumber
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextRegistryNumber
					.setBorder(Rectangle.NO_BORDER);
			cellSupplierInfoAccountTextRegistryNumber.setPaddingBottom(5);
			cellSupplierInfoAccountTextRegistryNumber.setPaddingTop(10);
			tableSupplierInfoAccountText
					.addCell(cellSupplierInfoAccountTextRegistryNumber);

			PdfPCell cellSupplierInfoAccountTextBankName = new PdfPCell(
					new Phrase("Депонент на:", fontInfo));
			cellSupplierInfoAccountTextBankName
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankName.setBorder(Rectangle.NO_BORDER);
			cellSupplierInfoAccountTextBankName.setPaddingBottom(5);
			cellSupplierInfoAccountTextBankName.setPaddingTop(10);
			tableSupplierInfoAccountText
					.addCell(cellSupplierInfoAccountTextBankName);

			PdfPCell cellSupplierInfoAccountTextBankAccountNumberValue = new PdfPCell(
					new Phrase(invoice.getBankInfo().getBankInfoAccount(), fontInfoText));
			cellSupplierInfoAccountTextBankAccountNumberValue
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankAccountNumberValue
					.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
			cellSupplierInfoAccountTextBankAccountNumberValue
					.setPaddingBottom(5);
			tableSupplierInfoAccountText
					.addCell(cellSupplierInfoAccountTextBankAccountNumberValue);

			PdfPCell cellSupplierInfoAccountTextRegistryNumberValue = new PdfPCell(
					new Phrase(invoice.getSupplier().getSupplierRegistryNumber(),
							fontInfoText));
			cellSupplierInfoAccountTextRegistryNumberValue
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextRegistryNumberValue
					.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
			cellSupplierInfoAccountTextRegistryNumberValue.setPaddingBottom(5);
			tableSupplierInfoAccountText
					.addCell(cellSupplierInfoAccountTextRegistryNumberValue);

			PdfPCell cellSupplierInfoAccountTextBankNameValue = new PdfPCell(
					new Phrase(invoice.getBankInfo().getBankInfoName(),
							fontInfoText));
			cellSupplierInfoAccountTextBankNameValue
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankNameValue.setBorder(Rectangle.BOTTOM
					| Rectangle.LEFT);
			cellSupplierInfoAccountTextBankNameValue.setPaddingBottom(5);
			tableSupplierInfoAccountText
					.addCell(cellSupplierInfoAccountTextBankNameValue);

			pdfDocument.add(tableSupplierInfoAccountText);

			pdfDocument.add(new Phrase("\n"));
			pdfDocument.add(new Phrase("\n"));

			// pdfDocument.add(new Chunk(new LineSeparator()));

			PdfPTable tableCustomerInfoText = new PdfPTable(1);
			tableCustomerInfoText.setWidthPercentage(100);

			PdfPCell cellCustomerInfoText = new PdfPCell(new Phrase(
					"Купувач / име и адреса /", fontInfo));
			cellCustomerInfoText.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellCustomerInfoText.setBorder(Rectangle.NO_BORDER);
			cellCustomerInfoText.setPaddingBottom(5);
			tableCustomerInfoText.addCell(cellCustomerInfoText);

			pdfDocument.add(tableCustomerInfoText);

			PdfPTable tableCustomerInvoceInfoContent = new PdfPTable(2);
			tableCustomerInvoceInfoContent.setWidthPercentage(100);

			PdfPCell cellCustomerInfoContentCustomerName = new PdfPCell(
					new Phrase(invoice.getCustomer().getCustomerName() + "\n"
							+ invoice.getCustomer().getCustomerAddress(),
							fontInfoText));
			cellCustomerInfoContentCustomerName
					.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellCustomerInfoContentCustomerName
					.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCustomerInfoContentCustomerName.setFixedHeight(100);

			PdfPCell cellInvoiceInfo = new PdfPCell(new Phrase("", fontNormal));
			cellInvoiceInfo.setBorder(Rectangle.NO_BORDER);

			PdfPTable tableInvoceInfoContentNested = new PdfPTable(2);
			tableInvoceInfoContentNested.setWidthPercentage(100);
			tableInvoceInfoContentNested.getDefaultCell().setFixedHeight(20);

			PdfPCell cellInvoceInfoContentNestedSerialNumberText = new PdfPCell(
					new Phrase("Сериски број:", fontSerialNumber));
			cellInvoceInfoContentNestedSerialNumberText
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedSerialNumberText
					.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentNestedSerialNumberText.setPaddingTop(4);
			cellInvoceInfoContentNestedSerialNumberText.setFixedHeight(20);

			PdfPCell cellInvoceInfoContentNestedSerialNumberValue = new PdfPCell(
					new Phrase("", fontInfoText));
			cellInvoceInfoContentNestedSerialNumberValue
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedSerialNumberValue
					.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentNestedSerialNumberValue.setFixedHeight(20);

			PdfPTable tableInvoceInfoContentNestedSerialNumberValue = new PdfPTable(
					1);
			tableInvoceInfoContentNestedSerialNumberValue
					.setWidthPercentage(100);
			
			Phrase phraseInvoceSerialNumberValue = new Phrase(invoice.getInvoiceSerialNumber(), fontInfoText);
			phraseInvoceSerialNumberValue.add(" ");
			
			PdfPCell cellInvoceInfoContentNestedSerialNumberValueNested = new PdfPCell(phraseInvoceSerialNumberValue);
//			cellInvoceInfoContentNestedSerialNumberValueNested.setFixedHeight(20);
			cellInvoceInfoContentNestedSerialNumberValueNested
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedSerialNumberValueNested
					.setBorder(Rectangle.BOTTOM);

			tableInvoceInfoContentNestedSerialNumberValue
					.addCell(cellInvoceInfoContentNestedSerialNumberValueNested);

			cellInvoceInfoContentNestedSerialNumberValue
					.addElement(tableInvoceInfoContentNestedSerialNumberValue);

			PdfPCell cellInvoceInfoContentNestedInvoiceNumberText = new PdfPCell(
					new Phrase("Фактура бр.", fontInvoiceNumber));
			cellInvoceInfoContentNestedInvoiceNumberText
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceNumberText
					.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentNestedInvoiceNumberText.setFixedHeight(20);

			PdfPCell cellInvoceInfoContentNestedInvoiceNumberValue = new PdfPCell(
					new Phrase("", fontInfoText));
			cellInvoceInfoContentNestedInvoiceNumberValue
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceNumberValue
					.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentNestedInvoiceNumberValue.setFixedHeight(20);

			PdfPTable tableInvoceInfoContentNestedInvoiceNumberValue = new PdfPTable(
					1);
			tableInvoceInfoContentNestedInvoiceNumberValue
					.setWidthPercentage(100);

			Phrase phraseInvoceNumberValue = new Phrase(invoice.getInvoiceNumber(), fontInfoText);
			phraseInvoceNumberValue.add(" ");
			
			PdfPCell cellInvoceInfoContentNestedInvoiceNumberValueNested = new PdfPCell(phraseInvoceNumberValue);
//			cellInvoceInfoContentNestedInvoiceNumberValueNested.setFixedHeight(18);
			cellInvoceInfoContentNestedInvoiceNumberValueNested
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceNumberValueNested
					.setBorder(Rectangle.BOTTOM);

			tableInvoceInfoContentNestedInvoiceNumberValue
					.addCell(cellInvoceInfoContentNestedInvoiceNumberValueNested);

			cellInvoceInfoContentNestedInvoiceNumberValue
					.addElement(tableInvoceInfoContentNestedInvoiceNumberValue);

			PdfPCell cellInvoceInfoContentNestedInvoiceFillerText = new PdfPCell(
					new Phrase("", fontNormal));
			cellInvoceInfoContentNestedInvoiceFillerText
					.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellInvoceInfoContentNestedInvoiceFillerText
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceFillerText
					.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentNestedInvoiceFillerText.setFixedHeight(20);

			PdfPCell cellInvoceInfoContentNestedInvoiceFillerValue = new PdfPCell(
					new Phrase("", fontNormal));
			cellInvoceInfoContentNestedInvoiceFillerValue
					.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellInvoceInfoContentNestedInvoiceFillerValue
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceFillerValue
					.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentNestedInvoiceFillerValue.setFixedHeight(20);

			PdfPCell cellInvoceInfoContentNestedInvoiceDateText = new PdfPCell(
					new Phrase("Датум,", fontNormal));
			cellInvoceInfoContentNestedInvoiceDateText
					.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellInvoceInfoContentNestedInvoiceDateText
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceDateText
					.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentNestedInvoiceDateText.setFixedHeight(20);
			cellInvoceInfoContentNestedInvoiceDateText.setPaddingRight(10);

			PdfPCell cellInvoceInfoContentNestedInvoiceDateValue = new PdfPCell(
					new Phrase(invoice.getInvoiceDate(), fontInfoText));
			cellInvoceInfoContentNestedInvoiceDateValue
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceDateValue.setFixedHeight(20);

			PdfPCell cellInvoceInfoContentNestedInvoiceCurrencyText = new PdfPCell(
					new Phrase("Валута,", fontNormal));
			cellInvoceInfoContentNestedInvoiceCurrencyText
					.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellInvoceInfoContentNestedInvoiceCurrencyText
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceCurrencyText
					.setBorder(Rectangle.NO_BORDER);
			cellInvoceInfoContentNestedInvoiceCurrencyText.setFixedHeight(20);
			cellInvoceInfoContentNestedInvoiceCurrencyText.setPaddingRight(10);

			String invoiceCurrencyValue = "";			
			if (!"- Избери валута -".equals(invoice.getInvoiceCurrency())) {
				invoiceCurrencyValue = invoice.getInvoiceCurrency();
			}
			
			PdfPCell cellInvoceInfoContentNestedInvoiceCurrencyValue = new PdfPCell(
					new Phrase(invoiceCurrencyValue, fontSerialNumber));
			cellInvoceInfoContentNestedInvoiceCurrencyValue
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoceInfoContentNestedInvoiceCurrencyValue.setFixedHeight(20);

			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedSerialNumberText);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedSerialNumberValue);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedInvoiceNumberText);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedInvoiceNumberValue);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedInvoiceFillerText);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedInvoiceFillerValue);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedInvoiceDateText);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedInvoiceDateValue);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedInvoiceCurrencyText);
			tableInvoceInfoContentNested
					.addCell(cellInvoceInfoContentNestedInvoiceCurrencyValue);

			cellInvoiceInfo.addElement(tableInvoceInfoContentNested);

			tableCustomerInvoceInfoContent
					.addCell(cellCustomerInfoContentCustomerName);
			tableCustomerInvoceInfoContent.addCell(cellInvoiceInfo);

			pdfDocument.add(tableCustomerInvoceInfoContent);

			pdfDocument.add(new Phrase("\n"));

			PdfPTable tableDeliveryInfoText = new PdfPTable(1);
			tableDeliveryInfoText.setWidthPercentage(100);

			PdfPCell cellDeliveryInfoText = new PdfPCell(
					new Phrase(
							"По испратница бр. ______________________________ од ____________________20_____ год.",
							fontNormal));
			cellDeliveryInfoText.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDeliveryInfoText.setBorder(Rectangle.NO_BORDER);
			cellDeliveryInfoText.setPaddingBottom(10);
			tableDeliveryInfoText.addCell(cellDeliveryInfoText);

			pdfDocument.add(tableDeliveryInfoText);

			PdfPTable tableInvoiceOrderItems = new PdfPTable(
					orderItemColumnNames.length);
			tableInvoiceOrderItems.setWidths(headerwidths);
			tableInvoiceOrderItems.setWidthPercentage(100);

			for (int i = 0; i < orderItemColumnNames.length; i++) {
				tableInvoiceOrderItems.addCell(new Phrase(
						orderItemColumnNames[i], fontTableHeader));
			}

			int rowNumber = 0;

			tableInvoiceOrderItems.setHeaderRows(1);

			for (OrderItem orderItem : orderItems) {
				++rowNumber;

				tableInvoiceOrderItems.addCell(new Phrase(String
						.valueOf(rowNumber), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase(orderItem
						.getProduct().getProductName(), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase(orderItem
						.getProduct().getProductMeasurement(), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase(orderItem
						.getOrderItemQuantity(), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase(orderItem
						.getProduct().getProductPrice(), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase(orderItem
						.getOrderItemQuantityPriceWithoutTax(), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase(orderItem
						.getProduct().getProductTax(), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase(orderItem
						.getOrderItemQuantityTax(), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase(orderItem
						.getOrderItemQuantityPrice(), fontTableText));
			}

			int leftOverRows = numberOfRows - orderItems.size();

			for (int i = 0; i < leftOverRows; i++) {
				++rowNumber;
				tableInvoiceOrderItems.addCell(new Phrase(String
						.valueOf(rowNumber), fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
			}

			PdfPCell cellInvoiceTotalText = new PdfPCell(new Phrase("Вкупно",
					fontTableHeader));
			cellInvoiceTotalText.setColspan(3);
			cellInvoiceTotalText.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoiceTotalText.setPaddingRight(10);
			tableInvoiceOrderItems.addCell(cellInvoiceTotalText);

			tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
			tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
			tableInvoiceOrderItems.addCell(new Phrase(invoice
					.getInvoiceTotalTax(), fontTableText));
			tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
			tableInvoiceOrderItems.addCell(new Phrase(invoice
					.getInvoiceTotalPriceTax(), fontTableText));
			tableInvoiceOrderItems.addCell(new Phrase(invoice
					.getInvoiceTotalPrice(), fontTableText));

			pdfDocument.add(tableInvoiceOrderItems);

			// pdfDocument.add(new Phrase("\n\n\n\n\n\n"));

			PdfPTable tableInvoiceTotalText = new PdfPTable(1);
			tableInvoiceTotalText.setWidthPercentage(100);

			PdfPCell cellTableInvoiceTotalText = new PdfPCell(
					new Phrase(
							"Со букви: ____________________________________________________________________________________________________________",
							fontNormal));
			cellTableInvoiceTotalText
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTableInvoiceTotalText.setBorder(Rectangle.NO_BORDER);
			cellTableInvoiceTotalText.setPaddingTop(5);
			tableInvoiceTotalText.addCell(cellTableInvoiceTotalText);

			pdfDocument.add(tableInvoiceTotalText);

			PdfPTable tableInvoiceDisclamerText = new PdfPTable(1);
			tableInvoiceDisclamerText.setWidthPercentage(100);

			PdfPCell cellInvoiceDisclamerText = new PdfPCell(
					new Phrase(
							"За испорачаната стока рекламации примаме во рок од _________ дена од денот на приемот, "
									+ "а за лично преземената стока рекламации не признаваме. "
									+ "Штетата настаната при превозот не се надокнадува. "
									+ "Краен рок на плаќање е __________ дена од денот на приемот на фактурата. "
									+ "По истекот на овој рок, купувачот плаќа законска или договорена камата од __________ % на ден.",
							fontNormal));
			cellInvoiceDisclamerText
					.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cellInvoiceDisclamerText.setBorder(Rectangle.NO_BORDER);
			tableInvoiceDisclamerText.addCell(cellInvoiceDisclamerText);

			pdfDocument.add(tableInvoiceDisclamerText);

			pdfDocument.add(new Phrase("\n"));
			pdfDocument.add(new Phrase("\n"));

			PdfPTable tableInvoiceSignatures = new PdfPTable(5);
			tableInvoiceSignatures.setWidthPercentage(100);

			PdfPCell cellInvoiceSignaturesInvoiceReceiver = new PdfPCell(
					new Phrase("Фактурирал,", fontNormal));
			cellInvoiceSignaturesInvoiceReceiver
					.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellInvoiceSignaturesInvoiceReceiver.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceReceiver);

			PdfPCell cellInvoiceSignaturesInvoiceFillMiddleUpperCellLeft = new PdfPCell(
					new Phrase("", fontNormal));
			cellInvoiceSignaturesInvoiceFillMiddleUpperCellLeft
					.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cellInvoiceSignaturesInvoiceFillMiddleUpperCellLeft
					.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceFillMiddleUpperCellLeft);

			PdfPCell cellInvoiceSignaturesInvoiceFillMiddleUpperCellMiddle = new PdfPCell(
					new Phrase("", fontNormal));
			cellInvoiceSignaturesInvoiceFillMiddleUpperCellMiddle
					.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cellInvoiceSignaturesInvoiceFillMiddleUpperCellMiddle
					.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceFillMiddleUpperCellMiddle);

			PdfPCell cellInvoiceSignaturesInvoiceFillMiddleUpperCellRight = new PdfPCell(
					new Phrase("", fontNormal));
			cellInvoiceSignaturesInvoiceFillMiddleUpperCellRight
					.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cellInvoiceSignaturesInvoiceFillMiddleUpperCellRight
					.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceFillMiddleUpperCellRight);

			PdfPCell cellInvoiceSignaturesInvoiceDirector = new PdfPCell(
					new Phrase("Директор,", fontNormal));
			cellInvoiceSignaturesInvoiceDirector
					.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellInvoiceSignaturesInvoiceDirector.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceDirector);

			PdfPCell cellInvoiceSignaturesInvoiceReceiverLine = new PdfPCell(
					new Phrase("______________________", fontNormal));
			cellInvoiceSignaturesInvoiceReceiverLine
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellInvoiceSignaturesInvoiceReceiverLine
					.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceReceiverLine);

			PdfPCell cellInvoiceSignaturesInvoiceFillMiddleLowerCellLeft = new PdfPCell(
					new Phrase("", fontNormal));
			cellInvoiceSignaturesInvoiceFillMiddleLowerCellLeft
					.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cellInvoiceSignaturesInvoiceFillMiddleLowerCellLeft
					.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceFillMiddleLowerCellLeft);

			PdfPCell cellInvoiceSignaturesInvoiceFillMiddleLowerCellMiddle = new PdfPCell(
					new Phrase("", fontNormal));
			cellInvoiceSignaturesInvoiceFillMiddleLowerCellMiddle
					.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cellInvoiceSignaturesInvoiceFillMiddleLowerCellMiddle
					.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceFillMiddleLowerCellMiddle);

			PdfPCell cellInvoiceSignaturesInvoiceFillMiddleLowerCellRight = new PdfPCell(
					new Phrase("", fontNormal));
			cellInvoiceSignaturesInvoiceFillMiddleLowerCellRight
					.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cellInvoiceSignaturesInvoiceFillMiddleLowerCellRight
					.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceFillMiddleLowerCellRight);

			PdfPCell cellInvoiceSignaturesInvoiceDirectorLine = new PdfPCell(
					new Phrase("______________________", fontNormal));
			cellInvoiceSignaturesInvoiceDirectorLine
					.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoiceSignaturesInvoiceDirectorLine
					.setBorder(Rectangle.NO_BORDER);
			tableInvoiceSignatures
					.addCell(cellInvoiceSignaturesInvoiceDirectorLine);

			pdfDocument.add(tableInvoiceSignatures);

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
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd-MM-yyyy-HH-mm-ss");

		String folder = "pdf/";
		String invoiceDate = "";

		String invoiceNumber = invoice.getInvoiceNumber();
		String invoiceCustomerName = "-"
				+ invoice.getCustomer().getCustomerName();
		if (!"".equals(invoice.getInvoiceDate())) {
			invoiceDate = "-" + invoice.getInvoiceDate();
		} else {
			invoiceDate = "-" + simpleDateFormat.format(cal.getTime());
		}

		String extension = ".pdf";
		String invoiceFileName = invoiceNumber + invoiceCustomerName
				+ invoiceDate + extension;

		return invoiceFileName;
	}

}
