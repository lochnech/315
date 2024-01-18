package edu.missouristate;

// Keegan Spell

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Week10Lab2 {
    private static final BigDecimal salesTax = new BigDecimal("0.091");
    private static final int storeNumber = 2006;
    private static final String storeAddress = "Palm Springs, FL 33461";
    private static final String storePhoneNumber = "561-223-4357";
    private static final String opNumber = "00006167";
    private static final String terminal = "03";
    private static final BigDecimal cashTendered = new BigDecimal("50.00");


    public static void main(String[] args) {
        List<Item> itemList = new ArrayList<>();

        itemList.add(new Item("068113112582", "PET TREATS", true, new BigDecimal("0.96")));
        itemList.add(new Item("004900005034", "POWERADE", true, new BigDecimal("0.80")));
        itemList.add(new Item("060265217030", "BARS", false, new BigDecimal("1.24")));
        itemList.add(new Item("060265217030", "VAL CANDY", true, new BigDecimal("3.24")));


        BigDecimal tax = getTax(itemList);
        System.out.println(tax);

        BigDecimal subtotal = getSubtotal(itemList);
        System.out.println(subtotal);

        String receipt = buildReceipt(itemList, tax, subtotal);
        System.out.println(receipt);

    }

    private static BigDecimal getTax(List<Item> itemList) {
        BigDecimal taxableAmount = new BigDecimal("0.00");
        BigDecimal tax;
        for (Item item : itemList) {
            if (item.isTaxable()) {
                taxableAmount = taxableAmount.add(item.getPrice());
            }
        }
        tax = taxableAmount.multiply(salesTax, new MathContext(2, RoundingMode.HALF_UP));
        return tax;
    }

    private static BigDecimal getSubtotal(List<Item> itemList) {
        BigDecimal subtotal = new BigDecimal("0.00");
        for (Item item : itemList) {
            subtotal = subtotal.add(item.getPrice());
        }

        return subtotal;
    }

    private static String buildReceipt(List<Item> itemList, BigDecimal tax, BigDecimal subtotal) {
        // MALWART // I15
        // 561-223-4357 // I12
        // 2765 10TH AVE N. // I10
        // Palm Springs, FL 33461 // I7
        // ST# 02006 OP# 009047 TE# 03 TR# 04247 // I0
        // PET TREATS 068113112582 0.96 X // I0
        // POWERADE 004900005034 0.80 X // I0
        // BARS 060265217030 1.24 N // I0
        // VAL CANDY 060265217030 3.24 X // I0
        // SUBTOTAL N.NN // I19
        // TAX 1 9.100 % N.NN // I10
        // TOTAL N.NN // I22
        // CASH TEND N.NN // I18
        // CHANGE DUE NN.NN // I17

        String newline = "\n";
        StringBuilder sb = new StringBuilder();

        // HEADER
        sb.append(" ".repeat(15)).append("MALWART").append(newline);
        sb.append(" ".repeat(12)).append(storePhoneNumber).append(newline);
        sb.append(" ".repeat(10)).append("2765 10TH AVE N.").append(newline);
        sb.append(" ".repeat(7)).append(storeAddress).append(newline);
        sb.append("ST# 0" + storeNumber + " ");
        sb.append("OP# "+ opNumber +" TE# " + terminal + " ");
        int transactionId = 4247;
        sb.append("TR# 0"+ transactionId).append(newline);

        // ITEMS
        for (Item item : itemList) {
            sb.append(item.getShortDescription());
            sb.append(" ".repeat(14 - item.getShortDescription().length()));
            sb.append(item.getSku() + " ");
            sb.append(" ".repeat(9 - item.getPrice().toString().length()));
            sb.append(item.getPrice() + " ");
            sb.append((item.isTaxable()) ? "X":"N").append(newline);
        }

        // SUBTOTAL
        sb.append(" ".repeat(19) + "SUBTOTAL ");
        sb.append(" ".repeat(8 - subtotal.toString().length()) + subtotal).append(newline);
        // TAX
        sb.append(" ".repeat(10) + "TAX 1 9.100 % ");
        sb.append(" ".repeat(9 - tax.toString().length()));
        sb.append(tax).append(newline);

        // TOTAL
        BigDecimal total = subtotal.add(tax);
        sb.append(" ".repeat(22) + "TOTAL ");
        sb.append(" ".repeat(8 - total.toString().length()));
        sb.append(total).append(newline);

        // CASH TEND
        sb.append(" ".repeat(18) + "CASH TEND ");
        sb.append(" ".repeat(8 - cashTendered.toString().length()));
        sb.append(cashTendered).append(newline);

        // CHANGE DUE
        BigDecimal changeDue = cashTendered.subtract(total);
        sb.append(" ".repeat(17) + "CHANGE DUE ");
        sb.append(" ".repeat(8 - changeDue.toString().length()));
        sb.append(changeDue).append(newline);
        return sb.toString();
    }
}