//
// Swiss QR Bill Generator
// Copyright (c) 2017 Manuel Bleichenbacher
// Licensed under MIT License
// https://opensource.org/licenses/MIT
//
package net.codecrete.qrbill.generatortest;

import net.codecrete.qrbill.generator.Bill;
import net.codecrete.qrbill.generator.Bill.QrCodeVersion;
import net.codecrete.qrbill.generator.GraphicsFormat;
import net.codecrete.qrbill.generator.Language;
import net.codecrete.qrbill.generator.OutputSize;
import net.codecrete.qrbill.generator.QRBill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for generation of A6 bills (PDF and SVG)
 * <p>
 * Resulting output is compared byte by byte.
 * </p>
 */
@DisplayName("Bill generation (QR bill only, PDF and SVG)")
class QRBillTest {

    @Test
    void createQRBill1() {
        Bill bill = SampleData.getExample1();
        bill.getFormat().setOutputSize(OutputSize.QR_BILL_ONLY);
        bill.getFormat().setGraphicsFormat(GraphicsFormat.SVG);
        byte[] svg = QRBill.generate(bill);
        FileComparison.assertFileContentsEqual(svg, "qrbill_ex1.svg");
    }

    @Test
    void createQRBill2() {
        Bill bill = SampleData.getExample2();
        bill.getFormat().setOutputSize(OutputSize.QR_BILL_ONLY);
        bill.getFormat().setGraphicsFormat(GraphicsFormat.SVG);
        byte[] svg = QRBill.generate(bill);
        FileComparison.assertFileContentsEqual(svg, "qrbill_ex2.svg");
    }

    @Test
    void createQRBill3() {
        Bill bill = SampleData.getExample3();
        bill.getFormat().setOutputSize(OutputSize.QR_BILL_ONLY);
        bill.getFormat().setGraphicsFormat(GraphicsFormat.SVG);
        byte[] svg = QRBill.generate(bill);
        FileComparison.assertFileContentsEqual(svg, "qrbill_ex3.svg");
    }

    @Test
    void createQRBill4() {
        Bill bill = SampleData.getExample4();
        bill.getFormat().setOutputSize(OutputSize.QR_BILL_ONLY);
        bill.getFormat().setGraphicsFormat(GraphicsFormat.SVG);
        byte[] svg = QRBill.generate(bill);
        FileComparison.assertFileContentsEqual(svg, "qrbill_ex4.svg");
    }

    @Test
    void createQRBill5b() {
        Bill bill = SampleData.getExample3();
        bill.getFormat().setOutputSize(OutputSize.QR_BILL_EXTRA_SPACE);
        bill.getFormat().setGraphicsFormat(GraphicsFormat.SVG);
        byte[] svg = QRBill.generate(bill);
        FileComparison.assertFileContentsEqual(svg, "qrbill_ex5.svg");
    }

    @Test
    void createQRBillFrench() {
        Bill bill = SampleData.getExample1();
        bill.getFormat().setGraphicsFormat(GraphicsFormat.SVG);
        bill.getFormat().setLanguage(Language.FR);
        byte[] svg = QRBill.generate(bill);
        FileComparison.assertFileContentsEqual(svg, "qrbill_fr.svg");
    }

    @Test
    void qrBillSentFromLI_correctAddress() {
        Bill bill = SampleData.getExample1();
        bill.getFormat().setLocalCountryCode("LI");
        bill.getFormat().setGraphicsFormat(GraphicsFormat.SVG);
        byte[] svg = QRBill.generate(bill);
        FileComparison.assertFileContentsEqual(svg, "qrbill_li.svg");
    }

    @Test
    void createGiroCodeBill1() {
        Bill bill = SampleData.getExample1();
        bill.setQrCodeVersion(QrCodeVersion.EU);
        bill.setAccountBIC("UBSWCHZH80A");
        bill.getFormat().setOutputSize(OutputSize.QR_BILL_ONLY);
        bill.getFormat().setGraphicsFormat(GraphicsFormat.SVG);
        byte[] svg = QRBill.generate(bill);
        FileComparison.assertFileContentsEqual(svg, "gcbill_ex1.svg");
    }
}
