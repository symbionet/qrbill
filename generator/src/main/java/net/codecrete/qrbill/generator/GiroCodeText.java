//
// Swiss QR Bill Generator
// Copyright (c) 2018 Manuel Bleichenbacher
// Licensed under MIT License
// https://opensource.org/licenses/MIT
//
package net.codecrete.qrbill.generator;

import java.nio.charset.StandardCharsets;


/**
 * Internal class for encoding and decoding the text embedded in the EPC QR code.
 */
public class GiroCodeText {

    private final Bill bill;

    private GiroCodeText(Bill bill) {
        this.bill = bill;
    }

    /**
     * Gets the text embedded in the QR code according to the data structure defined by EPC
     *
     * @param bill bill data
     * @return QR code text
     */
    public static String create(Bill bill) {
        GiroCodeText qrCodeText = new GiroCodeText(bill);
        return qrCodeText.createText();
    }

    private String createText() {
        String epcText = "BCD" // 3 - BCD (Servicekennung)
            + "\n"
            + "001" // 3 - 001 (Version)
            + "\n"
            + "1" // 1 - 1,2,3,4,5,6,7,8 (Kodierung)
            + "\n"
            + "SCT" // 3 - SCT (Funktion)
            + "\n"
            + this.bill.getAccountBIC() // 8 oder 11 - (BIC EmpfängerBank)
            + "\n"
            + this.bill.getCreditor().getName() // 70 (Name Empfänger/KontoInhaber)
            + "\n"
            + this.bill.getAccount() // 34 (IBAN EmpfängerKonto)
            + "\n"
            + this.bill.getAmount() // 12 - (Betrag in Euro)
            + "\n"
            + "OTHR" // 4 - (Geschäftscode)
            + "\n"
            + this.bill.getReference() // Zahlungsreferenz(35)
            + "\n"
            + this.bill.getUnstructuredMessage(); // oder Text(140)

        // The total payload is limited to 331 bytes
        int payloadLength = epcText.getBytes(StandardCharsets.UTF_8).length; //$NON-NLS-1$
        if (payloadLength > 331) {
            throw new IllegalStateException(
                "total payload is greater than 331 bytes => " //$NON-NLS-1$
                    + payloadLength);
        }

        return epcText;
    }
}
