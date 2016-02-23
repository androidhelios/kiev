package com.chadik.kiev.printer.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.printer.IInvoicePrinter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class InvoicePrinterImpl implements IInvoicePrinter {

	private Document pdfDocument;
	private OutputStream pdfFile;

	private Invoice invoice;
	private List<OrderItem> orderItems;
	
	private DecimalFormat decimalFormat;
	private DecimalFormat decimalFormatNoTrailingZeros;

	@Override
	public void initInvoicePrinter() {

		try {
			
			decimalFormat = new DecimalFormat("0.00");
			decimalFormatNoTrailingZeros = new DecimalFormat("#.##");

			invoice = getInvoice();
			orderItems = invoice.getOrderItems();

			int numberOfRows = 25;

			int headerwidths[] = { 5, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
			String orderItemColumnNames[] = getTableOrderItemColumnNames();

			// String fileName = createFileName(invoice).replaceAll("\\", "-")
			// .replaceAll("/", "-").replaceAll(".", "-")
			// .replaceAll("_", "-");

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

			pdfDocument.addTitle("PSF - v(0.0.2) - " + fileName);

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
					new Phrase(invoice.getBankInfo().getBankInfoAccount(),
							fontInfoText));
			cellSupplierInfoAccountTextBankAccountNumberValue
					.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSupplierInfoAccountTextBankAccountNumberValue
					.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
			cellSupplierInfoAccountTextBankAccountNumberValue
					.setPaddingBottom(5);
			tableSupplierInfoAccountText
					.addCell(cellSupplierInfoAccountTextBankAccountNumberValue);

			PdfPCell cellSupplierInfoAccountTextRegistryNumberValue = new PdfPCell(
					new Phrase(invoice.getSupplier()
							.getSupplierRegistryNumber(), fontInfoText));
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

			Phrase phraseInvoceSerialNumberValue = new Phrase(
					invoice.getInvoiceSerialNumber(), fontInfoText);
			phraseInvoceSerialNumberValue.add(" ");

			PdfPCell cellInvoceInfoContentNestedSerialNumberValueNested = new PdfPCell(
					phraseInvoceSerialNumberValue);
			// cellInvoceInfoContentNestedSerialNumberValueNested.setFixedHeight(20);
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

			Phrase phraseInvoceNumberValue = new Phrase(
					invoice.getInvoiceNumber(), fontInfoText);
			phraseInvoceNumberValue.add(" ");

			PdfPCell cellInvoceInfoContentNestedInvoiceNumberValueNested = new PdfPCell(
					phraseInvoceNumberValue);
			// cellInvoceInfoContentNestedInvoiceNumberValueNested.setFixedHeight(18);
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

			for (int i = 0; i < orderItemColumnNames.length - 4; i++) {

				PdfPCell headerCell = new PdfPCell(new Phrase(
						orderItemColumnNames[i], fontTableHeader));
				headerCell.setRowspan(2);
				headerCell.setVerticalAlignment(Element.ALIGN_CENTER);

				tableInvoiceOrderItems.addCell(headerCell);
			}

			PdfPCell headerCellTax = new PdfPCell(new Phrase("Износ на ДДВ",
					fontTableHeader));
			headerCellTax.setColspan(2);
			headerCellTax.setHorizontalAlignment(Element.ALIGN_CENTER);

			tableInvoiceOrderItems.addCell(headerCellTax);

			PdfPCell headerCellTotalTax = new PdfPCell(new Phrase(
					"Вкупен износ со ДДВ", fontTableHeader));
			headerCellTotalTax.setColspan(2);
			headerCellTotalTax.setHorizontalAlignment(Element.ALIGN_CENTER);

			tableInvoiceOrderItems.addCell(headerCellTotalTax);

			tableInvoiceOrderItems.addCell(new Phrase("18%", fontTableHeader));
			tableInvoiceOrderItems.addCell(new Phrase("5%", fontTableHeader));
			tableInvoiceOrderItems.addCell(new Phrase("18%", fontTableHeader));
			tableInvoiceOrderItems.addCell(new Phrase("5%", fontTableHeader));

			int rowNumber = 0;

			tableInvoiceOrderItems.setHeaderRows(2);
			
			double doubleTempSumQuantityBigTax = 0;
			double doubleTempSumQuantitySmallTax = 0;
			double doubleTempSumQuantityTaxSummary = 0;
			
			double doubleTempSumQuantityBigTaxPrice = 0;
			double doubleTempSumQuantitySmallTaxPrice = 0;
			double doubleTempSumQuantityTaxPriceSummary = 0;

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
				// tableInvoiceOrderItems.addCell(new Phrase(orderItem
				// .getProduct().getProductTax(), fontTableText));
				if (orderItem.getProduct().getProductTax().contains("18")) {
					tableInvoiceOrderItems.addCell(new Phrase(orderItem
							.getOrderItemQuantityTax(), fontTableText));
					tableInvoiceOrderItems
							.addCell(new Phrase("", fontTableText));
					tableInvoiceOrderItems.addCell(new Phrase(orderItem
							.getOrderItemQuantityPrice(), fontTableText));
					tableInvoiceOrderItems
							.addCell(new Phrase("", fontTableText));
					
					doubleTempSumQuantityBigTax = doubleTempSumQuantityBigTax + Double.parseDouble(orderItem
							.getOrderItemQuantityTax().replace(",", "."));
					
					doubleTempSumQuantityBigTaxPrice = doubleTempSumQuantityBigTaxPrice + Double.parseDouble(orderItem
							.getOrderItemQuantityPrice().replace(",", "."));
					
				} else {
					tableInvoiceOrderItems
							.addCell(new Phrase("", fontTableText));
					tableInvoiceOrderItems.addCell(new Phrase(orderItem
							.getOrderItemQuantityTax(), fontTableText));
					tableInvoiceOrderItems
							.addCell(new Phrase("", fontTableText));
					tableInvoiceOrderItems.addCell(new Phrase(orderItem
							.getOrderItemQuantityPrice(), fontTableText));
					
					doubleTempSumQuantitySmallTax = doubleTempSumQuantitySmallTax + Double.parseDouble(orderItem
							.getOrderItemQuantityTax().replace(",", "."));
					
					doubleTempSumQuantitySmallTaxPrice = doubleTempSumQuantitySmallTaxPrice + Double.parseDouble(orderItem
							.getOrderItemQuantityPrice().replace(",", "."));
				}

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
				tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
			}

			PdfPCell cellInvoicePercentageTotalText = new PdfPCell(new Phrase("Вкупен збир:",
					fontTableHeader));
			cellInvoicePercentageTotalText.setColspan(4);
			cellInvoicePercentageTotalText.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoicePercentageTotalText.setPaddingRight(10);
			tableInvoiceOrderItems.addCell(cellInvoicePercentageTotalText);
			
			String stringDoubleTempSumQuantityBigTax = "";
			String stringDoubleTempSumQuantitySmallTax = "";
			String stringDoubleTempSumQuantityBigTaxPrice = "";
			String stringDoubleTempSumQuantitySmallTaxPrice = "";
			
			if (doubleTempSumQuantityBigTax > 0) {
				stringDoubleTempSumQuantityBigTax = decimalFormat.format(doubleTempSumQuantityBigTax);
			}
			
			if (doubleTempSumQuantitySmallTax > 0) {
				stringDoubleTempSumQuantitySmallTax = decimalFormat.format(doubleTempSumQuantitySmallTax);
			}
			
			if (doubleTempSumQuantityBigTaxPrice > 0) {
				stringDoubleTempSumQuantityBigTaxPrice = decimalFormat.format(doubleTempSumQuantityBigTaxPrice);
			}
			
			if (doubleTempSumQuantitySmallTaxPrice > 0) {
				stringDoubleTempSumQuantitySmallTaxPrice = decimalFormat.format(doubleTempSumQuantitySmallTaxPrice);
			}

			tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
			tableInvoiceOrderItems.addCell(new Phrase(invoice
					.getInvoiceTotalTax(), fontTableText));
			tableInvoiceOrderItems.addCell(new Phrase(new Phrase(stringDoubleTempSumQuantityBigTax,
					fontTableText)));
			tableInvoiceOrderItems.addCell(new Phrase(new Phrase(stringDoubleTempSumQuantitySmallTax,
					fontTableText)));
			tableInvoiceOrderItems.addCell(new Phrase(new Phrase(stringDoubleTempSumQuantityBigTaxPrice,
					fontTableText)));
			tableInvoiceOrderItems.addCell(new Phrase(new Phrase(stringDoubleTempSumQuantitySmallTaxPrice,
					fontTableText)));
			
			PdfPCell cellInvoiceTotalText = new PdfPCell(new Phrase("ВКУПНО ЗА ПЛАЌАЊЕ:",
					fontTableHeader));
			cellInvoiceTotalText.setColspan(4);
			cellInvoiceTotalText.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellInvoiceTotalText.setPaddingRight(10);
			tableInvoiceOrderItems.addCell(cellInvoiceTotalText);
			
			tableInvoiceOrderItems.addCell(new Phrase("", fontTableText));
			tableInvoiceOrderItems.addCell(new Phrase(invoice
					.getInvoiceTotalTax(), fontTableHeader));
			
			doubleTempSumQuantityTaxSummary = doubleTempSumQuantityBigTax + doubleTempSumQuantitySmallTax;
			
			PdfPCell cellInvoiceTotalQuantityTaxText = new PdfPCell(new Phrase(decimalFormat.format(doubleTempSumQuantityTaxSummary),
					fontTableHeader));
			cellInvoiceTotalQuantityTaxText.setColspan(2);
			cellInvoiceTotalQuantityTaxText.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellInvoiceTotalQuantityTaxText.setPaddingRight(10);
			tableInvoiceOrderItems.addCell(cellInvoiceTotalQuantityTaxText);
			
			doubleTempSumQuantityTaxPriceSummary = doubleTempSumQuantityBigTaxPrice + doubleTempSumQuantitySmallTaxPrice;
			
			PdfPCell cellInvoiceTotalQuantityPriceText = new PdfPCell(new Phrase(decimalFormat.format(doubleTempSumQuantityTaxPriceSummary),
					fontTableHeader));
			cellInvoiceTotalQuantityPriceText.setColspan(2);
			cellInvoiceTotalQuantityPriceText.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellInvoiceTotalQuantityPriceText.setPaddingRight(10);
			tableInvoiceOrderItems.addCell(cellInvoiceTotalQuantityPriceText);

			pdfDocument.add(tableInvoiceOrderItems);
			
			doubleTempSumQuantityBigTax = 0;
			doubleTempSumQuantitySmallTax = 0;
			
			doubleTempSumQuantityBigTaxPrice = 0;
			doubleTempSumQuantitySmallTaxPrice = 0;
			
			doubleTempSumQuantityTaxSummary = 0;
			doubleTempSumQuantityTaxPriceSummary = 0;

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
					new Phrase("Овластено лице за потпис", fontNormal));
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
					.setHorizontalAlignment(Element.ALIGN_CENTER);
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
					new Phrase("Директор", fontNormal));
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
		return new String[] { "Реден Бр.", "Опис", "Мерна единица", "Количина",
				"Цена без данок", "Износ без данок", "Износ на ДДВ 18",
				"Износ на ДДВ 5", "Вкупен износ со ДДВ 18",
				"Вкупен износ со ДДВ 5" };
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

		String invoiceNumber = invoice.getInvoiceNumber()
				.replaceAll("[.]+", "").replaceAll("[^\\w]", "");
		String invoiceCustomerName = "-"
				+ invoice.getCustomer().getCustomerName()
						.replaceAll("[.]+", "").replaceAll("[^\\w]", "");
		if (!"".equals(invoice.getInvoiceDate())) {
			invoiceDate = "-"
					+ invoice.getInvoiceDate().replaceAll("[.]+", "")
							.replaceAll("[^\\w]", "");
		} else {
			invoiceDate = "-"
					+ simpleDateFormat.format(cal.getTime())
							.replaceAll("[.]+", "").replaceAll("[^\\w]", "");
		}

		String extension = ".pdf";
		String invoiceFileName = invoiceNumber + invoiceCustomerName
				+ invoiceDate + extension;

		return invoiceFileName;
	}

}
